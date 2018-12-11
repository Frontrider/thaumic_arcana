package hu.frontrider.arcana.entity.ai

import net.minecraft.entity.ai.EntityAIBase
import net.minecraft.util.ResourceLocation

interface IInhibitedAI {
    val location:ResourceLocation
}

abstract class InhibitedAI:EntityAIBase(), IInhibitedAI

val aiList= HashMap<ResourceLocation, InhibitedAI>()

lateinit var peopleList:List<String>