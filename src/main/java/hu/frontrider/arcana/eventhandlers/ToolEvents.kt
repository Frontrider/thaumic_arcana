package hu.frontrider.arcana.eventhandlers

import hu.frontrider.arcana.util.IInfuseable
import net.minecraft.entity.EntityLivingBase
import net.minecraft.potion.PotionEffect
import net.minecraft.potion.PotionUtils
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ToolEvents {

    @SubscribeEvent
    fun entityHurt(event: LivingHurtEvent) {
        val trueSource = event.source.trueSource
        val entity = event.entity
        if (trueSource != null) {
            if (trueSource is EntityLivingBase && entity is EntityLivingBase) {
                if (trueSource.heldItemMainhand.item is IInfuseable) {
                    val effectsFromStack = PotionUtils.getEffectsFromStack(trueSource.heldItemMainhand)
                    val newEffects = ArrayList<PotionEffect>()

                    effectsFromStack.forEach {
                        val duration = it.duration - 120
                        val potionEffect =if(duration >0) {
                            newEffects.add(PotionEffect(it.potion, duration, it.amplifier))
                            PotionEffect(it.potion, 120, it.amplifier)
                        }else{
                            PotionEffect(it.potion, 120+duration, it.amplifier)
                        }
                        entity.addPotionEffect(potionEffect)
                    }

                    PotionUtils.appendEffects(trueSource.heldItemMainhand, newEffects)
                }
            }
        }
    }
}