package hu.frontrider.core

import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

interface IBlockUpdater {

   fun updateBlockState(world: World, left: BlockPos, right: BlockPos, thizState: IBlockState, thizPos: BlockPos, leftSide: EnumFacing, rightSide: EnumFacing)
}