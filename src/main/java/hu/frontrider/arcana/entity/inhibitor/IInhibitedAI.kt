package hu.frontrider.arcana.entity.inhibitor

import hu.frontrider.arcana.api.InhibitorAiWrapper
import net.minecraft.entity.ai.EntityAIBase
import net.minecraft.util.ResourceLocation

interface IInhibitedAI {
    val location:ResourceLocation
}

abstract class InhibitedAI:EntityAIBase(), IInhibitedAI

val aiList= HashMap<ResourceLocation, InhibitorAiWrapper>()

lateinit var peopleList:List<String>