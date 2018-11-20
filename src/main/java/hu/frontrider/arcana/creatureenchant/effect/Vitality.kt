package hu.frontrider.arcana.creatureenchant.effect

import hu.frontrider.arcana.creatureenchant.EffectEnchantBase
import net.minecraft.init.MobEffects
import net.minecraft.util.ResourceLocation
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID

class Vitality : EffectEnchantBase(MobEffects.REGENERATION, MobEffects.WITHER, -2.0, ResourceLocation(MODID, "vitality"), "vitality") {


    override val research: String
        get() = "CREATURE_ENCHANT_ADVANCED"


    override fun formula(): AspectList {
        return AspectList()
                .merge(Aspect.SENSES, 20)
                .merge(Aspect.LIFE, 100)
                .merge(Aspect.MAGIC, 20)
    }

}
