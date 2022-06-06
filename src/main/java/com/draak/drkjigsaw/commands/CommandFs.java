package com.draak.drkjigsaw.commands;

import com.draak.drkjigsaw.Main;
import com.draak.drkjigsaw.player.PlayerWrapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFs implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player plr = (Player) commandSender;
        PlayerWrapper pw = Main.players.get(plr);

        if (args.length == 1) {
            int spd = 100;
            try {
                spd = Integer.parseInt(args[0]);
            } catch(Exception e) {
                spd = 100;
            }

            plr.sendMessage("§aFlight speed set to " + spd+"%");
            plr.setFlySpeed(spd/1000f);

        } else {
            pw.sendError("Invalid Command Format: /fs [speed]");
        }
        return false;
    }

}
