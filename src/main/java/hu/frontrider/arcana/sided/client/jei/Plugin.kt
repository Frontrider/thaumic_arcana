package hu.frontrider.arcana.sided.client.jei

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.blocks.production.tile.TileArcaneSieve
import hu.frontrider.arcana.registrationhandlers.BlockRegistry
import hu.frontrider.arcana.sided.client.jei.categories.SieveRecipe
import hu.frontrider.arcana.sided.client.jei.categories.SieveRecipeWrapper
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.ForgeRegistries

@JEIPlugin
class Plugin : IModPlugin {

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        registry.addRecipeCategories(SieveRecipe(registry.jeiHelpers.guiHelper))
    }

    override fun register(registry: IModRegistry) {

        registry.addRecipeCatalyst(ItemStack(BlockRegistry.sieve), "${MODID}_sieve")
        TileArcaneSieve.recipes.forEach { recipe ->
            var input1: ItemStack = ItemStack.EMPTY
            var input2: ItemStack = ItemStack.EMPTY
            var catalyst: ItemStack = ItemStack.EMPTY


            ForgeRegistries.ITEMS.values.forEach {
                if (recipe.isItem1(ItemStack(it))) {
                    input1 = ItemStack(it)
                }
                if (recipe.isItem2(ItemStack(it))) {
                    input2 = ItemStack(it)
                }
                if (recipe.isCatalyst(ItemStack(it))) {
                    catalyst = ItemStack(it)
                }
            }

            if(input1 != ItemStack.EMPTY && input2 != ItemStack.EMPTY && catalyst != ItemStack.EMPTY)
            registry.addRecipes(mutableListOf(SieveRecipeWrapper(recipe, input1, input2, catalyst)) as Collection<*>)
        }
    }
}
