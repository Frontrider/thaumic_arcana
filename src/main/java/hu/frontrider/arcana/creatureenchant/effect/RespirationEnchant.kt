package hu.frontrider.arcana.creatureenchant.effect

import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.creatureenchant.CreatureEnchant

class RespirationEnchant : CreatureEnchant(ResourceLocation(MODID, "respiration"), "respiration") {
    override val research: String
        get() = "CREATURE_ENCHANT"


    @SubscribeEvent
    fun handleEvent(event: LivingEvent.LivingUpdateEvent) {
        val entity = event.entityLiving
        if (entity.isInWater) {
            val enchantLevel = getEnchantLevel(entity, this)
            if (enchantLevel > 0) {
                entity.air = 300
            }
        }
    }

    override fun formula(): AspectList {
        return AspectList()
                .merge(Aspect.AIR, 200)
                .merge(Aspect.WATER, 50)
                .merge(Aspect.LIFE, 5)
                .merge(Aspect.ORDER, 10)
                .merge(Aspect.SENSES, 20)
                .merge(Aspect.MAGIC, 80)
    }
}
