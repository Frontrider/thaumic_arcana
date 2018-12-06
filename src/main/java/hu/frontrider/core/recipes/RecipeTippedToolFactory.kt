package hu.frontrider.core.recipes

import com.google.gson.JsonObject
import net.minecraft.item.crafting.IRecipe
import net.minecraftforge.common.crafting.IRecipeFactory
import net.minecraftforge.common.crafting.JsonContext

class RecipeTippedToolFactory:IRecipeFactory {
    override fun parse(p0: JsonContext?, p1: JsonObject?): IRecipe {
        return RecipeTippedTool()
    }
}