package hu.frontrider.arcana.core.formulacrafting

import net.minecraft.block.Block
import java.util.*

object FormulaRecipes {

    val recipes: MutableMap<Block, List<FormulaApplicationRecipe>> = LinkedHashMap()

    fun addRecipe(recipe: FormulaApplicationRecipe) {
        if (recipes.containsKey(recipe.block)) {
            recipes[recipe.block]!!.plus(recipe)
        } else {
            val formulaApplicationRecipeArrayList = LinkedList<FormulaApplicationRecipe>()
            recipes[recipe.block] = formulaApplicationRecipeArrayList
        }
    }

    fun getRecipesCollection(): Collection<List<FormulaApplicationRecipe>> {
        return recipes.values
    }

    fun init(){

    }
}
