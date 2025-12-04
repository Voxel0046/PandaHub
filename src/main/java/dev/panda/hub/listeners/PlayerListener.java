package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.hotbar.Hotbar;
import dev.panda.hub.commons.hotbar.HotbarManager;
import dev.panda.hub.hooks.AJParkourHook;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.compatibility.effect.EffectUtil;
import dev.panda.hub.utilities.compatibility.sound.SoundUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import us.ajg0702.parkour.api.AjPakour;

public class PlayerListener implements Listener {

	private final ProfileManager profileManager;
	private final HotbarManager hotbarManager;

	public PlayerListener(PandaHub plugin) {
		this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
		this.hotbarManager = PandaHub.get().getModuleService().getManagerModule().getHotbarManager();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	private void onPlayerJoinMessage(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.setLevel(2025);
		event.setJoinMessage(null);

		if (ConfigService.CUSTOM_JOIN_ENABLE) {
			if (player.hasPermission(ConfigService.CUSTOM_JOIN_PERMISSION)) {
				Bukkit.broadcastMessage(ChatUtil.placeholder(player, ChatUtil.replaced(player, ConfigService.CUSTOM_JOIN_MESSAGE_JOIN), true));
			}
		}

		if (ConfigService.PLAYER_JOIN_LEVEL_ENABLE) {
			player.setLevel(ConfigService.PLAYER_JOIN_LEVEL);
		}

		if (ConfigService.PLAYER_JOIN_MESSAGE_CLEAR) {
			for (int i = 0; i < 50; i++) {
				player.sendMessage("");
			}
		}

		if (ConfigService.PLAYER_JOIN_MESSAGE_ENABLE) {
			for (String message : ConfigService.PLAYER_JOIN_MESSAGE_MESSAGE) {
				player.sendMessage(ChatUtil.placeholder(player, ChatUtil.replaced(player, message), true));
			}
		}

		if (ConfigService.PLAYER_JOIN_TITLE_ENABLE) {
			String title = ChatUtil.placeholder(player, ChatUtil.replaced(player, ConfigService.PLAYER_JOIN_TITLE_TITLE), true);
			String subtitle = ChatUtil.placeholder(player, ChatUtil.replaced(player, ConfigService.PLAYER_JOIN_TITLE_SUBTITLE), true);
			player.sendTitle(title, subtitle);
		}
	}

	@EventHandler
	private void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		if (ConfigService.CUSTOM_JOIN_ENABLE) {
			if (ConfigService.CUSTOM_JOIN_PERMISSION.isEmpty() || ConfigService.CUSTOM_JOIN_MESSAGE_QUIT.isEmpty()) {
				event.setQuitMessage(null);
			}
			else if (player.hasPermission(ConfigService.CUSTOM_JOIN_PERMISSION)) {
				event.setQuitMessage(ChatUtil.placeholder(player, ChatUtil.replaced(player, ConfigService.CUSTOM_JOIN_MESSAGE_QUIT), true));
			}
			else {
				event.setQuitMessage(null);
			}
		}

		player.setWalkSpeed(0.2F);
		player.getInventory().clear();
	}

	@EventHandler(priority = EventPriority.MONITOR)
	private void onClickInventory(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Profile profile = profileManager.getProfile(player.getUniqueId());

		if (profile.isPvpMode()) {
			ItemStack itemStack = event.getCurrentItem();
			Hotbar pvpModeLeave = hotbarManager.getHotbar("PVP_MODE_LEAVE");

			event.setCancelled(false);

			if (itemStack == null || pvpModeLeave == null) return;

			if (pvpModeLeave.isHotbarItem(itemStack)) {
				event.setCancelled(true);
				player.updateInventory();
			}
			return;
		}

		if (profile.isBuildMode()) {
			event.setCancelled(false);
			return;
		}

		event.setCancelled(true);
		player.updateInventory();
	}

