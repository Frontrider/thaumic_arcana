package hu.frontrider.arcana.eventhandlers

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
import hu.frontrider.arcana.capabilities.creatureenchant.CreatureEnchantProvider
import hu.frontrider.arcana.capabilities.inhibitor.InhibitorProvider
import hu.frontrider.arcana.capabilities.scar.ScarProvider
import hu.frontrider.arcana.entity.ai.peopleList

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
                    if (entityEntry.entityClass != `object`.javaClass) {
                        event.addCapability(CREATURE_ENCHANT, CreatureEnchantProvider())


                    }
                }
            }
            var isPeople = false;
            for (entry in peopleList) {
                val entityEntry = ForgeRegistries.ENTITIES.getValue(ResourceLocation(entry))
                if (entityEntry != null) {
                    isPeople = isPeople && entityEntry.entityClass == `object`.javaClass
                }
            }
            if(!isPeople)
                event.addCapability(INHIBITOR, InhibitorProvider())

        }
        if (`object` is EntityPlayer) {
            event.addCapability(CREATURE_ENCHANT, CreatureEnchantProvider())
            event.addCapability(SCAR, ScarProvider())
        }
    }

    companion object {

        private val CREATURE_ENCHANT = ResourceLocation(MODID, "creature_enchant")
        private val SCAR = ResourceLocation(MODID, "scar")
        private val INHIBITOR = ResourceLocation(MODID, "inhibitor")

    }

}
