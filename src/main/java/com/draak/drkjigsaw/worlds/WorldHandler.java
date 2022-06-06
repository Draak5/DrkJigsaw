package com.draak.drkjigsaw.worlds;

import com.draak.drkjigsaw.Main;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.sql.Array;
import java.util.ArrayList;

public class WorldHandler {


    ArrayList<String> unloadedWorlds = new ArrayList<>();
    ArrayList<String> loadedWorlds = new ArrayList<>();
    ArrayList<String> worlds = new ArrayList<>();



    public static void initialize() {
        Main.logInfo("Initialize World Handler");
        FileConfiguration config = Main.getInstance().getConfig();
        /*
        if (config.getConfigurationSection("worlds") != null) {
            for (String world : config.getConfigurationSection("worlds").getKeys(false)) {
                Main.getInstance().getLogger().info("Preparing world '" + world+"'");
                World w = new WorldCreator(world).type(WorldType.FLAT).environment(World.Environment.CUSTOM).generateStructures(false).createWorld();
                w.setPVP(true);
                w.setDifficulty(Difficulty.NORMAL);
                w.setSpawnFlags(false, false);
            }
        }*/
    }
    public static void unloadWorld(String world) {

    }
    public static void loadWorld() {

    }
    public static void createNewPlotWorld() {

    }
    public static void teleportToWorld(Player plr, String worldName) {

    }

}
