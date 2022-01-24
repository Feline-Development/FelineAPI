package dev.trixxie.felineapi;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class FelineAPI {

    /**
     * Calculate player's total experience based on level and progress to next level
     *
     * @param player the player
     * @return the amount of experience player has
     */
    public static int getExp(Player player){
        return getExpFromLevel(player.getLevel()) + Math.round(getExpToNext(player.getLevel()) * player.getExp());
    }

    /**
     * Calculate total experience based on level
     *
     * @param level the level
     * @return the total experience calculated
     */
    public static int getExpFromLevel(int level){
        if (level > 30) {
            return (int) (4.5 * level * level - 162.5 * level + 2220);
        }
        if (level > 15) {
            return (int) (2.5 * level * level - 40.5 * level + 360);
        }
        return level * level + 6 * level;
    }

    /**
     * Calculate level (including progress to next level) based on total experience.
     *
     * @param exp the total experience
     * @return the level calculated
     */
    public static double getLevelFromExp(long exp) {
        int level = getIntLevelFromExp(exp);

        // Get remaining exp progressing towards next level. Cast to float for next bit of math.
        float remainder = exp - (float) getExpFromLevel(level);

        // Get level progress with float precision.
        float progress = remainder / getExpToNext(level);

        // Slap both numbers together and call it a day. While it shouldn't be possible for progress
        // to be an invalid value (value < 0 || 1 <= value)
        return ((double) level) + progress;
    }

    /**
     * Calculate level based on total experience.
     *
     * @param exp the total experience
     * @return the level calculated
     */
    public static int getIntLevelFromExp(long exp) {
        if (exp > 1395) {
            return (int) ((Math.sqrt(72 * exp - 54215D) + 325) / 18);
        }
        if (exp > 315) {
            return (int) (Math.sqrt(40 * exp - 7839D) / 10 + 8.1);
        }
        if (exp > 0) {
            return (int) (Math.sqrt(exp + 9D) - 3);
        }
        return 0;
    }

    /**
     * Get the total amount of experience required to progress to the next level.
     *
     * @param level the current level
     *
     * @see <a href=http://minecraft.gamepedia.com/Experience#Leveling_up>Experience#Leveling_up</a>
     */
    private static int getExpToNext(int level) {
        if (level >= 30) {
            // Simplified formula. Internal: 112 + (level - 30) * 9
            return level * 9 - 158;
        }
        if (level >= 15) {
            // Simplified formula. Internal: 37 + (level - 15) * 5
            return level * 5 - 38;
        }
        // Internal: 7 + level * 2
        return level * 2 + 7;
    }

    /**
     * Change a Player's experience.
     *
     * <p>This method is preferred over {@link Player#giveExp(int)}.
     * <br>In older versions the method does not take differences in exp per level into account.
     * This leads to overlevelling when granting players large amounts of experience.
     * <br>In modern versions, while differing amounts of experience per level are accounted for, the
     * approach used is loop-heavy and requires an excessive number of calculations, which makes it
     * quite slow.
     *
     * @param player the Player affected
     * @param exp the amount of experience to add or remove
     */
    public static void changeExp(Player player, int exp) {
        exp += getExp(player);

        if (exp < 0) {
            exp = 0;
        }

        double levelAndExp = getLevelFromExp(exp);
        int level = (int) levelAndExp;
        player.setLevel(level);
        player.setExp((float) (levelAndExp - level));
    }

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