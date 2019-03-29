package hu.frontrider.arcana.entity.inhibitor.ai

import hu.frontrider.arcana.api.InhibitorAiWrapper
import net.minecraft.util.ResourceLocation

object AIHolder {
    var storage = HashMap<ResourceLocation, InhibitorAiWrapper>()

    fun register(aiList: List<InhibitorAiWrapper>) {
        aiList.forEach {
            storage[it.id] = it
        }
    }
}