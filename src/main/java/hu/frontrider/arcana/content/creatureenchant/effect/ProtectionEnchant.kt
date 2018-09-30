package hu.frontrider.arcana.content.creatureenchant.effect

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

class ProtectionEnchant : CreatureEnchant(ResourceLocation(MODID, "protection"), "protection") {


    override val research: String
        get() = "CREATURE_ENCHANT"

    @SubscribeEvent
    fun entityHurt(event: LivingHurtEvent) {
        val entity = event.entityLiving

        val enchantLevel = this.getEnchantLevel(entity, this)
        if (enchantLevel != 0) {
            if (enchantLevel > 0)
                event.amount = event.amount / (enchantLevel/2)
            if (enchantLevel < 0)
                event.amount = event.amount * (enchantLevel/2)
        }
    }

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
