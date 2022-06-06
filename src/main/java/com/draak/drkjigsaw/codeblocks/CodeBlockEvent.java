package com.draak.drkjigsaw.codeblocks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

public class CodeBlockEvent extends CodeBlock {

    public CodeBlockEvent() {
        barrel = false;
        material = Material.WAXED_COPPER_BLOCK;
        name = ChatColor.of("#3F97FC")+"Event Block";
        signText = "EVENT";
        id = "event";
    }



}
