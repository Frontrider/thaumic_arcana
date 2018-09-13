package hu.frontrider.arcana.content.creatureenchant.effect

import net.minecraft.init.MobEffects
import net.minecraft.util.ResourceLocation
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.creatureenchant.EffectEnchantBase

class ProtectionEnchant : EffectEnchantBase(MobEffects.RESISTANCE, MobEffects.RESISTANCE, 1.0, ResourceLocation(MODID, "protection"), "protection") {
    override val research: String
        get() = "CREATURE_ENCHANT"

    override fun formula(): AspectList {
        return AspectList()
                .merge(Aspect.PROTECT, 100)
                .merge(Aspect.METAL, 60)
                .merge(Aspect.AVERSION, 10)
                .merge(Aspect.CRYSTAL, 3)
                .merge(Aspect.EARTH, 20)
                .merge(Aspect.MAGIC, 70)
    }
}
