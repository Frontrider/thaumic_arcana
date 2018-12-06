package hu.frontrider.arcana.items.tools

import com.google.common.collect.Sets
import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.ThaumicArcana.TABARCANA
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.item.*
import thaumcraft.api.blocks.BlocksTC

open class PickAxeBase(name:String, material:ToolMaterial): ItemPickaxe(material) {

    init{
        setRegistryName(MODID,name)
        unlocalizedName = "$MODID.$name"
        creativeTab = TABARCANA
    }
}
//the axe needs a custom class, because it's broken when you extend it
private val AXE_EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE, BlocksTC.logGreatwood,BlocksTC.logSilverwood,BlocksTC.plankGreatwood,BlocksTC.plankSilverwood)

open class AxeBase(name:String,material:ToolMaterial): ItemTool(material, AXE_EFFECTIVE_ON) {

    init{
        setRegistryName(MODID,name)
        unlocalizedName = "$MODID.$name"
        creativeTab = TABARCANA
    }

    override fun canHarvestBlock(blockIn: IBlockState): Boolean {
        return blockIn.block.getMaterial(blockIn) == Material.WOOD
    }
}
open class ShovelBase(name:String,material:ToolMaterial): ItemSpade(material) {

    init{
        setRegistryName(MODID,name)
        unlocalizedName = "$MODID.$name"
        creativeTab = TABARCANA
    }
}
open class SwordBase(name:String,material:ToolMaterial): ItemSword(material) {

    init{
        setRegistryName(MODID,name)
        unlocalizedName = "$MODID.$name"
        creativeTab = TABARCANA
    }
}
open class HoeBase(name:String,material:ToolMaterial): ItemHoe(material) {

    init{
        setRegistryName(MODID,name)
        unlocalizedName = "$MODID.$name"
        creativeTab = TABARCANA
    }
}


