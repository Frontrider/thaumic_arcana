package hu.frontrider.arcana.blocks.plants.magictree

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import thaumcraft.api.aura.AuraHelper
import java.util.*

class AuraLeaves : Block(Material.LEAVES){
    init {
        translucent = true
        needsRandomTick = true
    }

    //if the aura is not at it's base level, remove the leave and add vis to the aura
    override fun randomTick(worldIn: World, pos: BlockPos, state: IBlockState, random: Random) {
        val vis = AuraHelper.getVis(worldIn, pos)
        val auraBase = AuraHelper.getAuraBase(worldIn, pos)
        if(vis < auraBase) {
            AuraHelper.addVis(worldIn,pos,5f)
            worldIn.setBlockState(pos, Blocks.AIR.defaultState)
        }
    }
}