package com.draak.drkjigsaw.player;

import com.draak.drkjigsaw.Main;
import com.draak.drkjigsaw.codeblocks.CodeBlocks;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class PlayerWrapper {

    public enum PlayerMode { Play, Edit }
    private boolean inSpawn = true;
    private int isOnPlotID = 0;
    private Player player;
    private PlayerMode playerMode;


    public PlayerWrapper(Player player) {
        this.player = player;
        sendToSpawn();
    }


    public void sendToSpawn() {
        Location loc = new Location(Main.spawnWorld, 0, -60, 0, 0, 0);
        player.teleport(loc);
        inSpawn = true;
        player.setGameMode(GameMode.ADVENTURE);


        // Inventory
        player.getInventory().clear();
        ItemStack item = new ItemStack(Material.NETHERITE_SCRAP, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f§nYour Plots");
        meta.setLore(Collections.singletonList("§7Click to view your plots"));
        item.setItemMeta(meta);
        player.getInventory().setHeldItemSlot(0);
        player.getInventory().setItem(0, item);
    }
    public void setMode(String mode) {
        switch (mode.toLowerCase()) {
            case "play":
                playerMode = PlayerMode.Play;
                break;
            case "edit":
                playerMode = PlayerMode.Edit;
                break;
            default:
                playerMode = PlayerMode.Edit;
        }

        player.sendMessage("§aSwitching to "+mode+" mode.");

        player.getInventory().clear();
        if (playerMode == PlayerMode.Edit) {
            player.setGameMode(GameMode.CREATIVE);
            CodeBlocks.giveCodeBlocks(player);
            player.teleport(new Location(player.getWorld(), -10, 50, 6, -90, 0));
        }
        if (playerMode == PlayerMode.Play) {
            player.setGameMode(GameMode.SURVIVAL);
            player.teleport(new Location(player.getWorld(), 5, 50, 5, 0, 0));
        }
    }
    public PlayerMode getMode() {
        return playerMode;
    }

    public void sendError(String s) {
        player.sendMessage("§4[♂] §c"+s);
    }
}
