package hu.frontrider.arcana.sided.client.jei.categories

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.api.IArcaneSieveRecipe
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeCategory
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation


class SieveRecipe(guiHelper:IGuiHelper):IRecipeCategory<SieveRecipeWrapper> {

    private val background:IDrawable
    init{
        val backgroundLocation = ResourceLocation(MODID,"textures/gui/jei_arcane_sieve.png")
        background = guiHelper.createDrawable(backgroundLocation, 0, 10, 155, 73)
    }

    override fun getUid(): String {
        return "${MODID}_sieve"
    }

    override fun getModName(): String {
        return MODID
    }

    override fun setRecipe(p0: IRecipeLayout, p1: SieveRecipeWrapper, p2: IIngredients) {

    }

    override fun getBackground(): IDrawable {
            return background
    }

    override fun getTitle(): String {
        return "Arcane Sieve"
    }
}

class SieveRecipeWrapper(val recipe: IArcaneSieveRecipe, val input1:ItemStack,val input2:ItemStack,val catalyst:ItemStack):IRecipeWrapper {
    override fun getIngredients(p0: IIngredients) {
        p0.setInput(VanillaTypes.ITEM, input1)
        p0.setInput(VanillaTypes.ITEM, input2)
        p0.setInput(VanillaTypes.ITEM, catalyst)
        p0.setOutput(VanillaTypes.ITEM,recipe.output)
    }

    override fun drawInfo(minecraft: Minecraft, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {

    }

}
