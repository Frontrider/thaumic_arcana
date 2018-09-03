package hu.frontrider.arcana.recipes.formulacrafting

import hu.frontrider.arcana.ThaumicArcana

import java.io.File

object FormulaRecipeLoader {

    fun load() {
        val file = File(ThaumicArcana.ConfigDirectory.absolutePath + "/formulaRecipes/")

        file.mkdirs()

        for (recipeFile in file.walkTopDown()) {


        }

    }

}
