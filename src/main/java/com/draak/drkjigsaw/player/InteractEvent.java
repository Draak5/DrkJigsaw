package com.draak.drkjigsaw.player;

import com.draak.drkjigsaw.InventoryMenu.InvMenuMyPlots;
import com.draak.drkjigsaw.InventoryMenu.InventoryMenu;
import com.draak.drkjigsaw.ItemTagHandler;
import com.draak.drkjigsaw.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractEvent implements Listener {

    @EventHandler
    void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        PlayerWrapper pw = Main.getWrapper(player);
        ItemStack eventItem = e.getItem();

        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (ItemTagHandler.hasCustomTag(eventItem, "spawnyourplots")) {
                InvMenuMyPlots.openMyPlots(player);
            }
            if (ItemTagHandler.hasCustomTag(eventItem, "spawncreateplot")) {
                InvMenuMyPlots.clickCreatePlot(player, eventItem);
            }
        }


    }

}
