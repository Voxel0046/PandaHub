package dev.panda.hub.services.impl;

import dev.panda.hub.utilities.item.ItemBuilder;
import dev.panda.hub.PandaHub;
import dev.panda.hub.services.Service;
import dev.panda.hub.utilities.file.FileConfig;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CosmeticService extends Service {

    private final FileConfig cosmeticConfig = PandaHub.get().getModuleService().getFileModule().getFile("cosmetic");
    private final FileConfig outfitConfig = PandaHub.get().getModuleService().getFileModule().getFile("outfit");
    private final FileConfig trailConfig = PandaHub.get().getModuleService().getFileModule().getFile("trail");
    private final FileConfig gadgetConfig = PandaHub.get().getModuleService().getFileModule().getFile("gadget");
    private final FileConfig messageColorConfig = PandaHub.get().getModuleService().getFileModule().getFile("message-color");
    private final FileConfig chatColorConfig = PandaHub.get().getModuleService().getFileModule().getFile("chat-color");

    // Cosmetic Configuration
    public static String COSMETIC_TITLE;
    public static int COSMETIC_SIZE;

    // Outfit Configuration
    public static String OUTFITS_TITLE;
    public static String OUTFIT_HELMET_TITLE;
    public static String OUTFIT_CHESTPLATE_TITLE;
    public static String OUTFIT_LEGGINGS_TITLE;
    public static String OUTFIT_BOOTS_TITLE;
    public static String OUTFIT_EDITOR_TITLE;

    public static String OUTFIT_EDITOR_EQUIPPED_DISPLAYNAME_BUTTON;
    public static List<String> OUTFIT_EDITOR_EQUIPPED_DESCRIPTION_BUTTON;
    public static String OUTFIT_EDITOR_EQUIPPED_MATERIAL_BUTTON;
    public static int OUTFIT_EDITOR_EQUIPPED_DATA_BUTTON;

    public static String OUTFIT_EDITOR_NO_EQUIPPED_DISPLAYNAME_BUTTON;
    public static List<String> OUTFIT_EDITOR_NO_EQUIPPED_DESCRIPTION_BUTTON;
    public static String OUTFIT_EDITOR_NO_EQUIPPED_MATERIAL_BUTTON;
    public static int OUTFIT_EDITOR_NO_EQUIPPED_DATA_BUTTON;

    public static ItemStack OUTFIT_EDITOR_OUTFITS_BUTTON;
    public static ItemStack OUTFIT_EDITOR_REMOVE_BUTTON;

    // Trail Configuration
    public static String TRAIL_TITLE;

    public static String TRAIL_ALLOWED_DISPLAYNAME_BUTTON;
    public static List<String> TRAIL_ALLOWED_DESCRIPTION_BUTTON;

    public static String TRAIL_EQUIPPED_DISPLAYNAME_BUTTON;
    public static List<String> TRAIL_EQUIPPED_DESCRIPTION_BUTTON;

    public static String TRAIL_DENIED_DISPLAYNAME_BUTTON;
    public static List<String> TRAIL_DENIED_DESCRIPTION_BUTTON;

    public static ItemStack TRAIL_REMOVE_BUTTON;

    // Gadget Configuration
    public static String GADGET_TITLE;

    public static String GADGET_ALLOWED_DISPLAYNAME_BUTTON;
    public static List<String> GADGET_ALLOWED_DESCRIPTION_BUTTON;

    public static String GADGET_EQUIPPED_DISPLAYNAME_BUTTON;
    public static List<String> GADGET_EQUIPPED_DESCRIPTION_BUTTON;

    public static String GADGET_DENIED_DISPLAYNAME_BUTTON;
    public static List<String> GADGET_DENIED_DESCRIPTION_BUTTON;

    public static ItemStack GADGET_REMOVE_BUTTON;

    // Message Color Configuration
    public static String MESSAGE_COLOR_TITLE;

    public static String MESSAGE_COLOR_ALLOWED_DISPLAYNAME_BUTTON;
    public static List<String> MESSAGE_COLOR_ALLOWED_DESCRIPTION_BUTTON;

    public static String MESSAGE_COLOR_EQUIPPED_DISPLAYNAME_BUTTON;
    public static List<String> MESSAGE_COLOR_EQUIPPED_DESCRIPTION_BUTTON;

    public static String MESSAGE_COLOR_DENIED_DISPLAYNAME_BUTTON;
    public static List<String> MESSAGE_COLOR_DENIED_DESCRIPTION_BUTTON;

    public static ItemStack MESSAGE_COLOR_REMOVE_BUTTON;

    // Chat Color Configuration
    public static String CHAT_COLOR_TITLE;

    public static String CHAT_COLOR_ALLOWED_DISPLAYNAME_BUTTON;
    public static List<String> CHAT_COLOR_ALLOWED_DESCRIPTION_BUTTON;

    public static String CHAT_COLOR_EQUIPPED_DISPLAYNAME_BUTTON;
    public static List<String> CHAT_COLOR_EQUIPPED_DESCRIPTION_BUTTON;

    public static String CHAT_COLOR_DENIED_DISPLAYNAME_BUTTON;
    public static List<String> CHAT_COLOR_DENIED_DESCRIPTION_BUTTON;

    public static ItemStack CHAT_COLOR_REMOVE_BUTTON;

    @Override
    public void initialize() {
        COSMETIC_TITLE = cosmeticConfig.getString("MENU.TITLE");
        COSMETIC_SIZE = cosmeticConfig.getInt("MENU.SIZE");

        OUTFITS_TITLE = outfitConfig.getString("OUTFITS.TITLE");
        OUTFIT_HELMET_TITLE = outfitConfig.getString("OUTFIT_HELMET.TITLE");
        OUTFIT_CHESTPLATE_TITLE = outfitConfig.getString("OUTFIT_CHESTPLATE.TITLE");
        OUTFIT_LEGGINGS_TITLE = outfitConfig.getString("OUTFIT_LEGGINGS.TITLE");
        OUTFIT_BOOTS_TITLE = outfitConfig.getString("OUTFIT_BOOTS.TITLE");
        OUTFIT_EDITOR_TITLE = outfitConfig.getString("OUTFIT_EDITOR.TITLE");

        OUTFIT_EDITOR_EQUIPPED_DISPLAYNAME_BUTTON = outfitConfig.getString("BUTTONS.EQUIPPED.ICON.DISPLAYNAME");
        OUTFIT_EDITOR_EQUIPPED_DESCRIPTION_BUTTON = outfitConfig.getStringList("BUTTONS.EQUIPPED.ICON.DESCRIPTION");
        OUTFIT_EDITOR_EQUIPPED_MATERIAL_BUTTON = outfitConfig.getString("BUTTONS.EQUIPPED.ICON.MATERIAL");
        OUTFIT_EDITOR_EQUIPPED_DATA_BUTTON = outfitConfig.getInt("BUTTONS.EQUIPPED.ICON.DATA");

        OUTFIT_EDITOR_NO_EQUIPPED_DISPLAYNAME_BUTTON = outfitConfig.getString("BUTTONS.NO_EQUIPPED.ICON.DISPLAYNAME");
        OUTFIT_EDITOR_NO_EQUIPPED_DESCRIPTION_BUTTON = outfitConfig.getStringList("BUTTONS.NO_EQUIPPED.ICON.DESCRIPTION");
        OUTFIT_EDITOR_NO_EQUIPPED_MATERIAL_BUTTON = outfitConfig.getString("BUTTONS.NO_EQUIPPED.ICON.MATERIAL");
        OUTFIT_EDITOR_NO_EQUIPPED_DATA_BUTTON = outfitConfig.getInt("BUTTONS.NO_EQUIPPED.ICON.DATA");

        OUTFIT_EDITOR_OUTFITS_BUTTON = getButton(outfitConfig, "OUTFITS");
        OUTFIT_EDITOR_REMOVE_BUTTON = getButton(outfitConfig, "REMOVE");

        TRAIL_TITLE = trailConfig.getString("MENU.TITLE");

        TRAIL_ALLOWED_DISPLAYNAME_BUTTON = trailConfig.getString("BUTTONS.TRAIL.ALLOWED.ICON.DISPLAYNAME");
        TRAIL_ALLOWED_DESCRIPTION_BUTTON = trailConfig.getStringList("BUTTONS.TRAIL.ALLOWED.ICON.DESCRIPTION");

        TRAIL_EQUIPPED_DISPLAYNAME_BUTTON = trailConfig.getString("BUTTONS.TRAIL.EQUIPPED.ICON.DISPLAYNAME");
        TRAIL_EQUIPPED_DESCRIPTION_BUTTON = trailConfig.getStringList("BUTTONS.TRAIL.EQUIPPED.ICON.DESCRIPTION");

        TRAIL_DENIED_DISPLAYNAME_BUTTON = trailConfig.getString("BUTTONS.TRAIL.DENIED.ICON.DISPLAYNAME");
        TRAIL_DENIED_DESCRIPTION_BUTTON = trailConfig.getStringList("BUTTONS.TRAIL.DENIED.ICON.DESCRIPTION");

        TRAIL_REMOVE_BUTTON = getButton(trailConfig, "REMOVE");

        GADGET_TITLE = gadgetConfig.getString("MENU.TITLE");

        GADGET_ALLOWED_DISPLAYNAME_BUTTON = gadgetConfig.getString("BUTTONS.GADGET.ALLOWED.ICON.DISPLAYNAME");
        GADGET_ALLOWED_DESCRIPTION_BUTTON = gadgetConfig.getStringList("BUTTONS.GADGET.ALLOWED.ICON.DESCRIPTION");

        GADGET_EQUIPPED_DISPLAYNAME_BUTTON = gadgetConfig.getString("BUTTONS.GADGET.EQUIPPED.ICON.DISPLAYNAME");
        GADGET_EQUIPPED_DESCRIPTION_BUTTON = gadgetConfig.getStringList("BUTTONS.GADGET.EQUIPPED.ICON.DESCRIPTION");

        GADGET_DENIED_DISPLAYNAME_BUTTON = gadgetConfig.getString("BUTTONS.GADGET.DENIED.ICON.DISPLAYNAME");
        GADGET_DENIED_DESCRIPTION_BUTTON = gadgetConfig.getStringList("BUTTONS.GADGET.DENIED.ICON.DESCRIPTION");

        GADGET_REMOVE_BUTTON = getButton(gadgetConfig, "REMOVE");

        MESSAGE_COLOR_TITLE = messageColorConfig.getString("MENU.TITLE");

        MESSAGE_COLOR_ALLOWED_DISPLAYNAME_BUTTON = messageColorConfig.getString("BUTTONS.MESSAGE_COLOR.ALLOWED.ICON.DISPLAYNAME");
        MESSAGE_COLOR_ALLOWED_DESCRIPTION_BUTTON = messageColorConfig.getStringList("BUTTONS.MESSAGE_COLOR.ALLOWED.ICON.DESCRIPTION");

        MESSAGE_COLOR_EQUIPPED_DISPLAYNAME_BUTTON = messageColorConfig.getString("BUTTONS.MESSAGE_COLOR.EQUIPPED.ICON.DISPLAYNAME");
        MESSAGE_COLOR_EQUIPPED_DESCRIPTION_BUTTON = messageColorConfig.getStringList("BUTTONS.MESSAGE_COLOR.EQUIPPED.ICON.DESCRIPTION");

        MESSAGE_COLOR_DENIED_DISPLAYNAME_BUTTON = messageColorConfig.getString("BUTTONS.MESSAGE_COLOR.DENIED.ICON.DISPLAYNAME");
        MESSAGE_COLOR_DENIED_DESCRIPTION_BUTTON = messageColorConfig.getStringList("BUTTONS.MESSAGE_COLOR.DENIED.ICON.DESCRIPTION");

        MESSAGE_COLOR_REMOVE_BUTTON = getButton(messageColorConfig, "REMOVE");

        CHAT_COLOR_TITLE = chatColorConfig.getString("MENU.TITLE");

        CHAT_COLOR_ALLOWED_DISPLAYNAME_BUTTON = chatColorConfig.getString("BUTTONS.CHAT_COLOR.ALLOWED.ICON.DISPLAYNAME");
        CHAT_COLOR_ALLOWED_DESCRIPTION_BUTTON = chatColorConfig.getStringList("BUTTONS.CHAT_COLOR.ALLOWED.ICON.DESCRIPTION");

        CHAT_COLOR_EQUIPPED_DISPLAYNAME_BUTTON = chatColorConfig.getString("BUTTONS.CHAT_COLOR.EQUIPPED.ICON.DISPLAYNAME");
        CHAT_COLOR_EQUIPPED_DESCRIPTION_BUTTON = chatColorConfig.getStringList("BUTTONS.CHAT_COLOR.EQUIPPED.ICON.DESCRIPTION");

        CHAT_COLOR_DENIED_DISPLAYNAME_BUTTON = chatColorConfig.getString("BUTTONS.CHAT_COLOR.DENIED.ICON.DISPLAYNAME");
        CHAT_COLOR_DENIED_DESCRIPTION_BUTTON = chatColorConfig.getStringList("BUTTONS.CHAT_COLOR.DENIED.ICON.DESCRIPTION");

        CHAT_COLOR_REMOVE_BUTTON = getButton(chatColorConfig, "REMOVE");
    }

    private ItemStack getButton(FileConfig fileConfig, String button) {
        return new ItemBuilder(fileConfig.getString("BUTTONS." + button + ".ICON.MATERIAL"))
                .data(fileConfig.getInt("BUTTONS." + button + ".ICON.DATA"))
                .name(fileConfig.getString("BUTTONS." + button + ".ICON.DISPLAYNAME"))
                .lore(fileConfig.getStringList("BUTTONS." + button + ".ICON.DESCRIPTION"))
                .build();
    }
}
