package hu.frontrider.arcana.recipes.formulacrafting

import com.google.gson.Gson
import hu.frontrider.arcana.ThaumicArcana
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.Loader
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import java.io.File
import java.util.*
import java.util.stream.Stream

object FormulaRecipeLoader {

    class RecipePlaceholder {
        lateinit var research: String
        lateinit var location: String
        lateinit var result: String
        var required_item: String? = null
        lateinit var block: String
        var consume_block = false
        var consume_formula = true
        var vis = 1
        var pollution = 0
        lateinit var formula: Array<FormulaNotation>

        class FormulaNotation {
            lateinit var name: String
            var amount = 0
        }

        fun getAspects(): AspectList {
            val aspectList = AspectList()
            formula.forEach {
                val aspect = Aspect.getAspect(it.name)
                aspectList.merge(aspect, it.amount)
            }
            return aspectList
        }
    }

    fun load() {
        val file = File(ThaumicArcana.ConfigDirectory.absolutePath + "/formulaRecipes/")
        file.mkdirs()
        val recipes = file.listFiles()
        val gson = Gson()
        Stream.concat(
                Loader.instance().activeModList.stream()
                        .map {
                            it.modId
                        }.map {
                            getResourceFolderFiles("assets/$it/ta_formula_crafting")
                        }.flatMap {
                            it.stream()
                        },
                Arrays.stream(recipes)
        ).forEach {

            if (!it.name.startsWith("_")) {

                val json = it.readText()
                val recipe = gson.fromJson(json, RecipePlaceholder::class.java)
                val applicationRecipe = FormulaApplicationRecipe(
                        research = recipe.research,
                        blockResource = ResourceLocation(recipe.block),
                        formula = recipe.getAspects(),
                        itemResource = recipe.required_item,
                        resultResource = recipe.result,
                        vis = recipe.vis,
                        pollution = recipe.pollution,
                        consumeFormula = recipe.consume_formula,
                        consumeBlock = recipe.consume_block
                )
                applicationRecipe.location = ResourceLocation(recipe.location)
                FormulaRecipes.addRecipe(applicationRecipe)
            }
        }
    }


    private fun getResourceFolderFiles(folder: String): List<File> {
        val loader = Thread.currentThread().contextClassLoader
        val url = loader.getResource(folder)

        return if (url != null) {
            val path = url.path
            File(path).listFiles().toList()
        } else
            emptyList()
    }

}
