package hu.frontrider.arcana.blocks.experiments;

import hu.frontrider.arcana.ThaumicArcana;
import hu.frontrider.arcana.blocks.BlockCage;
import hu.frontrider.arcana.blocks.experiments.tiles.TileEntityExperimentTable;
import hu.frontrider.arcana.client.gui.GuiHandler;
import hu.frontrider.arcana.items.Rodent;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.Random;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class ExperimentTable extends BlockCage<TileEntityExperimentTable> {
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    @GameRegistry.ObjectHolder("minecraft:water_bucket")
    private static Item water_bucket = null;

    @GameRegistry.ObjectHolder("minecraft:bucket")
    private static Item bucket = null;

    @GameRegistry.ObjectHolder(MODID + ":formula")
    public static Item formula = null;

    @GameRegistry.ObjectHolder("thaumcraft:salis_mundus")
    private static final Item sal_mundi = null;


    public ExperimentTable() {
        super(Material.WOOD, "experiment_table");
        setHardness(3);
        setTickRandomly(true);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return true;
        ItemStack itemMainhand = playerIn.getHeldItemMainhand();
        ItemStack itemOffhand = playerIn.getHeldItemOffhand();

        if (!playerIn.isSneaking()) {
            if (state.getValue(FACING).rotateYCCW() == facing) {
                playerIn.openGui(ThaumicArcana.INSTANCE, GuiHandler.EXPERIMENT_TABLE_CAGE, worldIn, pos.getX(), pos.getY(), pos.getZ());
                return true;
            }
        }

        Item itemMainhandItem = itemMainhand.getItem();
        Item item = itemOffhand.getItem();
        //noinspection ConstantConditions
        if (itemMainhandItem == Items.GLASS_BOTTLE && item == sal_mundi) {
            itemMainhand.shrink(1);
            itemOffhand.shrink(1);
            playerIn.addItemStackToInventory(new ItemStack(formula, 1));

            return true;
        }

        return false;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()), 2);
    }


    @Override
    public Class<TileEntityExperimentTable> getTileEntityClass() {
        return TileEntityExperimentTable.class;
    }

    @Nullable
    @Override
    public TileEntityExperimentTable createTileEntity(World world, IBlockState state) {
        return new TileEntityExperimentTable();
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        TileEntityExperimentTable tileEntity = getTileEntity(worldIn, pos);

        IItemHandler capability = tileEntity.getCapability(ITEM_HANDLER_CAPABILITY, null);
        assert capability != null;
        ItemStack rat = capability.getStackInSlot(0);
        if (rat.getCount() == 1) {
            ItemStack food = capability.getStackInSlot(1);
            if (food.getCount() > 0) {
                if (!((Rodent) rat.getItem()).Companion.isDead(rat, worldIn)) {
                    food.shrink(1);
                    NBTTagCompound tagCompound = rat.getTagCompound();
                    tagCompound.setLong("lastFed", worldIn.getTotalWorldTime());
                }
            }
        }
    }
}
