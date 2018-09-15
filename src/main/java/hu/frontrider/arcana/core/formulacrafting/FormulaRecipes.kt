package hu.frontrider.arcana.core.formulacrafting

import net.minecraft.block.Block
import java.util.*

object FormulaRecipes {

    val recipes: MutableMap<Block, LinkedList<FormulaApplicationRecipe>> = LinkedHashMap()

    fun addRecipe(recipe: FormulaApplicationRecipe) {
        if (recipes.containsKey(recipe.block)) {
            if(recipes[recipe.block] == null)
                recipes[recipe.block] = LinkedList()
            recipes[recipe.block]!!.add(recipe)
        } else {
            val formulaApplicationRecipeArrayList = LinkedList<FormulaApplicationRecipe>()
            formulaApplicationRecipeArrayList.add(recipe)
            recipes[recipe.block] = formulaApplicationRecipeArrayList
        }
    }

    fun getRecipesCollection(): Collection<List<FormulaApplicationRecipe>> {
        return recipes.values
    }

    fun init(){

    }
}
