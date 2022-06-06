package com.draak.drkjigsaw.commands;

import com.draak.drkjigsaw.Main;
import com.draak.drkjigsaw.player.PlayerWrapper;
import com.draak.drkjigsaw.worlds.EmptyChunkGenerator;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWorld implements CommandExecutor {

    private final Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player plr = (Player) commandSender;
        PlayerWrapper pw = Main.players.get(plr);

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("create")) {
                plr.sendMessage("Creating World...");

                WorldCreator wc = new WorldCreator("plot_"+args[1].toLowerCase());
                wc.generator(new EmptyChunkGenerator());
                World world = wc.createWorld();
                world.setPVP(true);
                world.setDifficulty(Difficulty.NORMAL);
                world.setSpawnFlags(false, false);
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);


                int plotSize = 128;

                // Generate plot blocks
                for (int x = 0; x < plotSize; x++) {
                    for (int y = 0; y < 50; y++) {
                        for (int z = 0; z < plotSize; z++) {
                            assert world != null;
                            Material mat = Material.STONE;
                            if (y > 44) mat = Material.DIRT;
                            if (y == 49) mat = Material.GRASS_BLOCK;
                            world.getBlockAt(x, y, z).setType(mat);
                        }
                    }
                }
                //-26 49 0 > -1 49 49
                for (int x = -26; x < 0; x++) {
                    for (int z = 0; z < plotSize; z++) {
                        Material mat = Material.CALCITE;
                        if (x == -26 || x == -1) mat = Material.TUFF;
                        if (z == 0 || z == plotSize-1) mat = Material.TUFF;
                        world.getBlockAt(x, 49, z).setType(mat);
                    }
                }

                pw.setMode("play");
                plr.teleport(new Location(world, 5, 50, 5));
            }
            if (args[0].equalsIgnoreCase("join")) {
                World world = Main.getInstance().getServer().getWorld("plot_"+args[1].toLowerCase());
                plr.sendMessage("Joining World...");
                pw.setMode("play");
                plr.teleport(new Location(world, 5, 50, 5));
            }
        } else {
            pw.sendError("Invalid Command Format: /world [create/join] <world>");
        }
        return false;
    }

}
