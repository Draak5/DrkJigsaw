package com.draak.drkjigsaw.player;

import com.draak.drkjigsaw.ItemTagHandler;
import com.draak.drkjigsaw.Main;
import com.draak.drkjigsaw.codeblocks.CodeBlock;
import com.draak.drkjigsaw.codeblocks.CodeBlocks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvent implements Listener {

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent e) {
        Player plr = e.getPlayer();
        PlayerWrapper pw = Main.getWrapper(plr);
        Location loc = e.getBlock().getLocation();
        World world = loc.getWorld();

        if (pw.getMode() == PlayerWrapper.PlayerMode.Edit) {
            if (ItemTagHandler.hasCustomTag(e.getItemInHand(), "codeblockid")) {
                CodeBlock codeBlock = CodeBlocks.getCodeBlock(ItemTagHandler.getCustomTag(e.getItemInHand(), "codeblockid"));

                world.getBlockAt(loc).setType(codeBlock.getMaterial());

                world.getBlockAt(loc.clone().add(0, 0, 1)).setBlockData(Bukkit.createBlockData("minecraft:jigsaw[orientation=south_up]"));
                world.getBlockAt(loc.clone().add(0, 1, 1)).setType(Material.BLACKSTONE_SLAB);

                Block signBlock = world.getBlockAt(loc.clone().add(-1, 0, 0));
                signBlock.setBlockData(Bukkit.createBlockData("minecraft:spruce_wall_sign[facing=west]"));
                Sign sign = (Sign) signBlock.getState();
                sign.setLine(0, "§f" + codeBlock.getSignText());
                sign.update();

                if (codeBlock.hasBarrel()) {
                    world.getBlockAt(loc.clone().add(0, 1, 0)).setType(Material.BARREL);
                }
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e) {
        Player plr = e.getPlayer();
        PlayerWrapper pw = Main.getWrapper(plr);
        Block b = e.getBlock();
        Location loc = b.getLocation();
        World world = loc.getWorld();

        if (pw.getMode() == PlayerWrapper.PlayerMode.Edit) {
            if (CodeBlocks.isCodeBlock(b.getType())) {
                assert world != null;

                world.getBlockAt(loc.clone().add(-1, 0, 0)).setType(Material.AIR);
                world.getBlockAt(loc).setType(Material.AIR);
                world.getBlockAt(loc.clone().add(0, 1, 0)).setType(Material.AIR);
                world.getBlockAt(loc.clone().add(0, 0, 1)).setType(Material.AIR);
                world.getBlockAt(loc.clone().add(0, 1, 1)).setType(Material.AIR);
            } else {
                e.setCancelled(true);
            }
        }
    }

}
