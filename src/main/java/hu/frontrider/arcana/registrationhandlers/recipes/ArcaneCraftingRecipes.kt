package hu.frontrider.arcana.registrationhandlers.recipes

import hu.frontrider.arcana.util.ListBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.crafting.CraftingHelper
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.crafting.ShapedArcaneRecipe
import thaumcraft.api.crafting.ShapelessArcaneRecipe

import hu.frontrider.arcana.ThaumicArcana.MODID


class ArcaneCraftingRecipes {


    private val defaultGroup = ResourceLocation("biomancy")

    fun register() {
        initRecipes()
    }

    private fun initRecipes() {
        run {
            val cagePrimer = CraftingHelper.ShapedPrimer()
            cagePrimer.height = 3
            cagePrimer.width = 3

            cagePrimer.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .add(Ingredient.fromItem(bowl!!))
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .add(Ingredient.fromItem(bowl!!))
                    .add(Ingredient.fromItem(arcane_stone_brick!!))
                    .add(Ingredient.fromItem(arcane_stone_brick!!))
                    .add(Ingredient.fromItem(arcane_stone_brick!!))
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_cage"),
                    ShapedArcaneRecipe(defaultGroup,
                            "BIOMANCY_BASICS",
                            30,
                            AspectList().add(Aspect.EARTH, 1).add(Aspect.AIR, 1).add(Aspect.ORDER, 1),
                            ItemStack(arcane_cage!!),
                            cagePrimer))
        }
        run {
            val cagePrimer = CraftingHelper.ShapedPrimer()
            cagePrimer.height = 3
            cagePrimer.width = 3

            cagePrimer.input = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromItem(bottle!!))
                    .add(Ingredient.fromItem(stick!!))
                    .add(Ingredient.fromItem(bottle!!))
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .add(Ingredient.EMPTY)
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .add(Ingredient.fromItem(bowl!!))
                    .add(Ingredient.fromItem(plank_greatwood!!))
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_table"),
                    ShapedArcaneRecipe(defaultGroup,
                            "BIOMANCY_BASICS",
                            30,
                            AspectList().add(Aspect.WATER, 1).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1),
                            ItemStack(experiment_table!!),
                            cagePrimer))
        }
        run {
            val formulaRecipe = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromStacks(ThaumcraftApiHelper.makeCrystal(Aspect.LIFE)))
                    .add(Ingredient.fromStacks(ThaumcraftApiHelper.makeCrystal(Aspect.LIFE)))
                    .add(Ingredient.fromStacks(ThaumcraftApiHelper.makeCrystal(Aspect.LIFE)))
                    .add(Ingredient.fromItem(bottle!!))
                    .add(Ingredient.fromItem(sal_mundi!!))
                    .build()

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_formula"),
                    ShapelessArcaneRecipe(defaultGroup,
                            "BIOMANCY_BASICS",
                            30,
                            AspectList().add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1),
                            formulaRecipe as NonNullList<Ingredient>,
                            ItemStack(formula!!)
                    ))
        }
    }

    companion object {

        @GameRegistry.ObjectHolder("$MODID:arcane_cage")
        var arcane_cage: Item? = null

        @GameRegistry.ObjectHolder("$MODID:experiment_table")
        var experiment_table: Item? = null

        @GameRegistry.ObjectHolder("$MODID:formula")
        var formula: Item? = null

        @GameRegistry.ObjectHolder("thaumcraft:salis_mundus")
        var sal_mundi: Item? = null

        @GameRegistry.ObjectHolder("thaumcraft:plank_greatwood")
        var plank_greatwood: Item? = null

        @GameRegistry.ObjectHolder("thaumcraft:stone_arcane_brick")
        var arcane_stone_brick: Item? = null

        @GameRegistry.ObjectHolder("minecraft:bowl")
        var bowl: Item? = null


        @GameRegistry.ObjectHolder("minecraft:glass_bottle")
        var bottle: Item? = null

        @GameRegistry.ObjectHolder("minecraft:stick")
        var stick: Item? = null
    }
}
