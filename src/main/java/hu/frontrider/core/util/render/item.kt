package hu.frontrider.core.util.render

import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

fun Item.getTexture(): ResourceLocation {
    val registryName = this.registryName!!
    val resourcePath = registryName.resourcePath
    return ResourceLocation(registryName.resourceDomain,"textures/items/$resourcePath.png")
}