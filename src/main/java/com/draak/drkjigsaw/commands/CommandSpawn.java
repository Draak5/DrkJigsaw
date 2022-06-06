package com.draak.drkjigsaw.commands;

import com.draak.drkjigsaw.Main;
import com.draak.drkjigsaw.player.PlayerWrapper;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

    private final Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player plr = (Player) commandSender;
        PlayerWrapper pw = Main.players.get(plr);

        pw.sendToSpawn();
        return true;
    }
}

