package hu.frontrider.arcana.blocks;

import hu.frontrider.arcana.items.Rodent;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public abstract class BlockCage<TE extends TileEntity> extends BlockTileEntity<TE> {
    public BlockCage(Material material, String name) {
        super(material, name);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = world.getTileEntity(pos);
        IItemHandler capability = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        if (!capability.getStackInSlot(1).isEmpty())
            InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), capability.getStackInSlot(1));

        ItemStack rodent = capability.getStackInSlot(0);
        if (Rodent.isDead(rodent, tileEntity.getWorld())) {
            InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), capability.getStackInSlot(0));
        }
    }
}
