package hu.frontrider.arcana.content.items

import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.ThaumicArcana.TABARCANA

open class ItemBase : Item {

    internal constructor(name: String) {
        setRegistryName(MODID, name)
        unlocalizedName = "$MODID.$name"
        setCreativeTab(TABARCANA)
    }

    internal constructor(resourceLocation: ResourceLocation) {
        registryName = resourceLocation
        unlocalizedName = MODID + "." + resourceLocation.resourcePath
        setCreativeTab(TABARCANA)
    }
}
