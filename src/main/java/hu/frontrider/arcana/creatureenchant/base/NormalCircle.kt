package hu.frontrider.arcana.creatureenchant.base

import net.minecraft.entity.EntityLivingBase
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.creatureenchant.EnchantingBaseCircle

class NormalCircle : EnchantingBaseCircle("normal") {

    override val formula = AspectList()
            .merge(Aspect.BEAST, 300)
            .merge(Aspect.MAGIC, 100)
            .merge(Aspect.LIFE, 50)
            .merge(Aspect.AURA, 100)

    override val color= Color(255, 255, 255, 255)

    override val research = "ENCHANT_MODIFICATION"

    init {
        setRegistryName(MODID, "normal")

    }

    override fun doEffect(multiplier: Int, entityLiving: EntityLivingBase, enchant: CreatureEnchant): Int {
        return multiplier
    }
}
