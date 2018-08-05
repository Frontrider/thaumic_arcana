package hu.frontrider.arcana.proxy;

import hu.frontrider.arcana.recipes.AlchemyRecipes;
import hu.frontrider.arcana.recipes.ArcaneCraftingRecipes;
import hu.frontrider.arcana.recipes.InfusionRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void init(FMLInitializationEvent event) {
        AlchemyRecipes.register();
        InfusionRecipes.register();
        ArcaneCraftingRecipes.register();
    }

    public void preInit(FMLPreInitializationEvent event) {

    }
}


