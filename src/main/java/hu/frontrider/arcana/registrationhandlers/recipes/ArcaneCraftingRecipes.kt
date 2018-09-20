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
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.crafting.ShapedArcaneRecipe
import thaumcraft.api.crafting.ShapelessArcaneRecipe

import hu.frontrider.arcana.ThaumicArcana.MODID
import net.minecraft.init.Items


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
            val book = ListBuilder(NonNullList.create<Ingredient>())
                    .add(Ingredient.fromItem(Items.BOOK!!))
                    .add(Ingredient.fromItem(sal_mundi!!))
                    .add(Ingredient.fromItem(sal_mundi!!))
                    .add(Ingredient.fromItem(sal_mundi!!))
                    .add(Ingredient.fromItem(sal_mundi!!))
                    .build() as NonNullList<Ingredient>

            ThaumcraftApi.addArcaneCraftingRecipe(
                    ResourceLocation(MODID, "create_book"),
                    ShapelessArcaneRecipe(defaultGroup,
                            "BIOMANCY_BASICS",
                            30,
                            AspectList().add(Aspect.ORDER, 3),
                            ItemStack(enchanter!!),
                            book.toArray()))
        }
    }

    companion object {


        @GameRegistry.ObjectHolder("$MODID:experiment_table")
        var experiment_table: Item? = null

        @GameRegistry.ObjectHolder("$MODID:creature_enchanter")
        var enchanter: Item? = null


        @GameRegistry.ObjectHolder("thaumcraft:salis_mundus")
        var sal_mundi: Item? = null

        @GameRegistry.ObjectHolder("thaumcraft:plank_greatwood")
        var plank_greatwood: Item? = null



        @GameRegistry.ObjectHolder("minecraft:bowl")
        var bowl: Item? = null


        @GameRegistry.ObjectHolder("minecraft:glass_bottle")
        var bottle: Item? = null

        @GameRegistry.ObjectHolder("minecraft:stick")
        var stick: Item? = null
    }
}
