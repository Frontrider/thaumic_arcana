package hu.frontrider.core.util

import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object BlockHelper {

    fun isPowered(world: World, pos: BlockPos): Boolean {
        return world.isBlockIndirectlyGettingPowered(pos) > 0 || world.isBlockPowered(pos)
    }

    fun getFacing(p_getFacing_0_: Int): EnumFacing? {
        val i = p_getFacing_0_ and 7
        return if (i > 5) null else EnumFacing.getFront(i)
    }

}
