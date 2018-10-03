package hu.frontrider.arcana.util

import hu.frontrider.arcana.ThaumicArcana
import hu.frontrider.arcana.ThaumicArcana.MODID
import net.minecraft.block.Block
import net.minecraft.util.ResourceLocation

object BlockFactory {

    class BlockFactoryHelper(val base:Block){
        private lateinit var resourceLocation:ResourceLocation

        fun setResourourceLocation(location:ResourceLocation): BlockFactoryHelper {
            resourceLocation = location
            return this
        }

        fun setResourourceLocation(name:String): BlockFactoryHelper {
            resourceLocation = ResourceLocation(MODID,name)
            return this
        }

        fun build(): Block {
            base.registryName = resourceLocation
            base.unlocalizedName = "${ThaumicArcana.MODID}.${resourceLocation.resourcePath}"
            base.setCreativeTab(ThaumicArcana.TABARCANA)
            return base
        }
    }

    fun start(base: Block): BlockFactoryHelper {
        return BlockFactoryHelper(base)
    }

}