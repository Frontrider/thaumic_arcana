package hu.frontrider.arcana.sided.client.jei

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.util.strings.formatTranslate
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeCategory

class ApplicationRecipeCategory: IRecipeCategory<ApplicationRecipeWrapper> {
    override fun getUid(): String {
        return "thaumic_arcana.formula"
    }

    override fun getModName(): String {
        return MODID
    }

    override fun setRecipe(p0: IRecipeLayout, p1: ApplicationRecipeWrapper, p2: IIngredients) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBackground(): IDrawable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTitle(): String {
        return formatTranslate("thaumic_arcana.jei.formulacrafting.title")
    }
}