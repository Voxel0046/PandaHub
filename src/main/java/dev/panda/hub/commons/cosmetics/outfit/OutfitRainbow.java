package dev.panda.hub.commons.cosmetics.outfit;

import dev.panda.hub.PandaHub;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class OutfitRainbow extends BukkitRunnable {

    private final Player player;
    private int b;
    private int g;
    private int r;
    private int first;
    private int second;
    private int third;
    private int fourth;
    private int fifth;
    private int sixth;

    private final ItemStack helmet = new ItemStack(Material.LEATHER_HELMET, 1);
    private final ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    private final ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    private final ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);

    private ProfileManager profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
    
    public OutfitRainbow(Player player, int b, int g, int r, int first, int second, int third, int fourth, int fifth, int sixth) {
        this.player = player;
        this.b = b;
        this.g = g;
        this.r = r;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
    }

    @Override
    public void run() {
        Profile profile = profileManager.getProfile(player.getUniqueId());

        if (profile == null) {
            this.cancel();
            return;
        }

        if (!profile.isOutfitRainbow()) {
            this.cancel();
            return;
        }

        if (this.first <= 17) {
            ++this.first;
            this.g = (this.first - 1) * 15;
            changeRainbowArmor(player);
        }
        else if (this.second <= 17) {
            ++this.second;
            this.r = 255 - 15 * (this.second - 1);
            changeRainbowArmor(player);
        }
        else if (this.third <= 17) {
            ++this.third;
            this.b = (this.third - 1) * 15;
            changeRainbowArmor(player);
        }
        else if (this.fourth <= 17) {
            ++this.fourth;
            this.g = 255 - 15 * (this.fourth - 1);
            changeRainbowArmor(player);
        }
        else if (this.fifth <= 17) {
            ++this.fifth;
            this.r = (this.fifth - 1) * 15;
            changeRainbowArmor(player);
        }
        else if (this.sixth <= 17) {
            ++this.sixth;
            this.b = 255 - 15 * (this.sixth - 1);
            changeRainbowArmor(player);
        }
        else {
            this.first = 0;
            this.second = 0;
            this.third = 0;
            this.fourth = 0;
            this.fifth = 0;
            this.sixth = 0;
        }
    }
    
    private ItemStack rainbow(ItemStack itemStack) {
        LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
        meta.setColor(Color.fromBGR(b, g, r));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private void changeRainbowArmor(Player player) {
        player.getInventory().setHelmet(rainbow(helmet));
        player.getInventory().setChestplate(rainbow(chestplate));
        player.getInventory().setLeggings(rainbow(leggings));
        player.getInventory().setBoots(rainbow(boots));
    }
}
