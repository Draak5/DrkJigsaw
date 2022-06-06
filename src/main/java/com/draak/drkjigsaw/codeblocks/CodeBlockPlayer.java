package com.draak.drkjigsaw.codeblocks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

public class CodeBlockPlayer extends CodeBlock {

    public CodeBlockPlayer() {
        barrel = true;
        material = Material.BLACK_WOOL;
        name = ChatColor.of("#CC8000")+"Player Action";
        signText = "PLAYER ACTION";
        id = "playeraction";
    }



}
