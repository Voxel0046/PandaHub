package dev.panda.hub.commons.hotbar;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.file.FileConfig;
import dev.panda.hub.utilities.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class HotbarManager {

	@Getter private final Map<String, Hotbar> hotbars;
	private final FileConfig hotbarConfig;
	private final ProfileManager profileManager;

	public HotbarManager() {
		this.hotbars = Maps.newHashMap();
		this.hotbarConfig = PandaHub.get().getModuleService().getFileModule().getFile("hotbar");
		this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
	}

	public void load() {
		hotbars.clear();

		ConfigurationSection section = hotbarConfig.getConfiguration();

		if (section == null) return;

		for (String hotbarName : section.getKeys(false)) {
			if (hotbarName.equalsIgnoreCase("GADGET")) continue;

			Hotbar hotbar = new Hotbar(hotbarName);
			hotbar.setEnable(section.getBoolean(hotbarName + ".ENABLE"));
			hotbar.setHead(section.getBoolean(hotbarName + ".ICON.HEAD.ENABLE"));

			ItemStack itemStack;

			if (hotbar.isHead()) {
				if (!section.getString(hotbarName + ".ICON.HEAD.CUSTOM").isEmpty()) {
					itemStack = PandaHub.get()
							.getModuleService()
							.getManagerModule()
							.getSkullManager()
							.getVersion()
							.createCustomSkull(
									section.getString(hotbarName + ".ICON.HEAD.CUSTOM"),
									section.getString(hotbarName + ".ICON.DISPLAYNAME"),
									section.getStringList(hotbarName + ".ICON.DESCRIPTION")
					);
				}
				else if (!section.getString(hotbarName + ".ICON.HEAD.PLAYER").isEmpty()) {
					itemStack = ItemBuilder.createSkull(
							section.getString(hotbarName + ".ICON.HEAD.PLAYER"),
							section.getString(hotbarName + ".ICON.DISPLAYNAME"),
							section.getStringList(hotbarName + ".ICON.DESCRIPTION")
					);
				}
				else {
					itemStack = new ItemBuilder(section.getString(hotbarName + ".ICON.MATERIAL"))
							.data(section.getInt(hotbarName + ".ICON.DATA"))
							.name(section.getString(hotbarName + ".ICON.DISPLAYNAME"))
							.lore(section.getStringList(hotbarName + ".ICON.DESCRIPTION"))
							.amount(section.getInt(hotbarName + ".ICON.AMOUNT"))
							.enchant(section.getBoolean(hotbarName + ".ICON.ENCHANTED"))
							.build();
				}
			}
			else {
				itemStack = new ItemBuilder(section.getString(hotbarName + ".ICON.MATERIAL"))
						.data(section.getInt(hotbarName + ".ICON.DATA"))
						.name(section.getString(hotbarName + ".ICON.DISPLAYNAME"))
						.lore(section.getStringList(hotbarName + ".ICON.DESCRIPTION"))
						.amount(section.getInt(hotbarName + ".ICON.AMOUNT"))
						.enchant(section.getBoolean(hotbarName + ".ICON.GLOW"))
						.build();
			}

			hotbar.setItem(itemStack);
			hotbar.setSlot(section.getInt(hotbarName + ".ICON.SLOT"));
			hotbars.put(hotbarName, hotbar);
		}
	}

	public Hotbar getHotbar(String name) {
		return hotbars.get(name);
	}

	public void setHotbar(Player player) {
		Profile profile = profileManager.getProfile(player.getUniqueId());

		if (profile.isPvpMode()) {
			Hotbar pvpModeLeave = getHotbar("PVP_MODE_LEAVE");

			if (pvpModeLeave.isEnable()) {
				player.getInventory().setItem(pvpModeLeave.getSlot() - 1, null);
				player.getInventory().setItem(pvpModeLeave.getSlot() - 1, pvpModeLeave.getItem());
			}
			return;
		}

		if (!ConfigService.DISABLE_CLEAR_HOTBAR) {
			player.getInventory().clear();
		}

		if (profile.getGadget() != null) {
			profile.getGadget().giveGadgetItem(player);
		}

		for (Hotbar hotbar : hotbars.values()) {
			if (hotbar.isEnable()) {
				if (hotbar.getName().equalsIgnoreCase("PVP_MODE_LEAVE")) continue;
				player.getInventory().setItem(hotbar.getSlot() - 1, hotbar.getItem());
			}
		}
	}

	public void reload() {
		for (Player online : Bukkit.getOnlinePlayers()) {
			setHotbar(online);
		}
	}
}
