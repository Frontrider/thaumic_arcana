package hu.frontrider.arcana.content.creatureenchant.base

import net.minecraft.entity.EntityLivingBase
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle

class NormalCircle : EnchantingBaseCircle("normal") {
    override fun getColor(): Color {
        return color
    }

    override fun getFormula(): AspectList {
        return AspectList()
                .merge(Aspect.BEAST, 300)
                .merge(Aspect.MAGIC, 100)
                .merge(Aspect.LIFE, 50)
                .merge(Aspect.AURA, 100)
    }

    override fun getResearch(): String {
        return "ENCHANT_MODIFICATION"
    }

    private val color :Color
    init {
        setRegistryName(MODID, "normal")
        color = Color(255, 255, 255, 255)
    }

    override fun doEffect(multiplier: Int, entityLiving: EntityLivingBase, enchant: CreatureEnchant): Int {
        return multiplier
    }
}
