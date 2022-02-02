package com.felinedevelopment.felineapi.utility;

import org.bukkit.inventory.ItemStack;

public class ToolsUtility {

    /**
     * Check if item is tool.
     * <br>Tools are axes, shovels, hoes and pickaxes
     *
     * @param item item
     * @return true if given item is tool
     */

    public static boolean isTool(ItemStack item){
        if(isShovel(item)){
            return true;
        }else if(isAxe(item)){
            return true;
        }else if(isHoe(item)){
            return true;
        }else return isPickaxe(item);
    }

    /**
     * Check if item is any shovel
     *
     * @param item item
     * @return true if given item is shovel
     */
    public static boolean isShovel(ItemStack item){
        return item.getType().toString().toLowerCase().contains("shovel");
    }

    /**
     * Check if item is any axe
     *
     * @param item item
     * @return true if given item is axe
     */
    public static boolean isAxe(ItemStack item){
        return item.getType().toString().toLowerCase().contains("axe") && !item.getType().toString().toLowerCase().contains("pick");
    }

    /**
     * Check if item is any hoe
     *
     * @param item item
     * @return true if given item is hoe
     */
    public static boolean isHoe(ItemStack item){
        return item.getType().toString().toLowerCase().contains("hoe");
    }

    /**
     * Check if item is any pickaxe
     *
     * @param item item
     * @return true if given item is pickaxe
     */
    public static boolean isPickaxe(ItemStack item){
        return item.getType().toString().toLowerCase().contains("pickaxe");
    }
}
