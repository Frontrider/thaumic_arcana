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
}
