package hu.frontrider.arcana.blocks.experiments;

import hu.frontrider.arcana.ThaumicArcana;
import hu.frontrider.arcana.blocks.BlockCage;
import hu.frontrider.arcana.blocks.experiments.tiles.TileEntityArcaneCage;
import hu.frontrider.arcana.blocks.experiments.tiles.logic.ArcaneCageAreaScan;
import hu.frontrider.arcana.client.gui.GuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockArcaneCage extends BlockCage<TileEntityArcaneCage> {

    public static final PropertyBool CAPTURED = PropertyBool.create("captured");

    ArcaneCageAreaScan arcaneCageAreaScan = new ArcaneCageAreaScan();
    public BlockArcaneCage() {
        super(Material.ROCK, "arcane_cage");
        setHardness(3);
        setTickRandomly(true);
    }

    @Override
    public Class<TileEntityArcaneCage> getTileEntityClass() {
        return TileEntityArcaneCage.class;
    }

    @Nullable
    @Override
    public TileEntityArcaneCage createTileEntity(World world, IBlockState state) {
        return new TileEntityArcaneCage();
    }


    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        TileEntityArcaneCage tileEntity = getTileEntity(worldIn, pos);

        if (!state.getValue(CAPTURED) && tileEntity.randomTick(worldIn)&& arcaneCageAreaScan.isvalid(worldIn,pos)) {
            worldIn.setBlockState(pos, state.withProperty(CAPTURED, true), 2);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        playerIn.openGui(ThaumicArcana.instance, GuiHandler.ARCANE_CAGE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CAPTURED);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.setBlockState(pos, state.withProperty(CAPTURED, false), 2);
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(CAPTURED, meta > 0);
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(CAPTURED) ? 1 : 0;
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


}
