package com.draak.drkjigsaw.player;

import com.draak.drkjigsaw.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!Main.players.containsKey(player)) {
            Main.players.put(player, new PlayerWrapper(player));
        }
        Main.getWrapper(player).sendToSpawn();
        player.sendMessage("you joined");
    }

}
