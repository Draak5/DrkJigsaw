package com.draak.drkjigsaw;

import com.draak.drkjigsaw.codeblocks.CodeBlocks;
import com.draak.drkjigsaw.commands.CommandHandler;
import com.draak.drkjigsaw.player.*;
import com.draak.drkjigsaw.worlds.WorldHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Main extends JavaPlugin {

    public static Main INSTANCE;
    public static HashMap<Player, PlayerWrapper> players = new HashMap<>();
    public static World spawnWorld;

    public static int allPlotIDs = 0;


    public CodeBlocks codeBlocks = new CodeBlocks();

    private WorldHandler worldHandler;


    @Override
    public void onEnable() {
        INSTANCE = this;

        spawnWorld = getServer().getWorlds().get(0);

        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockEvent(), this);
        getServer().getPluginManager().registerEvents(new InteractEvent(), this);
        getServer().getPluginManager().registerEvents(new ClickItemEvent(), this);

        CommandHandler.initialize(this);
        worldHandler = new WorldHandler();

        if (Bukkit.getOnlinePlayers().size() > 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                players.put(p, new PlayerWrapper(p));
            }
        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static void logInfo(String string) {
        INSTANCE.getLogger().info(string);
    }

    public static Main getInstance() {
        return INSTANCE;
    }

    public static PlayerWrapper getWrapper(Player plr) {
        return players.get(plr);
    }


    public void updateBufferedWorlds() {
        worldHandler.updateBufferedWorlds();
    }
}
