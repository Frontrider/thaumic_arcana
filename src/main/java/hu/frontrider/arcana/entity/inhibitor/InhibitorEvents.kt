package hu.frontrider.arcana.entity.inhibitor

import hu.frontrider.arcana.capabilities.inhibitor.InhibitorProvider
import net.minecraft.entity.EntityCreature
import net.minecraft.entity.EntityLiving
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class InhibitorEvents {

    @SubscribeEvent
    fun joinWorld(event: EntityJoinWorldEvent) {
        event.entity.apply {
            if (this is EntityCreature) {
                if (hasCapability(InhibitorProvider.INHIBITOR_CAPABILITY, null)) {
                    val capability = getCapability(InhibitorProvider.INHIBITOR_CAPABILITY, null)!!
                    if (capability.inhibited) {
                        this.tasks.taskEntries.clear()
                    }
                    capability.aiList.forEach {
                        this.tasks.addTask(it.priority,it.get(this))
                    }
                }
            }
        }
    }
}