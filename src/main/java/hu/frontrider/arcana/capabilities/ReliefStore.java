package hu.frontrider.arcana.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class ReliefStore implements Capability.IStorage<IRelief> {
    @Override
    public NBTBase writeNBT(Capability<IRelief> capability, IRelief instance, EnumFacing side) {
        return new NBTTagFloat(instance.getHealthStore());
    }

    @Override
    public void readNBT(Capability<IRelief> capability, IRelief instance, EnumFacing side, NBTBase nbt) {
        instance.setHealthStore(((NBTTagFloat) nbt).getInt());
    }
}