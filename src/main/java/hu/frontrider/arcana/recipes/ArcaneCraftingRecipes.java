package hu.frontrider.arcana.recipes;

import hu.frontrider.arcana.registrationhandlers.ItemRegistry;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class ArcaneCraftingRecipes {
    private static ResourceLocation defaultGroup = new ResourceLocation("bionamncy");

    public static void register() {
        initRecipes();
    }

    private static void initRecipes() {
        ThaumcraftApi.addArcaneCraftingRecipe(
                new ResourceLocation(MODID, "create_cage"),
                new ShapedArcaneRecipe(defaultGroup,
                        null,
                        30,
                        (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.ORDER, 1),
                        ItemRegistry.creature_enchanter,
                        " S ", " B ", " S ",
                        'B', "minecraft:book", 'S', "thaumcraft:salis_mundus"));
    }
}
