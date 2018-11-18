package hu.frontrider.arcana.content.research.researchevents

import hu.frontrider.arcana.core.capabilities.scar.ScarProvider
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.DamageSource
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import thaumcraft.api.capabilities.ThaumcraftCapabilities.KNOWLEDGE

class TheScar {

    @SubscribeEvent
    fun damageEvent(event: LivingHurtEvent) {
        val player = event.entity
        if (player is EntityPlayer) {
            val knowledge = player.getCapability(KNOWLEDGE, null)!!
            if (knowledge.isResearchComplete("BIOMANCY_BASICS")) {
                val capability = player.getCapability(ScarProvider.SCARRED_CAPABILITY, null)!!
                capability.currentDamage += event.amount
            }
        }
    }

    @SubscribeEvent
    fun tickEvent(event: LivingEvent.LivingUpdateEvent) {
        val player = event.entity
        if (player is EntityPlayer) {
            val knowledge = player.getCapability(KNOWLEDGE, null)!!
            if (knowledge.isResearchComplete("BIOMANCY_BASICS")) {
                val capability = player.getCapability(ScarProvider.SCARRED_CAPABILITY, null)!!
                if (capability.currentDamage > capability.requiredDamage) {
                    if (player.health > player.maxHealth - capability.severity)
                        player.attackEntityFrom(DamageSource.GENERIC, capability.severity.toFloat())
                }
            }
        }
    }


}