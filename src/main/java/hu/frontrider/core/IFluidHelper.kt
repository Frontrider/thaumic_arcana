package hu.frontrider.core

import net.minecraft.block.state.IBlockState

interface IFluidHelper {
    fun getWaterValue(blockState: IBlockState): Int
}