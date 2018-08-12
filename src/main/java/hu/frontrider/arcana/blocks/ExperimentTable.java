package hu.frontrider.arcana.blocks;

import hu.frontrider.arcana.items.Formula;
import hu.frontrider.arcana.recipes.formulacrafting.FormulaRecipe;
import hu.frontrider.arcana.util.AspectUtil;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class ExperimentTable extends BlockHorizontal {

    @GameRegistry.ObjectHolder(MODID + ":enchanting_powder_basic")
    private static Item enchanting_powder_basic = null;
    @GameRegistry.ObjectHolder(MODID + ":enchanting_powder_advanced")
    private static Item enchanting_powder_advanced = null;

    @GameRegistry.ObjectHolder(MODID + ":enchanting_powder_magical")
    private static Item enchanting_powder_magical = null;

    @GameRegistry.ObjectHolder(MODID + ":formula")
    public static Item formula = null;

    @GameRegistry.ObjectHolder("thaumcraft:salis_mundus")
    private static final Item sal_mundi = null;


    protected ExperimentTable() {
        super(Material.WOOD);
        setRegistryName(new ResourceLocation(MODID, "experiment_table"));
        setUnlocalizedName("experiment_table");
        setHardness(3);
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
        Item itemMainhandItem = itemMainhand.getItem();
        Item item = itemOffhand.getItem();
        //noinspection ConstantConditions
        if (itemMainhandItem == Items.GLASS_BOTTLE && item == sal_mundi) {
            itemMainhand.shrink(1);
            itemOffhand.shrink(1);
            playerIn.addItemStackToInventory(new ItemStack(formula, 1));

            return true;
        }

        if (item == formula)
            for (FormulaRecipe formulaRecipe : FormulaRecipe.getFormulaRecipeList()) {
                if (formulaRecipe.getItem().getItem() == itemMainhandItem) {
                    AspectList aspects = ((Formula) item).getAspects(itemOffhand);
                    if (AspectUtil.aspectListEquals(aspects, formulaRecipe.getFormula())) {
                        ItemStack result = formulaRecipe.craft(itemMainhand, itemOffhand);

                        if(result == null)
                            return false;

                        pos = pos.up();
                        EntityItem entityItem = new EntityItem(worldIn);
                        entityItem.setPosition(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);
                        entityItem.setItem(result);
                        worldIn.spawnEntity(entityItem);
                        return true;
                    }
                }

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
}
