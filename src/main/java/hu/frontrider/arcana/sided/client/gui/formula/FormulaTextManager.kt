package hu.frontrider.arcana.sided.client.gui.formula

import hu.frontrider.arcana.sided.client.gui.formula.text.EnchantText
import hu.frontrider.arcana.sided.client.gui.formula.text.FormulaText
import hu.frontrider.arcana.sided.client.gui.formula.text.ModifierText

import hu.frontrider.arcana.sided.client.gui.formula.text.RecipeText
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import java.util.LinkedList


/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
object FormulaTextManager {
    private val texts: MutableList<FormulaText>

    init {
        texts = LinkedList()
    }

    fun addText(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        if (!stack.hasTagCompound())
            return
        val tagCompound = stack.tagCompound!!
        for (formulaText in texts) {
            formulaText.addText(stack, tagCompound, worldIn, tooltip)
        }

    }

    fun registerFormulaTexts() {
        texts.add(EnchantText())
        texts.add(ModifierText())
        texts.add(RecipeText())
    }
}
