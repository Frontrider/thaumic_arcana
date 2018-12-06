package hu.frontrider.core.util.factory

import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.ResourceLocation

object BlockFactory {
    var modid ="minecraft"
    var creativeTabs = CreativeTabs.BUILDING_BLOCKS
    class BlockFactoryHelper(val base:Block){
        private lateinit var resourceLocation:ResourceLocation

        fun setResourourceLocation(location:ResourceLocation): BlockFactoryHelper {
            resourceLocation = location
            return this
        }

        fun setResourourceLocation(name:String): BlockFactoryHelper {
            resourceLocation = ResourceLocation(modid,name)
            return this
        }

        fun build(): Block {
            base.registryName = resourceLocation
            base.unlocalizedName = "$modid.${resourceLocation.resourcePath}"
            base.setCreativeTab(creativeTabs)
            return base
        }
    }

    fun start(base: Block): BlockFactoryHelper {
        return BlockFactoryHelper(base)
    }

}