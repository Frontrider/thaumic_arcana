package hu.frontrider.arcana.blocks.tiles;

import hu.frontrider.arcana.blocks.tiles.capabilities.CageItemStackHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class TileEntityArcaneCage extends TileEntity {

    @GameRegistry.ObjectHolder(MODID + ":rodent")
    static Item rodent = null;

    private CageItemStackHandler cage = new CageItemStackHandler();

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("cage", cage.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        cage.deserializeNBT(compound.getCompoundTag("cage"));

        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == ITEM_HANDLER_CAPABILITY ||
                super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == ITEM_HANDLER_CAPABILITY ? ITEM_HANDLER_CAPABILITY.cast(cage) :
                super.getCapability(capability, facing);
    }

    public boolean randomTick(World world) {
        ItemStack stackInSlot = cage.getStackInSlot(0);

        if (stackInSlot.isEmpty() && world.rand.nextBoolean()) {
            ItemStack food = cage.getStackInSlot(1);
            if(!food.isEmpty())
            {
                food.shrink(1);
                ItemStack rodent = new ItemStack(TileEntityArcaneCage.rodent);
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setLong("lastFed", world.getTotalWorldTime());

                rodent.setTagCompound(tagCompound);

                cage.setStackInSlot(0, rodent);
                this.markDirty();
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

}
