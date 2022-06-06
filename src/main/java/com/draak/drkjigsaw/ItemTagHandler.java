package com.draak.drkjigsaw;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ItemTagHandler {

    private static final ItemTagHandler INSTANCE = new ItemTagHandler(Main.getInstance());

    private final Main plugin;
    private ItemTagHandler(Main p) {
        plugin = p;
    }

    private NamespacedKey getKey(String tag) {
        return new NamespacedKey(plugin, tag);
    }

    public static ItemMeta addCustomTag(ItemMeta meta, String tag, String value) {
        meta.getPersistentDataContainer().set(INSTANCE.getKey(tag), PersistentDataType.STRING, value);
        return meta;
    }
    public static ItemStack addCustomTag(ItemStack item, String tag, String value) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(INSTANCE.getKey(tag), PersistentDataType.STRING, value);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack addCustomTag(ItemStack item, String tag, int value) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(INSTANCE.getKey(tag), PersistentDataType.INTEGER, value);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack addCustomTag(ItemStack item, String tag, long value) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(INSTANCE.getKey(tag), PersistentDataType.LONG, value);
        item.setItemMeta(meta);
        return item;
    }
    public static boolean hasCustomTag(ItemMeta meta, String tag) {
        return meta.getPersistentDataContainer().has(INSTANCE.getKey(tag), PersistentDataType.STRING);
    }
    public static boolean hasCustomTag(ItemStack meta, String tag) {
        if (meta.getItemMeta() == null) return false;
        if (meta.getItemMeta().getPersistentDataContainer().has(INSTANCE.getKey(tag), PersistentDataType.STRING)) {
            return true;
        }
        return meta.getItemMeta().getPersistentDataContainer().has(INSTANCE.getKey(tag), PersistentDataType.INTEGER);
    }

    public static String getCustomTag(ItemMeta meta, String tag) {
        return meta.getPersistentDataContainer().get(INSTANCE.getKey(tag), PersistentDataType.STRING);
    }
    public static String getCustomTag(ItemStack meta, String tag) {
        return meta.getItemMeta().getPersistentDataContainer().get(INSTANCE.getKey(tag), PersistentDataType.STRING);
    }
    public static int getCustomTagInt(ItemMeta meta, String tag) {
        return meta.getPersistentDataContainer().get(INSTANCE.getKey(tag), PersistentDataType.INTEGER);
    }
    public static int getCustomTagInt(ItemStack meta, String tag) {
        return meta.getItemMeta().getPersistentDataContainer().get(INSTANCE.getKey(tag), PersistentDataType.INTEGER);
    }
    public static long getCustomTagLong(ItemStack meta, String tag) {
        return meta.getItemMeta().getPersistentDataContainer().get(INSTANCE.getKey(tag), PersistentDataType.LONG);
    }
}