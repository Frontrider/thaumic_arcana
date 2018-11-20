package hu.frontrider.arcana.creatureenchant.effect

import net.minecraft.init.MobEffects
import net.minecraft.util.ResourceLocation
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.creatureenchant.EffectEnchantBase

class StrengthEnchant : EffectEnchantBase(MobEffects.STRENGTH, MobEffects.WEAKNESS, -1.0, ResourceLocation(MODID, "strength"), "strength") {

    override val research: String
        get() = "CREATURE_ENCHANT_ADVANCED"

    override fun formula(): AspectList {
        return AspectList()
                .merge(Aspect.LIFE, 20)
                .merge(Aspect.ENERGY, 200)
                .merge(Aspect.FIRE, 50)
                .merge(Aspect.ORDER, 300)
                .merge(Aspect.MAGIC, 50)
    }
}
