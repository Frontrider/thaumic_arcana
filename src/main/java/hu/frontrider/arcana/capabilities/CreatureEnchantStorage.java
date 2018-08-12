package hu.frontrider.arcana.capabilities;

import hu.frontrider.arcana.creatureenchant.backend.CEnchantment;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.Map;

public class CreatureEnchantStorage implements Capability.IStorage<ICreatureEnchant> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ICreatureEnchant> capability, ICreatureEnchant instance, EnumFacing side) {
        NBTTagList compound = new NBTTagList();
        Map<CEnchantment, Integer> enchants = instance.getStore();
        enchants.forEach((enchant,level)->{
            NBTTagCompound enchantData = new NBTTagCompound();
            enchantData.setInteger("level",level);
            enchantData.setString("enchant",enchant.toString());
            compound.appendTag(enchantData);
        });

        return compound;
    }

    @Override
    public void readNBT(Capability<ICreatureEnchant> capability, ICreatureEnchant instance, EnumFacing side, NBTBase nbt) {
        NBTTagList compound = (NBTTagList) nbt;

        compound.iterator().forEachRemaining((enchant)->{
            String enchantName = ((NBTTagCompound) enchant).getString("enchant");
            int level = ((NBTTagCompound) enchant).getInteger("level");
            CEnchantment enchantment = CEnchantment.valueOf(enchantName);
            instance.putEnchant(enchantment,level);
        });
    }


}
