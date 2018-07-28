package hu.frontrider.arcana.proxy;

import hu.frontrider.arcana.effect.PotionRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class CommonProxy {

    public void init(FMLInitializationEvent event) {
        PotionRegistry.registerRecipes();
    }
}


