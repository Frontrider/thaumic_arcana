package hu.frontrider.arcana.content.items.formula

import hu.frontrider.arcana.content.items.formula.BaseFormula
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

import hu.frontrider.arcana.ThaumicArcana.MODID

class UsedFormula : BaseFormula(ResourceLocation(MODID, "formula_used")) {
    init {
        this.setMaxStackSize(1)
        this.maxDamage = 16
    }

    override fun showDurabilityBar(p_showDurabilityBar_1_: ItemStack): Boolean {
        return true
    }

    override fun getRGBDurabilityForDisplay(p_getRGBDurabilityForDisplay_1_: ItemStack): Int {
        return super.getRGBDurabilityForDisplay(p_getRGBDurabilityForDisplay_1_)
    }
}
