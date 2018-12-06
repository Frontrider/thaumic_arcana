package hu.frontrider.arcana.util.items

import hu.frontrider.arcana.ThaumicArcana
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

object ItemFactory {

    class ItemFactoryHelper(val base: Item){
        private lateinit var resourceLocation: ResourceLocation

        fun setResourourceLocation(location: ResourceLocation): ItemFactoryHelper {
            resourceLocation = location
            return this
        }

        fun setResourourceLocation(name:String): ItemFactoryHelper {
            resourceLocation = ResourceLocation(ThaumicArcana.MODID,name)
            return this
        }

        fun build(): Item {
            base.registryName = resourceLocation
            base.unlocalizedName = "${ThaumicArcana.MODID}.${resourceLocation.resourcePath}"
            base.setCreativeTab(ThaumicArcana.TABARCANA)
            return base
        }
    }

    fun start(base: Item): ItemFactoryHelper {
        return ItemFactoryHelper(base)
    }
}