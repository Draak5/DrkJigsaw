package com.draak.drkjigsaw.codeblocks;

import com.draak.drkjigsaw.ItemTagHandler;
import com.draak.drkjigsaw.Main;
import com.sun.tools.javac.jvm.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CodeBlocks {

    public static ArrayList<CodeBlock> codeBlocks = new ArrayList<>();

    public CodeBlocks() {
        codeBlocks.add(new CodeBlockEvent());
        codeBlocks.add(new CodeBlockPlayer());
    }

    public static void giveCodeBlocks(Player plr) {

        for (CodeBlock block : codeBlocks) {
            ItemStack item = new ItemStack(block.material, 1);

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(block.name);
            item.setItemMeta(meta);

            ItemTagHandler.addCustomTag(item, "codeblockid", block.id);

            plr.getInventory().addItem(item);
        }

    }

    public static CodeBlock getCodeBlock(String codeblockid) {
        Main.getInstance().getServer().broadcastMessage(codeblockid);
        for (CodeBlock i : codeBlocks) {
            if (i.id == codeblockid) return i;
        }
        return new CodeBlock();
    }

    public static boolean isCodeBlock(Material type) {
        for (CodeBlock i : codeBlocks) {
            if (i.material == type) return true;
        }
        return false;
    }
}
