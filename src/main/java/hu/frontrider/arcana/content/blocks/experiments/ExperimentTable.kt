package hu.frontrider.arcana.content.blocks.experiments

import hu.frontrider.arcana.ThaumicArcana
import hu.frontrider.arcana.content.blocks.BlockCage
import hu.frontrider.arcana.content.items.Rodent
import hu.frontrider.arcana.sided.client.gui.GuiHandler
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import java.util.Random

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.content.blocks.experiments.tiles.TileEntityExperimentTable
import net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY

class ExperimentTable : BlockCage<TileEntityExperimentTable>(Material.WOOD, "experiment_table") {


    override val tileEntityClass: Class<TileEntityExperimentTable>
        get() = TileEntityExperimentTable::class.java

    init {
        setHardness(3f)
        tickRandomly = true
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    override fun getStateFromMeta(meta: Int): IBlockState {
        var enumfacing = EnumFacing.getFront(meta)

        if (enumfacing.axis == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH
        }

        return this.defaultState.withProperty(FACING, enumfacing)
    }

    override fun onBlockActivated(worldIn: World?, pos: BlockPos?, state: IBlockState?, playerIn: EntityPlayer?, hand: EnumHand?, facing: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float): Boolean {


        val itemMainhand = playerIn!!.heldItemMainhand
        val itemOffhand = playerIn.heldItemOffhand


        if (!playerIn.isSneaking) {
            if (state!!.getValue(FACING).rotateYCCW() == facing) {
                playerIn.openGui(ThaumicArcana, GuiHandler.EXPERIMENT_TABLE_CAGE, worldIn, pos!!.x, pos.y, pos.z)
                return true
            }
        }

        if (!worldIn!!.isRemote)
            return true

        val itemMainhandItem = itemMainhand.item
        val item = itemOffhand.item

        if (itemMainhandItem === Items.GLASS_BOTTLE && item === sal_mundi) {
            itemMainhand.shrink(1)
            itemOffhand.shrink(1)
            playerIn.addItemStackToInventory(ItemStack(formula!!, 1))

            return true
        }

        return false
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).index
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    override fun withRotation(state: IBlockState, rot: Rotation?): IBlockState {
        return state.withProperty(FACING, rot!!.rotate(state.getValue(FACING)))
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    override fun withMirror(state: IBlockState, mirrorIn: Mirror?): IBlockState {
        return state.withRotation(mirrorIn!!.toRotation(state.getValue(FACING)))
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING)
    }

    override fun getRenderType(state: IBlockState?): EnumBlockRenderType {
        return EnumBlockRenderType.MODEL
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

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    override fun onBlockAdded(worldIn: World?, pos: BlockPos?, state: IBlockState?) {
        this.setDefaultFacing(worldIn!!, pos, state)
    }

    private fun setDefaultFacing(worldIn: World, pos: BlockPos?, state: IBlockState?) {
        if (!worldIn.isRemote) {
            val iblockstate = worldIn.getBlockState(pos!!.north())
            val iblockstate1 = worldIn.getBlockState(pos.south())
            val iblockstate2 = worldIn.getBlockState(pos.west())
            val iblockstate3 = worldIn.getBlockState(pos.east())
            var enumfacing = state!!.getValue(FACING)

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock && !iblockstate1.isFullBlock) {
                enumfacing = EnumFacing.SOUTH
            } else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock && !iblockstate.isFullBlock) {
                enumfacing = EnumFacing.NORTH
            } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock && !iblockstate3.isFullBlock) {
                enumfacing = EnumFacing.EAST
            } else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock && !iblockstate2.isFullBlock) {
                enumfacing = EnumFacing.WEST
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2)
        }
    }

    override fun onBlockPlacedBy(worldIn: World?, pos: BlockPos?, state: IBlockState?, placer: EntityLivingBase?, stack: ItemStack?) {
        worldIn!!.setBlockState(pos!!, state!!.withProperty(FACING, placer!!.horizontalFacing), 2)
    }

    override fun createTileEntity(world: World, state: IBlockState): TileEntityExperimentTable? {
        return TileEntityExperimentTable()
    }

    override fun randomTick(worldIn: World, pos: BlockPos, state: IBlockState, random: Random) {
        val tileEntity = getTileEntity(worldIn, pos)

        val capability = tileEntity.getCapability(ITEM_HANDLER_CAPABILITY, null)!!
        val rat = capability.getStackInSlot(0)
        if (rat.count == 1) {
            val food = capability.getStackInSlot(1)
            if (food.count > 0) {
                if (!Rodent.isDead(rat, worldIn)) {
                    food.shrink(1)
                    val tagCompound = rat.tagCompound
                    tagCompound!!.setLong("lastFed", worldIn.totalWorldTime)
                }
            }
        }
    }

    companion object {
        val FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL)

        @GameRegistry.ObjectHolder("minecraft:water_bucket")
        private val water_bucket: Item? = null

        @GameRegistry.ObjectHolder("minecraft:bucket")
        private val bucket: Item? = null

        @GameRegistry.ObjectHolder("$MODID:formula")
        var formula: Item? = null

        @GameRegistry.ObjectHolder("thaumcraft:salis_mundus")
        private val sal_mundi: Item? = null
    }
}
