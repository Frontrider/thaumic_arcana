package hu.frontrider.arcana.eventhandlers

import net.minecraft.entity.EntityLiving
import net.minecraft.init.MobEffects.UNLUCK
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class UnluckHandler {

    @SubscribeEvent
    fun damage(event:LivingHurtEvent){

        val trueSource = event.source.trueSource
        if (trueSource is EntityLiving) {
            val unluck = trueSource.getActivePotionEffect(UNLUCK)
            if(unluck != null){
                val amplifier = unluck.amplifier
                if(trueSource.world.rand.nextBoolean())
                    event.amount /=amplifier
            }
        }
        val unluck = event.entityLiving.getActivePotionEffect(UNLUCK)
        if(unluck != null){
            val amplifier = unluck.amplifier
            if(event.entityLiving.world.rand.nextBoolean())
                event.amount *=amplifier
        }
    }

}