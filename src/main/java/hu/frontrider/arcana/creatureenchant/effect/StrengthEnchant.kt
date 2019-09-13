package hu.frontrider.arcana.creatureenchant.effect

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.creatureenchant.CreatureEnchant
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

class StrengthEnchant : CreatureEnchant(ResourceLocation(MODID, "strength"), "strength") {

    override val research: String
        get() = "CREATURE_ENCHANT_ADVANCED"

    @SubscribeEvent
    fun hurt(event: LivingHurtEvent) {
        val trueSource = event.source.trueSource
        if (trueSource is EntityLivingBase) {
            val level = this.getEnchantLevel(trueSource, this)
            if (level != 0) {
                event.amount += level
            }
        }
    }

    override fun formula(): AspectList {
        return AspectList()
                .merge(Aspect.LIFE, 20)
                .merge(Aspect.ENERGY, 200)
                .merge(Aspect.FIRE, 50)
                .merge(Aspect.ORDER, 300)
                .merge(Aspect.MAGIC, 50)
    }
}
