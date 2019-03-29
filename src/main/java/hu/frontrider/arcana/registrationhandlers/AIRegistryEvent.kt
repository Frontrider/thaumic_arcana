package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.api.InhibitorAIRegistryEvent
import hu.frontrider.arcana.entity.inhibitor.ai.*
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class AIRegistryEvent {
    @SubscribeEvent
    fun register(aiRegistryEvent: InhibitorAIRegistryEvent){
        aiRegistryEvent.register(
                EntityAIAttackMeleeWrapper(3.0),
                EntityAIBreakDoorWrapper(),
                EntityAIWanderWrapper(3.0,10),
                EntityAIPanicWrapper(5.0),
                EntityAIOpenDoorWrapper(),
                EntityAIOpenAndCloseDoorWrapper()
        )
    }
}