package hu.frontrider.arcana.capabilities;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.util.Map;

public class CreatureEnchantStorage implements Capability.IStorage<ICreatureEnchant> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ICreatureEnchant> capability, ICreatureEnchant instance, EnumFacing side) {
        NBTTagCompound storage = new NBTTagCompound();
        NBTTagList compound = new NBTTagList();
        Map<CreatureEnchant, Integer> enchants = instance.getStore();

        enchants.forEach((enchant,level)->{

            NBTTagCompound enchantData = new NBTTagCompound();

            enchantData.setInteger("level",level);
            enchantData.setString("enchant",enchant.getRegistryName().toString());

            compound.appendTag(enchantData);
        });

        storage.setTag("enchants",compound);
        storage.setString("base",instance.getCircle().getRegistryName().toString());

        return storage;
    }

    @Override
    public void readNBT(Capability<ICreatureEnchant> capability, ICreatureEnchant instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound compound = (NBTTagCompound) nbt;

        NBTTagList enchants = (NBTTagList) compound.getTag("enchants");

        enchants.iterator().forEachRemaining((enchant)->{

            String enchantName = ((NBTTagCompound) enchant).getString("enchant");
            int level = ((NBTTagCompound) enchant).getInteger("level");

            ResourceLocation enchantment = new ResourceLocation(enchantName);
            CreatureEnchant creatureEnchant = GameRegistry.findRegistry(CreatureEnchant.class).getValue(enchantment);

            instance.putEnchant(creatureEnchant,level);
        });

        EnchantingBaseCircle base = GameRegistry
                .findRegistry(EnchantingBaseCircle.class)
                .getValue(new ResourceLocation(compound.getString("base")));

        instance.setCircle(base);
    }


}
