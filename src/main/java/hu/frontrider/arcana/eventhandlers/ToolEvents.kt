package hu.frontrider.arcana.eventhandlers

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.util.IInfuseable
import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.MobEffects
import net.minecraft.item.Item
import net.minecraft.potion.PotionEffect
import net.minecraft.potion.PotionUtils
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.items.CapabilityItemHandler

class ToolEvents {

    companion object {
        @GameRegistry.ObjectHolder("$MODID:revival_capsule")
        lateinit var revivalCapsule: Item
    }

    @SubscribeEvent
    fun toolPotionAttackEvent(event: LivingHurtEvent) {
        val trueSource = event.source.trueSource
        val entity = event.entity
        if (trueSource != null) {
            if (trueSource is EntityLivingBase && entity is EntityLivingBase) {
                if (trueSource.heldItemMainhand.item is IInfuseable) {
                    val effectsFromStack = PotionUtils.getEffectsFromStack(trueSource.heldItemMainhand)
                    val newEffects = ArrayList<PotionEffect>()

                    effectsFromStack.forEach {
                        val duration = it.duration - 120
                        val potionEffect = if (duration > 0) {
                            newEffects.add(PotionEffect(it.potion, duration, it.amplifier))
                            PotionEffect(it.potion, 120, it.amplifier)
                        } else {
                            PotionEffect(it.potion, 120 + duration, it.amplifier)
                        }
                        entity.addPotionEffect(potionEffect)
                    }
                    PotionUtils.appendEffects(trueSource.heldItemMainhand, newEffects)
                }
            }
        }
    }

    @SubscribeEvent
    fun revivalCapsuleEvent(event: LivingHurtEvent) {
        event.apply {
            if (entityLiving.health > amount)
                return
            if (!entityLiving.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null))
                return

            val capability = entityLiving.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)!!
            for(i in 0 until capability.slots){
                val stackInSlot = capability.getStackInSlot(i)
                if(stackInSlot.item == revivalCapsule){
                    capability.extractItem(i,1,false)
                    entityLiving.apply{
                        addPotionEffect(PotionEffect(MobEffects.REGENERATION,500,3))
                        addPotionEffect(PotionEffect(MobEffects.RESISTANCE,100,3))
                        addPotionEffect(PotionEffect(MobEffects.WATER_BREATHING,300,1))
                    }
                }
            }
        }
    }
}