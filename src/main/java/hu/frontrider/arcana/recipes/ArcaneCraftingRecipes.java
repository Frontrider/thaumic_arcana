package hu.frontrider.arcana.recipes;

import hu.frontrider.arcana.util.ListBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class ArcaneCraftingRecipes {

    @GameRegistry.ObjectHolder(MODID + ":arcane_cage")
    public static Item arcane_cage = null;

    @GameRegistry.ObjectHolder("thaumcraft:plank_greatwood")
    public static Item plank_greatwood = null;

    @GameRegistry.ObjectHolder("thaumcraft:stone_arcane_brick")
    public static Item arcane_stone_brick = null;

    @GameRegistry.ObjectHolder("minecraft:bowl")
    public static Item bowl = null;

    private static ResourceLocation defaultGroup = new ResourceLocation("bionamncy");

    public static void register() {
        initRecipes();
    }

    private static void initRecipes() {

        CraftingHelper.ShapedPrimer cagePrimer = new CraftingHelper.ShapedPrimer();
        cagePrimer.height = 3;
        cagePrimer.width = 3;

        cagePrimer.input = (NonNullList<Ingredient>) new ListBuilder<Ingredient>(NonNullList.create())
                .add(Ingredient.fromItem(plank_greatwood))
                .add(Ingredient.fromItem(plank_greatwood))
                .add(Ingredient.fromItem(plank_greatwood))
                .add(Ingredient.fromItem(bowl))
                .add(Ingredient.fromItem(plank_greatwood))
                .add(Ingredient.fromItem(bowl))
                .add(Ingredient.fromItem(arcane_stone_brick))
                .add(Ingredient.fromItem(arcane_stone_brick))
                .add(Ingredient.fromItem(arcane_stone_brick))
                .build();

        ThaumcraftApi.addArcaneCraftingRecipe(
                new ResourceLocation(MODID, "create_cage"),
                new ShapedArcaneRecipe(defaultGroup,
                        "CREATURES",
                        30,
                        (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.AIR, 1).add(Aspect.ORDER, 1),
                        new ItemStack(arcane_cage),
                        cagePrimer));
    }
}
