package hu.frontrider.arcana.terramancy

import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object BlockMovers {

    fun move(world: World, pos: BlockPos, direction: EnumFacing): BlockPos {
        if (world.getTileEntity(pos) != null)
            return pos
        else
            if (!world.isAirBlock(pos))
                return pos

        val blockState = world.getBlockState(pos)
        val offset = pos.offset(direction)
        world.setBlockState(offset, blockState)
        world.setBlockToAir(offset)
        return offset
    }
}