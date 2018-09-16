package hu.frontrider.arcana.content.blocks.experiments

import hu.frontrider.arcana.ThaumicArcana
import hu.frontrider.arcana.content.blocks.BlockCage
import hu.frontrider.arcana.content.blocks.experiments.tiles.TileEntityArcaneCage
import hu.frontrider.arcana.content.blocks.experiments.tiles.logic.ArcaneCageAreaScan
import hu.frontrider.arcana.sided.client.gui.GuiHandler
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.Random

class BlockArcaneCage : BlockCage<TileEntityArcaneCage>(Material.ROCK, "arcane_cage") {

    internal var arcaneCageAreaScan = ArcaneCageAreaScan()

    override val tileEntityClass: Class<TileEntityArcaneCage>
        get() = TileEntityArcaneCage::class.java

    init {
        setHardness(3f)
        tickRandomly = true
    }

    override fun createTileEntity(world: World, state: IBlockState): TileEntityArcaneCage? {
        return TileEntityArcaneCage()
    }


    override fun randomTick(worldIn: World, pos: BlockPos, state: IBlockState, random: Random) {
        val tileEntity = getTileEntity(worldIn, pos)

        if (!state.getValue(CAPTURED) && tileEntity.randomTick(worldIn) && arcaneCageAreaScan.isvalid(worldIn, pos)) {
            worldIn.setBlockState(pos, state.withProperty(CAPTURED, true), 2)
        }
    }

    override fun onBlockActivated(worldIn: World?, pos: BlockPos?, state: IBlockState?, playerIn: EntityPlayer?, hand: EnumHand?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if(!worldIn!!.isRemote)
            playerIn!!.openGui(ThaumicArcana, GuiHandler.ARCANE_CAGE, worldIn!!, pos!!.x, pos.y, pos.z)
        return true
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, CAPTURED)
    }

    override fun onBlockAdded(worldIn: World?, pos: BlockPos?, state: IBlockState?) {
        worldIn!!.setBlockState(pos!!, state!!.withProperty(CAPTURED, false), 2)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState.withProperty(CAPTURED, meta > 0)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return if (state.getValue(CAPTURED)) 1 else 0
    }


    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    override fun isFullCube(state: IBlockState?): Boolean {
        return false
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    override fun isOpaqueCube(state: IBlockState?): Boolean {
        return false
    }

    companion object {

        val CAPTURED = PropertyBool.create("captured")
    }


}
