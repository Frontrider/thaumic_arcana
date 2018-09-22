package hu.frontrider.arcana.content.blocks.plants.magictree

import net.minecraft.block.BlockBush
import net.minecraft.block.BlockPlanks
import net.minecraft.block.IGrowable
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraft.world.gen.feature.WorldGenAbstractTree
import java.util.*

class MagicTreeSapling(val generator:WorldGenAbstractTree): BlockBush(),IGrowable {

    val STAGE = PropertyInteger.create("stage", 0, 1)
    protected val SAPLING_AABB = AxisAlignedBB(0.09999999403953552, 0.0, 0.09999999403953552, 0.8999999761581421, 0.800000011920929, 0.8999999761581421)

    init{
    }

    override fun getBlockState(): BlockStateContainer {
        return BlockStateContainer(this,STAGE)
    }

    override fun canUseBonemeal(worldIn: World, rand: Random, pos: BlockPos, state: IBlockState): Boolean {
        return worldIn.rand.nextFloat().toDouble() < 0.45
    }

    override fun grow(worldIn: World, rand: Random, pos: BlockPos, state: IBlockState) {
        generator.generate(worldIn,rand,pos)
    }

    override fun canGrow(worldIn: World, pos: BlockPos, state: IBlockState, isClient: Boolean): Boolean {
        return true
    }

    override fun isReplaceable(worldIn: IBlockAccess, pos: BlockPos): Boolean {
        return true
    }

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return SAPLING_AABB
    }


    /**
     * Convert the given metadata into a BlockState for this Block
     */
    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState.withProperty(STAGE, Integer.valueOf(meta))
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(STAGE) as Int
    }

}