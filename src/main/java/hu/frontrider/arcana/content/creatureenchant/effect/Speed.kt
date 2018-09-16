package hu.frontrider.arcana.content.creatureenchant.effect

import hu.frontrider.arcana.core.creatureenchant.EffectEnchantBase
import net.minecraft.init.MobEffects
import net.minecraft.util.ResourceLocation
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID

class Speed : EffectEnchantBase(MobEffects.SPEED, MobEffects.SLOWNESS, -1.0, ResourceLocation(MODID, "speed"), "speed") {

    override val research: String
        get() = "CREATURE_ENCHANT_ADVANCED"


    override fun formula(): AspectList {
        return AspectList()
                .merge(Aspect.SENSES, 20)
                .merge(Aspect.ENERGY, 100)
                .merge(Aspect.MAGIC, 20)
    }

}
