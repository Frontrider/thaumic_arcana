package hu.frontrider.arcana.creatureenchant.effect

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.creatureenchant.EffectEnchantBase
import net.minecraft.init.MobEffects
import net.minecraft.util.ResourceLocation
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

class HasteEnchant : EffectEnchantBase(
        MobEffects.HASTE,
        MobEffects.MINING_FATIGUE,
        1.0,
        ResourceLocation(MODID, "haste")
) {
    override val research: String
        get() = "CREATURE_ENCHANT_ADVANCED2"

    override fun formula(): AspectList {
        return AspectList().merge(Aspect.FLIGHT,200)
                .merge(Aspect.EARTH,100)
                .merge(Aspect.MOTION,50)
                .merge(Aspect.CRAFT,20)
                .merge(Aspect.ORDER,50)
    }
}