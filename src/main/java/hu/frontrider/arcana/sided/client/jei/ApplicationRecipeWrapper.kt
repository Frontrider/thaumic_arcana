package hu.frontrider.arcana.sided.client.jei

import hu.frontrider.arcana.core.formulacrafting.FormulaApplicationRecipe
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.BlankRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack



class ApplicationRecipeWrapper(val recipe:FormulaApplicationRecipe) : BlankRecipeWrapper() {

    private val input: List<List<ItemStack>>
    private val output: ItemStack
    init{
        val tmpInput = mutableListOf<ItemStack>()
        tmpInput.add(ItemStack(recipe.block,1))
        if(recipe.required != null){
            tmpInput.add(recipe.required!!)
        }
        output = recipe.result!!
        input = listOf(tmpInput)
    }

    override fun getIngredients(p0: IIngredients) {
        p0.setInputLists(ItemStack::class.java,input)
        p0.setOutput(ItemStack::class.java,output)
    }

    override fun drawInfo(minecraft: Minecraft, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {}
}