package com.draak.drkjigsaw.codeblocks;

import org.bukkit.Material;

public class CodeBlock {
    protected boolean barrel = true;
    protected Material material = Material.STONE;
    protected String name = "Null Block";
    protected String signText = "NULL";
    protected String id = "null";


    public CodeBlock() {
        //
    }

    public boolean hasBarrel() {
        return barrel;
    }
    public Material getMaterial() {
        return material;
    }
    public String getSignText() {
        return signText;
    }

}
