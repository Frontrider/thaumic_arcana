package hu.frontrider.arcana.sided.client.jei

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.util.strings.formatTranslate
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeCategory
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack

class ApplicationRecipeCategory: IRecipeCategory<ApplicationRecipeWrapper> {
    override fun getBackground(): IDrawable {
        return EmptyDrawable
    }

    companion object {
        val UID ="thaumic_arcana.formula"
    }

    override fun getUid(): String {
        return UID
    }

    override fun getModName(): String {
        return MODID
    }

    override fun setRecipe(recipeLayout: IRecipeLayout, wrapper: ApplicationRecipeWrapper, ingredients: IIngredients) {
        recipeLayout.itemStacks.init(0, true, 64, 52)
        recipeLayout.itemStacks.set(0,ItemStack(wrapper.recipe.block))

        if(wrapper.recipe.required != null) {
            recipeLayout.itemStacks.init(0, true, 64, 20)
            recipeLayout.itemStacks.set(0,wrapper.recipe.required)
        }

        if(wrapper.recipe.required != null) {
            recipeLayout.itemStacks.init(0, true, 64, 10)
            recipeLayout.itemStacks.set(0,wrapper.recipe.result)
        }
    }


    override fun getTitle(): String {
        return formatTranslate("thaumic_arcana.jei.formulacrafting.title")
    }
}

object EmptyDrawable :IDrawable {



    override fun getHeight(): Int {
        return 150
    }

    override fun draw(p0: Minecraft, p1: Int, p2: Int) {

    }

    override fun getWidth(): Int {
        return 110
    }
}