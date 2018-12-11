package hu.frontrider.arcana.capabilities.inhibitor

import hu.frontrider.arcana.entity.ai.InhibitedAI
import net.minecraft.entity.ai.EntityAIBase

class InhibitorCapability : IInhibitor {
    override var inhibited = false
    override var aiList: List<InhibitedAI> = listOf()
}
