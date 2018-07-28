package hu.frontrider.arcana.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class CreatureEnchantStorage implements Capability.IStorage<ICreatureEnchant> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ICreatureEnchant> capability, ICreatureEnchant instance, EnumFacing side) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("type", instance.getName());
        compound.setInteger("level", instance.getLevel());
        return compound;
    }

    @Override
    public void readNBT(Capability<ICreatureEnchant> capability, ICreatureEnchant instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound compound = (NBTTagCompound) nbt;
        instance.setName(compound.getString("type"));
        instance.setLevel(compound.getInteger("level"));
    }
}
