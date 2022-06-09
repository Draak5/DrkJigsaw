package com.draak.drkjigsaw.worlds;

import com.draak.drkjigsaw.Main;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.FileUtil;

import java.io.File;
import java.nio.file.SecureDirectoryStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class WorldHandler {


    // Worlds ready for players to join
    ArrayList<String> bufferedWorlds = new ArrayList<>();
    // Worlds players are in
    ArrayList<String> loadedWorlds = new ArrayList<>();
    // Worlds which are not loaded
    ArrayList<String> availableWorlds = new ArrayList<>();





    public WorldHandler() {
        Main.logInfo("Initialize World Handler");

        for (File file : Objects.requireNonNull(Main.getInstance().getServer().getWorldContainer().listFiles())) {
            if (file.isDirectory()) {
                if (!file.getName().equals("spawn")) {
                    if (deleteWorld(file)) {
                        Main.logInfo("Deleted " + file.getName());
                    } else {
                        Main.getInstance().getLogger().warning("Failed to delete " + file.getName());
                    }
                }
            }
        }

        //
        updateBufferedWorlds();
    }

    public boolean deleteWorld(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteWorld(file);
                } else {
                    file.delete();
                }
            }
        }
        return (path.delete());
    }
    public void updateBufferedWorlds() {
        int targetBufferAmount = Main.getInstance().getServer().getOnlinePlayers().size();
        targetBufferAmount -= loadedWorlds.size();
        if (targetBufferAmount > 5) targetBufferAmount = 5;

        Main.logInfo("Target Buffer Count: " + targetBufferAmount);

        if (bufferedWorlds.size() < targetBufferAmount) {
            bufferedWorlds.add("w" + (bufferedWorlds.size() + loadedWorlds.size()));
        } else {
            deleteWorld(Main.getInstance().getServer().getWorld(bufferedWorlds.get(bufferedWorlds.size()-1)).getWorldFolder());
        }
    }
    public void unloadWorld(String world) {

    }
    public void loadWorld() {

    }
    public void loadPlotOntoWorld() {

    }
    public void teleportToWorld(Player plr, String worldName) {

    }

}
