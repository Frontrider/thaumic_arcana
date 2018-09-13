package hu.frontrider.arcana.content.creatureenchant.base

import net.minecraft.entity.EntityLivingBase
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle

class NegationCircle : EnchantingBaseCircle("negation") {

    override fun getColor(): Color {
        return color;
    }

    private val color: Color

    override fun getFormula(): AspectList {
        return AspectList()
                .merge(Aspect.BEAST, 300)
                .merge(Aspect.FLUX, 100)
                .merge(Aspect.LIFE, 50)
                .merge(Aspect.ENTROPY, 100)
    }

    override fun getResearch(): String {
     return "ENCHANT_MODIFICATION"
    }

    init {
        setRegistryName(MODID, "negation")
        color = Color(255, 0, 0, 255)
    }

    override fun doEffect(multiplier: Int, entityLiving: EntityLivingBase, enchant: CreatureEnchant): Int {
        return multiplier * -1
    }
}
