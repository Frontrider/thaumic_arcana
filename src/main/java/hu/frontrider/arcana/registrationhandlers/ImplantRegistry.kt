package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.capabilities.implants.IImplant
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ImplantRegistry {
    @SubscribeEvent
    fun registryEventCreatureEnchant(event: RegistryEvent.Register<IImplant>) {
        event.registry.registerAll()
    }
}