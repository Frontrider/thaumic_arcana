package hu.frontrider.arcana.content.blocks.plants.magictree

import net.minecraft.block.BlockBush
import net.minecraft.block.BlockPlanks
import net.minecraft.block.IGrowable
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraft.world.gen.feature.WorldGenAbstractTree
import java.util.*

class MagicTreeSapling(val generator:WorldGenAbstractTree): BlockBush(Material.LEAVES),IGrowable {

    companion object {
        protected val SAPLING_AABB = AxisAlignedBB(0.09999999403953552, 0.0, 0.09999999403953552, 0.8999999761581421, 0.800000011920929, 0.8999999761581421)
    }


    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this)
    }

    override fun canUseBonemeal(worldIn: World, rand: Random, pos: BlockPos, state: IBlockState): Boolean {
        return worldIn.rand.nextFloat().toDouble() < 0.45
    }

    override fun updateTick(worldIn: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand)

            if (!worldIn.isAreaLoaded(pos, 1)) return  // Forge: prevent loading unloaded chunks when checking neighbor's light
            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(worldIn,rand, pos, state)
            }
        }
    }

    override fun grow(worldIn: World, rand: Random, pos: BlockPos, state: IBlockState) {
        generator.generate(worldIn,rand,pos)
    }

    override fun canGrow(worldIn: World, pos: BlockPos, state: IBlockState, isClient: Boolean): Boolean {
        return true
    }

    override fun isReplaceable(worldIn: IBlockAccess, pos: BlockPos): Boolean {
        return false
    }

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return SAPLING_AABB
    }


}