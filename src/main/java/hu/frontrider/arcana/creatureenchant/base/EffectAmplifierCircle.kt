package hu.frontrider.arcana.creatureenchant.base

import net.minecraft.entity.EntityLivingBase
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.creatureenchant.EffectEnchantBase
import hu.frontrider.arcana.creatureenchant.EnchantingBaseCircle

class EffectAmplifierCircle : EnchantingBaseCircle("effect_amplifier") {

    override val color = Color(0, 173, 75, 255)

    override val formula = AspectList()
            .merge(Aspect.WATER, 30)
            .merge(Aspect.ALCHEMY, 300)
            .merge(Aspect.MAGIC, 10)
            .merge(Aspect.ENERGY, 200)

    override val research ="ENCHANT_EFFECT_AMPLIFICATION"


    init {
        setRegistryName(MODID, "effect_amplifier")
    }

    override fun doEffect(multiplier: Int, entityLiving: EntityLivingBase, enchant: CreatureEnchant): Int {
        return if (enchant is EffectEnchantBase)
            multiplier +1
        else
            multiplier

    }
}
