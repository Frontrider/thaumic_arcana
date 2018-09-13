package hu.frontrider.arcana.content.items.formula

import hu.frontrider.arcana.content.items.formula.text.EnchantText
import hu.frontrider.arcana.content.items.formula.text.FormulaText
import hu.frontrider.arcana.content.items.formula.text.ModifierText

import hu.frontrider.arcana.content.items.formula.text.RecipeText
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import java.util.LinkedList


/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
object FormulaTextManager {
    val texts: MutableList<FormulaText>

    init {
        texts = LinkedList()
    }

    fun addText(stack: ItemStack, worldIn: World?, tooltip: List<String>, flagIn: ITooltipFlag) {
        if (!stack.hasTagCompound())
            return
        val tagCompound = stack.tagCompound
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
