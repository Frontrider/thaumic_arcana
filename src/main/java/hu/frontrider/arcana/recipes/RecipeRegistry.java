package hu.frontrider.arcana.recipes;


import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RecipeRegistry {

    @SubscribeEvent
    public static void registerVanillaRecipes(RegistryEvent.Register<IRecipe> event) {
        AlchemyRecipes.register();
        InfusionRecipes.register();
        ArcaneCraftingRecipes.register();
    }

}