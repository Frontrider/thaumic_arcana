package hu.frontrider.arcana.capabilities.inhibitor

import hu.frontrider.arcana.api.InhibitorAiWrapper
import hu.frontrider.arcana.entity.inhibitor.InhibitedAI

class InhibitorCapability() : IInhibitor {
    override var inhibited = false
    override var aiList: List<InhibitorAiWrapper> = listOf()

}
