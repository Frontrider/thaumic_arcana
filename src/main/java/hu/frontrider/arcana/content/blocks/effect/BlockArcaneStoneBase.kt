package hu.frontrider.arcana.content.blocks.effect

import hu.frontrider.arcana.content.blocks.BlockBase
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class BlockArcaneStoneBase(name:String):BlockBase(Material.ROCK,name){

    override fun onEntityWalk(worldIn: World, pos: BlockPos, entityIn: Entity) {
        activate(worldIn,pos,entityIn)
    }

    abstract fun activate(worldIn: World,pos:BlockPos,entityIn: Entity)
}