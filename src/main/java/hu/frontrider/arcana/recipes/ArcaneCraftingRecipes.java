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
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;

import java.util.List;

import static hu.frontrider.arcana.ThaumicArcana.MODID;


public class ArcaneCraftingRecipes {

    @GameRegistry.ObjectHolder(MODID + ":arcane_cage")
    public static Item arcane_cage = null;

    @GameRegistry.ObjectHolder(MODID + ":experiment_table")
    public static Item experiment_table = null;

    @GameRegistry.ObjectHolder(MODID + ":formula")
    public static Item formula = null;

    @GameRegistry.ObjectHolder("thaumcraft:salis_mundus")
    public static Item sal_mundi = null;

    @GameRegistry.ObjectHolder("thaumcraft:plank_greatwood")
    public static Item plank_greatwood = null;

    @GameRegistry.ObjectHolder("thaumcraft:stone_arcane_brick")
    public static Item arcane_stone_brick = null;

    @GameRegistry.ObjectHolder("minecraft:bowl")
    public static Item bowl = null;


    @GameRegistry.ObjectHolder("minecraft:glass_bottle")
    public static Item bottle = null;

    @GameRegistry.ObjectHolder("minecraft:stick")
    public static Item stick = null;


    private ResourceLocation defaultGroup = new ResourceLocation("biomancy");

    public void register() {
        initRecipes();
    }

    private void initRecipes() {
        {
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
                            "BIOMANCY_BASICS",
                            30,
                            (new AspectList()).add(Aspect.EARTH, 1).add(Aspect.AIR, 1).add(Aspect.ORDER, 1),
                            new ItemStack(arcane_cage),
                            cagePrimer));
        }
        {
            CraftingHelper.ShapedPrimer cagePrimer = new CraftingHelper.ShapedPrimer();
            cagePrimer.height = 3;
            cagePrimer.width = 3;

            cagePrimer.input = (NonNullList<Ingredient>) new ListBuilder<Ingredient>(NonNullList.create())
                    .add(Ingredient.fromItem(bottle))
                    .add(Ingredient.fromItem(stick))
                    .add(Ingredient.fromItem(bottle))
                    .add(Ingredient.fromItem(plank_greatwood))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(plank_greatwood))
                    .add(Ingredient.fromItem(plank_greatwood))
                    .add(Ingredient.fromItem(bowl))
                    .add(Ingredient.fromItem(plank_greatwood))
                    .build();

            ThaumcraftApi.addArcaneCraftingRecipe(
                    new ResourceLocation(MODID, "create_table"),
                    new ShapedArcaneRecipe(defaultGroup,
                            "BIOMANCY_BASICS",
                            30,
                            (new AspectList()).add(Aspect.WATER, 1).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1),
                            new ItemStack(experiment_table),
                            cagePrimer));
        }
        {
            List<Ingredient> formulaRecipe = new ListBuilder<Ingredient>(NonNullList.create())
                    .add(Ingredient.fromStacks(ThaumcraftApiHelper.makeCrystal(Aspect.LIFE)))
                    .add(Ingredient.fromStacks(ThaumcraftApiHelper.makeCrystal(Aspect.LIFE)))
                    .add(Ingredient.fromStacks(ThaumcraftApiHelper.makeCrystal(Aspect.LIFE)))
                    .add(Ingredient.fromItem(bottle))
                    .add(Ingredient.fromItem(sal_mundi))
                    .build();

            ThaumcraftApi.addArcaneCraftingRecipe(
                    new ResourceLocation(MODID, "create_formula"),
                    new ShapelessArcaneRecipe(defaultGroup,
                            "BIOMANCY_BASICS",
                            30,
                            (new AspectList()).add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1),
                            (NonNullList<Ingredient>) formulaRecipe,
                            new ItemStack(formula)
                    ));
        }
    }
}
