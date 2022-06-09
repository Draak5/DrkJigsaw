package com.draak.drkjigsaw.InventoryMenu;

import com.draak.drkjigsaw.ItemTagHandler;
import com.draak.drkjigsaw.Main;
import com.draak.drkjigsaw.player.PlayerSaveData;
import com.draak.drkjigsaw.player.PlayerWrapper;
import com.sun.tools.javac.jvm.Items;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class InvMenuMyPlots {


    public static void openMyPlots(Player player) {

        PlayerWrapper pw = Main.getWrapper(player);
        InventoryMenu inv = new InventoryMenu(9, "§nYour Plots");

        PlayerSaveData data = pw.getSaveData();
        HashMap<Integer, PlayerSaveData.PlotSize> map = data.getClaimedPlots();
        int slot = 0;
        for (int id : map.keySet()) {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(map.get(id).toString()+" Plot");
            meta.setLore(Collections.singletonList("§8ID: "+id));
            item.setItemMeta(meta);
            inv.setItem(slot, item);

            slot++;
        }


        ItemStack item = new ItemStack(Material.GLOBE_BANNER_PATTERN, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fCreate Plot");
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);
        ItemTagHandler.addCustomTag(item, "spawncreateplot", 1);
        inv.setItem(8, item);

        inv.setTag("spawnyourplots");
        player.openInventory(inv.getInventory());

    }

    public static void openCreatePlot(Player player) {
        PlayerWrapper pw = Main.getWrapper(player);

        PlayerSaveData data = pw.getSaveData();

        InventoryMenu inv = new InventoryMenu(9, "§nCreate Plot");


        // Generate plot items
        int smallPlots = data.getAvailablePlots(PlayerSaveData.PlotSize.Small);
        int largePlots = data.getAvailablePlots(PlayerSaveData.PlotSize.Large);
        int massivePlots = data.getAvailablePlots(PlayerSaveData.PlotSize.Massive);
        int hugePlots = data.getAvailablePlots(PlayerSaveData.PlotSize.Huge);

        ItemStack itemSmall = new ItemStack(Material.COPPER_INGOT, 1);
        ItemStack itemLarge = new ItemStack(Material.IRON_INGOT, 1);
        ItemStack itemMassive = new ItemStack(Material.GOLD_INGOT, 1);
        ItemStack itemHuge = new ItemStack(Material.NETHERITE_INGOT, 1);

        ItemTagHandler.addCustomTag(itemSmall,      "spawncreateplotsize", 1);
        ItemTagHandler.addCustomTag(itemLarge,      "spawncreateplotsize", 2);
        ItemTagHandler.addCustomTag(itemMassive,    "spawncreateplotsize", 3);
        ItemTagHandler.addCustomTag(itemHuge,       "spawncreateplotsize", 4);

        ItemMeta metaSmall = itemSmall.getItemMeta();
        ItemMeta metaLarge = itemLarge.getItemMeta();
        ItemMeta metaMassive = itemMassive.getItemMeta();
        ItemMeta metaHuge = itemHuge.getItemMeta();

        metaSmall   .addItemFlags(ItemFlag.HIDE_ENCHANTS);
        metaLarge   .addItemFlags(ItemFlag.HIDE_ENCHANTS);
        metaMassive .addItemFlags(ItemFlag.HIDE_ENCHANTS);
        metaHuge    .addItemFlags(ItemFlag.HIDE_ENCHANTS);

        metaSmall   .setDisplayName("§fSmall Plot");
        metaLarge   .setDisplayName("§fLarge Plot");
        metaMassive .setDisplayName("§fMassive Plot");
        metaHuge    .setDisplayName("§fHuge Plot");

        metaSmall   .setLore(Collections.singletonList("§7Create a plot that has a size of §o128x128§7."));
        metaLarge   .setLore(Collections.singletonList("§7Create a plot that has a size of §o256x256§7."));
        metaMassive .setLore(Collections.singletonList("§7Create a plot that has a size of §o1024x1024§7."));
        metaHuge    .setLore(Collections.singletonList("§7Create a plot that has a size of §o4096x4096§7."));

        if (smallPlots > 0) {
            itemSmall.setAmount(smallPlots);
            metaSmall.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        }
        if (largePlots > 0) {
            itemLarge.setAmount(largePlots);
            metaLarge.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        }
        if (massivePlots > 0) {
            itemMassive.setAmount(massivePlots);
            metaMassive.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        }
        if (hugePlots > 0) {
            itemHuge.setAmount(hugePlots);
            metaHuge.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        }

        itemSmall.setItemMeta(metaSmall);
        itemLarge.setItemMeta(metaLarge);
        itemMassive.setItemMeta(metaMassive);
        itemHuge.setItemMeta(metaHuge);

        inv.setItem(1, itemSmall);
        inv.setItem(3, itemLarge);
        inv.setItem(5, itemMassive);
        inv.setItem(7, itemHuge);

        inv.setTag("spawncreateplot");
        player.openInventory(inv.getInventory());
    }

    public static void clickCreatePlot(Player player, ItemStack eventItem) {
        PlayerWrapper pw = Main.getWrapper(player);

        if (eventItem == null) return;

        if (ItemTagHandler.hasCustomTag(eventItem, "spawncreateplotsize")) {
            int size = ItemTagHandler.getCustomTagInt(eventItem, "spawncreateplotsize");
            PlayerSaveData.PlotSize[] list = new PlayerSaveData.PlotSize[]{PlayerSaveData.PlotSize.Small, PlayerSaveData.PlotSize.Large, PlayerSaveData.PlotSize.Massive, PlayerSaveData.PlotSize.Huge};

            pw.generatePlot(list[size-1]);
        }

    }
}
