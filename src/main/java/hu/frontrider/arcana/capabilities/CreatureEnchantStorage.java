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
import java.util.Collection;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.capabilities.CreatureEnchantCapability.CreatureEnchantContainer;

public class CreatureEnchantStorage implements Capability.IStorage<ICreatureEnchant> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ICreatureEnchant> capability, ICreatureEnchant instance, EnumFacing side) {
        NBTTagCompound storage = new NBTTagCompound();
        NBTTagList compound = new NBTTagList();
        Collection<CreatureEnchantContainer> enchants = instance.getStore().values();

        enchants.forEach((enchant) -> {

            NBTTagCompound enchantData = new NBTTagCompound();

            enchantData.setInteger("level", enchant.getLevel());
            enchantData.setInteger("usedTo", enchant.getUsedTo());
            enchantData.setString("enchant", enchant.getCreatureEnchant().getRegistryName().toString());

            compound.appendTag(enchantData);
        });

        storage.setTag("enchants", compound);
        storage.setString("base", instance.getCircle().getRegistryName().toString());

        return storage;
    }

    @Override
    public void readNBT(Capability<ICreatureEnchant> capability, ICreatureEnchant instance, EnumFacing side, NBTBase nbt) {
        NBTTagList enchants;
        NBTTagCompound compound;
        if (nbt instanceof NBTTagList) {
            enchants = (NBTTagList) nbt;
        } else {
            compound = (NBTTagCompound) nbt;
            enchants = (NBTTagList) compound.getTag("enchants");

            if (compound.hasKey("base")) {
                EnchantingBaseCircle base = GameRegistry
                        .findRegistry(EnchantingBaseCircle.class)
                        .getValue(new ResourceLocation(compound.getString("base")));
                instance.setCircle(base);
            } else {
                EnchantingBaseCircle base = GameRegistry
                        .findRegistry(EnchantingBaseCircle.class)
                        .getValue(new ResourceLocation(MODID, "normal"));
                instance.setCircle(base);
            }
        }

        enchants.iterator().forEachRemaining((enchant) -> {

            String enchantName = ((NBTTagCompound) enchant).getString("enchant");
            int level = ((NBTTagCompound) enchant).getInteger("level");

            ResourceLocation enchantment = new ResourceLocation(enchantName);
            CreatureEnchant creatureEnchant = GameRegistry.findRegistry(CreatureEnchant.class).getValue(enchantment);
            int usedTo = 0;
            if (((NBTTagCompound) enchant).hasKey("usedTo")) {
                usedTo = ((NBTTagCompound) enchant).getInteger("usedTo");
            }
            CreatureEnchantContainer creatureEnchantContainer = new CreatureEnchantContainer(creatureEnchant, level, usedTo);
            instance.putEnchant(creatureEnchantContainer);
        });
    }
}
