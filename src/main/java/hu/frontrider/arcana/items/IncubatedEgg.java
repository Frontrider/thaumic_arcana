package hu.frontrider.arcana.items;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class IncubatedEgg extends ItemBase {

    IncubatedEgg() {
        super();
        setRegistryName(MODID, "incubated_egg");
        setMaxStackSize(16);
        setUnlocalizedName("incubated_egg");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        BlockPos offset = pos.offset(facing);


        if (player.canPlayerEdit(offset, facing, stack)) {
            if (!worldIn.isRemote) {
                EntityChicken entityChicken = new EntityChicken(worldIn);
                entityChicken.setPosition(offset.getX() + .5, offset.getY(), offset.getZ() + .5);
                entityChicken.setGrowingAge(-24000);
                entityChicken.setNoAI(false);
                worldIn.spawnEntity(entityChicken);

                stack.shrink(1);
            }
            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.FAIL;
    }
}
