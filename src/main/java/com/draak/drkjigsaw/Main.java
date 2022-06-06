package com.draak.drkjigsaw;

import com.draak.drkjigsaw.codeblocks.CodeBlocks;
import com.draak.drkjigsaw.commands.CommandFs;
import com.draak.drkjigsaw.commands.CommandHandler;
import com.draak.drkjigsaw.commands.CommandMode;
import com.draak.drkjigsaw.commands.CommandWorld;
import com.draak.drkjigsaw.player.BlockEvent;
import com.draak.drkjigsaw.player.JoinEvent;
import com.draak.drkjigsaw.player.PlayerWrapper;
import com.draak.drkjigsaw.worlds.WorldHandler;
import com.sun.tools.javac.comp.Check;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Main extends JavaPlugin {

    public static Main INSTANCE;
    public static HashMap<Player, PlayerWrapper> players = new HashMap<>();
    public static World spawnWorld;


    public CodeBlocks codeBlocks = new CodeBlocks();




    @Override
    public void onEnable() {
        INSTANCE = this;

        spawnWorld = getServer().getWorlds().get(0);

        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockEvent(), this);

        CommandHandler.initialize(this);

        WorldHandler.initialize();

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


}