	@EventHandler
	private void onDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			if (ConfigService.DISABLE_PVP) {
				Player player = (Player) event.getEntity();

				if (player == null) return;

				Profile profile = profileManager.getProfile(player.getUniqueId());

				if (profile == null) return;

				if (!profile.isPvpMode()
						|| event.getCause() == EntityDamageEvent.DamageCause.FALL
						|| event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION
						|| event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK
						|| event.getCause() == EntityDamageEvent.DamageCause.FIRE
						|| event.getCause() == EntityDamageEvent.DamageCause.DROWNING
						|| event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK
						|| event.getCause() == EntityDamageEvent.DamageCause.LAVA
						|| event.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
					event.setCancelled(true);
				}
				else return;
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	private void onAttack(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			if (ConfigService.DISABLE_PVP) {
				Player player = (Player) event.getEntity();
				Player damager = (Player) event.getDamager();

				if (player == null || damager == null) return;

				Profile playerProfile = profileManager.getProfile(player.getUniqueId());
				Profile damagerProfile = profileManager.getProfile(damager.getUniqueId());

				if (playerProfile == null || damagerProfile == null) return;

				if (damagerProfile.isPvpMode() || !playerProfile.isPvpMode()) return;

				event.setCancelled(true);

				if (ConfigService.ENABLE_PVP_MESSAGE) {
					ChatUtil.sendMessage(damager, LangService.DAMAGE);
				}
			}
		}
	}

	@EventHandler
	private void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Profile profile = profileManager.getProfile(player.getUniqueId());

		if (profile.isBuildMode()) {
			event.setCancelled(false);
			return;
		}

		if (ConfigService.DISABLE_BLOCK_BREAK) {
			event.setCancelled(true);

			if (ConfigService.ENABLE_BLOCK_BREAK_MESSAGE) {
				ChatUtil.sendMessage(player, LangService.BLOCK_BREAK);
			}
		}
	}

	@EventHandler
	private void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Profile profile = profileManager.getProfile(player.getUniqueId());

		if (profile.isBuildMode()) {
			event.setCancelled(false);
			return;
		}

		if (ConfigService.DISABLE_BLOCK_PLACE) {
			event.setCancelled(true);

			if (ConfigService.ENABLE_BLOCK_PLACE_MESSAGE) {
				ChatUtil.sendMessage(player, LangService.BLOCK_PLACE);
			}
		}
	}

	@EventHandler
	private void onDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		Profile profile = profileManager.getProfile(player.getUniqueId());

		if (profile.isBuildMode()) {
			event.setCancelled(false);
			return;
		}

		if (ConfigService.DISABLE_DROP_ITEM) {
			event.setCancelled(true);

			if (ConfigService.ENABLE_DROP_ITEM_MESSAGE) {
				ChatUtil.sendMessage(player, LangService.DROP_ITEM);
			}
		}
	}

	@EventHandler
	private void onItemPickup(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		Profile profile = profileManager.getProfile(player.getUniqueId());

		if (profile.isBuildMode()) {
			event.setCancelled(false);
			return;
		}

		if (ConfigService.DISABLE_PICKUP_ITEM) {
			event.setCancelled(true);

			if (ConfigService.ENABLE_PICKUP_ITEM_MESSAGE) {
				ChatUtil.sendMessage(player, LangService.PICKUP_ITEM);
			}
		}
	}

	@EventHandler
	private void onDoubleJump(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
		Profile profile = profileManager.getProfile(player.getUniqueId());

		if (profile.isPvpMode() || (AJParkourHook.isAjParkour() && AjPakour.playerInParkour(player))) {
			if (ConfigService.DOUBLE_JUMP_ENABLE) {
				player.setAllowFlight(false);
			}
			event.setCancelled(true);
			return;
		}

		if (ConfigService.DOUBLE_JUMP_ENABLE) {
			if (player.getGameMode().equals(GameMode.CREATIVE) || profile.isFlyMode()) return;
			event.setCancelled(true);

			player.setVelocity(player.getLocation().getDirection().multiply(ConfigService.DOUBLE_JUMP_VELOCITY).setY(1));

			EffectUtil.play(player, ConfigService.DOUBLE_JUMP_EFFECT);
			SoundUtil.play(player, ConfigService.DOUBLE_JUMP_SOUND);

			player.setAllowFlight(false);
		}
	}

	@EventHandler
	public void onPlayerLand(PlayerMoveEvent event) {
		if (ConfigService.DOUBLE_JUMP_ENABLE) {
			Player player = event.getPlayer();

			if (player.isOnGround()) {
				if (!player.getAllowFlight()) {
					player.setAllowFlight(true);
				}
			}
		}
	}

	@EventHandler
	private void onBlockCommands(PlayerCommandPreprocessEvent event) {
		if (ConfigService.BLOCK_COMMAND_ENABLE) {
			if (ConfigService.BLOCK_COMMAND_COMMANDS.contains(event.getMessage().toLowerCase())) {
				event.setCancelled(true);
				ChatUtil.sendMessage(event.getPlayer(), ConfigService.BLOCK_COMMAND_MESSAGE);
			}
		}
	}
}
