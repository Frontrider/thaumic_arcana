package hu.frontrider.arcana.core.eventhandlers

import hu.frontrider.arcana.TAConfig
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.capabilities.creatureenchant.CreatureEnchantProvider

class LifecycleEventManager {

    @SubscribeEvent
    fun onPlayerClone(event: PlayerEvent.Clone) {
        val original = event.original

        if (event.isWasDeath)
            return

        if (original.hasCapability(CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY, null)) {
            val player = event.entityPlayer
            val creatureEnchant = player.getCapability(CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY, null)
            val oldCreatureEnchant = original.getCapability(CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY, null)
            creatureEnchant!!.store = oldCreatureEnchant!!.store
        }
    }

    @SubscribeEvent
    fun attachCapability(event: AttachCapabilitiesEvent<Entity>) {
        val `object` = event.getObject()
        if (`object` is EntityLiving) {

            for (entry in TAConfig.entityBlacklist) {
                val entityEntry = ForgeRegistries.ENTITIES.getValue(ResourceLocation(entry))
                if (entityEntry != null) {
                    if (entityEntry.entityClass == `object`.javaClass)
                        return
                }
            }

            event.addCapability(CREATURE_ENCHANT, CreatureEnchantProvider())
        }
        if (`object` is EntityPlayer) {
            event.addCapability(CREATURE_ENCHANT, CreatureEnchantProvider())
        }
    }

    companion object {

        private val CREATURE_ENCHANT = ResourceLocation(MODID, "creature_enchant")
    }

}
