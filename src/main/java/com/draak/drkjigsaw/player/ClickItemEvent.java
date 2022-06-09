package com.draak.drkjigsaw.player;

import com.draak.drkjigsaw.InventoryMenu.InvMenuMyPlots;
import com.draak.drkjigsaw.InventoryMenu.InventoryMenu;
import com.draak.drkjigsaw.ItemTagHandler;
import com.draak.drkjigsaw.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ClickItemEvent implements Listener {

    @EventHandler
    public void onClickItem(InventoryClickEvent e) {
        Player player = (Player)e.getWhoClicked();
        PlayerWrapper pw = Main.getWrapper(player);
        Inventory openHud = player.getOpenInventory().getTopInventory();
        ItemStack clickedItem = e.getCurrentItem();

        if (pw.isInSpawn()) {
            e.setCancelled(true);
        }


        // Spawn Items
        if (e.getClickedInventory() != null) {
            if (e.getClickedInventory().getHolder() instanceof InventoryMenu) {
                InventoryMenu invMenu = (InventoryMenu) openHud.getHolder();
                if (invMenu.getTag().equals("spawnyourplots")) {
                    if (clickedItem != null) {
                        if (ItemTagHandler.hasCustomTag(clickedItem, "spawncreateplot")) {
                            InvMenuMyPlots.openCreatePlot(player);
                        }
                    }
                }
                if (invMenu.getTag().equals("spawncreateplot")) {
                    InvMenuMyPlots.clickCreatePlot(player, clickedItem);
                }
            }
        }


    }

}
