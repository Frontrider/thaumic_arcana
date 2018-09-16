package hu.frontrider.arcana.sided.client.jei

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.formulacrafting.FormulaApplicationRecipe
import hu.frontrider.arcana.core.formulacrafting.FormulaRecipes
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.ISubtypeRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry
import java.rmi.server.UID



@JEIPlugin
class ThaumicArcanaJEIPlugin: IModPlugin {

    companion object {
        @GameRegistry.ObjectHolder(MODID+":formula")
        lateinit var formula: Item
    }

    override fun registerItemSubtypes(subtypeRegistry: ISubtypeRegistry) {
            subtypeRegistry.registerSubtypeInterpreter(formula) {
                if(it.hasTagCompound()) {
                    it.tagCompound!!.toString()
                }else ""
            }
    }

    override fun register(registry: IModRegistry) {
        registry.addRecipeCategoryCraftingItem(ItemStack(formula), ApplicationRecipeCategory.UID)

        registry.handleRecipes(FormulaApplicationRecipe::class.java,{ApplicationRecipeWrapper(it)}, ApplicationRecipeCategory.UID)

        registry.addRecipes(FormulaRecipes.getRecipesCollection(),ApplicationRecipeCategory.UID)
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {


        registry.addRecipeCategories(
                ApplicationRecipeCategory()
        )
    }
}