package hu.frontrider.arcana.content.creatureenchant.base

import net.minecraft.entity.EntityLivingBase
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle

class NegationCircle : EnchantingBaseCircle("negation") {


    override val color= Color(255, 0, 0, 255)

    override val formula =AspectList()
            .merge(Aspect.BEAST, 300)
            .merge(Aspect.FLUX, 100)
            .merge(Aspect.LIFE, 50)
            .merge(Aspect.ENTROPY, 100)

    override val research = "ENCHANT_MODIFICATION"

    init {
        setRegistryName(MODID, "negation")
    }

    override fun doEffect(multiplier: Int, entityLiving: EntityLivingBase, enchant: CreatureEnchant): Int {
        return multiplier * -1
    }
}
