package hu.frontrider.arcana;

import net.minecraftforge.common.config.Config;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

@Config(modid = MODID)
public class Configuration {

    @Config.Comment({
            "Toggles weather or not it should enable the platinum recipe.",
            "this one has a toggle, because it can be truly game breaking"
    })
    public static boolean enablePlatinum = false;

    @Config.Comment({
            "a list of entities that can not be enchanted.",
            "Slimes are blacklisted, because the rendering can never be correct. (because of the varying size)"
    })
    public static String[] entityBlacklist = new String[]{
      "minecraft:slime"
    };

    @Config.Comment({
            "Items that you can use in cages to feed the labrats."
    })
    public static String[] rodentFoodWhitelist = new String[]{
            "minecraft:carrot",
            "minecraft:potato"
    };
}
