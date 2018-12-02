package hu.frontrider.core.util.factory


import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

object ItemFactory {
    var modid ="minecraft"
    var creativeTabs =CreativeTabs.BUILDING_BLOCKS
    class ItemFactoryHelper(val base: Item){
        private lateinit var resourceLocation: ResourceLocation

        fun setResourourceLocation(location: ResourceLocation): ItemFactoryHelper {
            resourceLocation = location
            return this
        }

        fun setResourourceLocation(name:String): ItemFactoryHelper {
            resourceLocation = ResourceLocation(modid,name)
            return this
        }

        fun build(): Item {
            base.registryName = resourceLocation
            base.unlocalizedName = "$modid.${resourceLocation.resourcePath}"
            base.setCreativeTab(creativeTabs)
            return base
        }
    }

    fun start(base: Item): ItemFactoryHelper {
        return ItemFactoryHelper(base)
    }
}