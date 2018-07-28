package hu.frontrider.arcana.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ReliefProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IRelief.class)
    public static final Capability<IRelief> RELIEF_CAPABILITY = null;

    private IRelief instance = RELIEF_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == RELIEF_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == RELIEF_CAPABILITY ? RELIEF_CAPABILITY.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return RELIEF_CAPABILITY.getStorage().writeNBT(RELIEF_CAPABILITY, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        RELIEF_CAPABILITY.getStorage().readNBT(RELIEF_CAPABILITY, this.instance, null, nbt);
    }
}
