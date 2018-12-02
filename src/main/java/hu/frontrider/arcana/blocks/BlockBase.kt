package hu.frontrider.arcana.blocks

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.ThaumicArcana.TABARCANA
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList

open class BlockBase(materialIn: Material, name: String) : Block(materialIn) {

    init {
        setRegistryName(MODID, name)
        unlocalizedName = "$MODID.$name"
        this.setCreativeTab(TABARCANA)
    }

}
