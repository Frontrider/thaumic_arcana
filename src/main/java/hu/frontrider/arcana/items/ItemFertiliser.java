package hu.frontrider.arcana.items;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class ItemFertiliser extends ItemBase {

    ItemFertiliser() {
        setRegistryName(new ResourceLocation(MODID, "fertiliser"));
        setUnlocalizedName("fertiliser");
    }

    private static boolean fertilise(World worldIn, BlockPos target, EntityPlayer player, ItemStack stack, EnumHand hand) {

        IBlockState iblockstate = worldIn.getBlockState(target);

        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, target, iblockstate, stack, hand);
        if (hook != 0) return hook > 0;

        if (iblockstate.getBlock() instanceof IGrowable) {
            IGrowable igrowable = (IGrowable) iblockstate.getBlock();

            if (igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote)) {
                if (!worldIn.isRemote) {
                    if (igrowable.canUseBonemeal(worldIn, worldIn.rand, target, iblockstate)) {
                        igrowable.grow(worldIn, worldIn.rand, target, iblockstate);
                    }
                    stack.shrink(1);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);

        if (player.canPlayerEdit(pos.offset(facing), facing, stack)) {
            if (fertilise(worldIn, pos, player, stack, hand)) {
                if (!worldIn.isRemote) {
                    worldIn.playEvent(2005, pos, 0);
                }
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }
}
