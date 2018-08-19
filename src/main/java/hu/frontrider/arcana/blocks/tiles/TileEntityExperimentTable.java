package hu.frontrider.arcana.blocks.tiles;

import hu.frontrider.arcana.blocks.tiles.experimenttable.CageItemStackHandler;
import hu.frontrider.arcana.blocks.tiles.experimenttable.WaterStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public class TileEntityExperimentTable extends TileEntity {

    private CageItemStackHandler cage = new CageItemStackHandler();

    WaterStorage waterStorage = new WaterStorage(2000);

    public TileEntityExperimentTable() {

    }

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

        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ||
                capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ||
                super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {

        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) cage :
                capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? (T) waterStorage :
                        super.getCapability(capability, facing);
    }
}
