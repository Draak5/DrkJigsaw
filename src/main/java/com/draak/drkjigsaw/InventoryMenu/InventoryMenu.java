package com.draak.drkjigsaw.InventoryMenu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryMenu implements InventoryHolder {

    private Inventory inv;
    private String tag;

    public InventoryMenu(int size, String name) {
        inv = Bukkit.createInventory(this, size, name);
    }
    public InventoryMenu(int size) {
        inv = Bukkit.createInventory(this, size, "Inventory Menu");
    }
    public InventoryMenu() {
        inv = Bukkit.createInventory(this, 27, "Inventory Menu");
    }

    public void fillInventory(ItemStack item) {
        for (int i = 0 ; i < inv.getSize(); i++) {
            inv.setItem(i, item);
        }
    }
    public void setItem(int slot, ItemStack item) {
        inv.setItem(slot, item);
    }
    public void setTag(String tag) {
        this.tag = tag;
    }


    @Override
    public Inventory getInventory() {
        return inv;
    }
    public String getTag() {
        return tag;
    }

}
