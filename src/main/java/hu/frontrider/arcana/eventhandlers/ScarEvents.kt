package hu.frontrider.arcana.eventhandlers

import hu.frontrider.arcana.capabilities.scar.ScarProvider
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ScarEvents {

    //@SubscribeEvent
    fun damage(event:LivingHurtEvent){

        val entity = event.entity

        if(entity.hasCapability(ScarProvider.SCARRED_CAPABILITY,null)){
            val capability = entity.getCapability(ScarProvider.SCARRED_CAPABILITY, null)!!
            capability.currentDamage+= event.amount
            capability.limbs.hurt(event.amount/2,entity.world.rand)
        }

    }
}