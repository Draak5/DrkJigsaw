package com.draak.drkjigsaw.commands;

import com.draak.drkjigsaw.Main;
import com.draak.drkjigsaw.player.PlayerWrapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMode implements CommandExecutor {

    private Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player plr = (Player) commandSender;
        PlayerWrapper pw = plugin.players.get(plr);

        if (args.length == 1) {
            pw.setMode(args[0]);
        } else {
            pw.sendError("Invalid Command Format: /mode [edit/play]");
        }
        return false;
    }

}
