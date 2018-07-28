package hu.frontrider.arcana.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("ALL")
public class CreatureEnchantProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(ICreatureEnchant.class)
    public static final Capability<ICreatureEnchant> CREATURE_ENCHANT_CAPABILITY = null;

    private ICreatureEnchant instance = CREATURE_ENCHANT_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CREATURE_ENCHANT_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CREATURE_ENCHANT_CAPABILITY ? CREATURE_ENCHANT_CAPABILITY.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return CREATURE_ENCHANT_CAPABILITY.getStorage().writeNBT(CREATURE_ENCHANT_CAPABILITY, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CREATURE_ENCHANT_CAPABILITY.getStorage().readNBT(CREATURE_ENCHANT_CAPABILITY, this.instance, null, nbt);
    }
}