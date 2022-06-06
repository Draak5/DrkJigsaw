package com.draak.drkjigsaw.commands;

import com.draak.drkjigsaw.Main;

public class CommandHandler {

    public static void initialize(Main main) {
        main.getCommand("mode").setExecutor(new CommandMode());
        main.getCommand("world").setExecutor(new CommandWorld());
        main.getCommand("fs").setExecutor(new CommandFs());
        main.getCommand("spawn").setExecutor(new CommandSpawn());
    }

}
