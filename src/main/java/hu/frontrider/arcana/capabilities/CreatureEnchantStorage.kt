package hu.frontrider.arcana.capabilities

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.capabilities.CreatureEnchantCapability.CreatureEnchantContainer
import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fml.common.registry.GameRegistry

class CreatureEnchantStorage : Capability.IStorage<ICreatureEnchant> {
    override fun writeNBT(capability: Capability<ICreatureEnchant>, instance: ICreatureEnchant, side: EnumFacing): NBTBase? {
        val storage = NBTTagCompound()
        val enchantmentNBTList = NBTTagList()
        val enchants = instance.store.values
        val enabledStatus = instance.getEnabledStatus()
        enchants.forEach { enchant ->

            val enchantData = NBTTagCompound()

            enchantData.setInteger("level", enchant.level)
            enchantData.setInteger("usedTo", enchant.usedTo)
            enchantData.setString("enchant", enchant.creatureEnchant.registryName!!.toString())

            if(enabledStatus.containsKey(enchant.creatureEnchant)) {
                enchantData.setBoolean("enabled", enabledStatus[enchant.creatureEnchant]!!)
            }else{
                enchantData.setBoolean("enabled", true)
            }

            enchantmentNBTList.appendTag(enchantData)
        }


        storage.setTag("enchants", enchantmentNBTList)
        storage.setString("base", instance.circle.registryName!!.toString())

        return storage
    }

    override fun readNBT(capability: Capability<ICreatureEnchant>, instance: ICreatureEnchant, side: EnumFacing, nbt: NBTBase) {
        val enchants: NBTTagList
        val compound: NBTTagCompound
        if (nbt is NBTTagList) {
            enchants = nbt
        } else {
            compound = nbt as NBTTagCompound
            enchants = compound.getTag("enchants") as NBTTagList

            if (compound.hasKey("base")) {
                val base = GameRegistry
                        .findRegistry(EnchantingBaseCircle::class.java)
                        .getValue(ResourceLocation(compound.getString("base")))
                instance.circle = base!!
            } else {
                val base = GameRegistry
                        .findRegistry(EnchantingBaseCircle::class.java)
                        .getValue(ResourceLocation(MODID, "normal"))
                instance.circle = base!!
            }
        }

        enchants.iterator().forEachRemaining { enchant ->

            val enchantName = (enchant as NBTTagCompound).getString("enchant")
            val level = enchant.getInteger("level")

            val enchantment = ResourceLocation(enchantName)
            val creatureEnchant = GameRegistry.findRegistry(CreatureEnchant::class.java).getValue(enchantment)
            var usedTo = 0
            if (enchant.hasKey("usedTo")) {
                usedTo = enchant.getInteger("usedTo")
            }
            var enabled = true
            if(enchant.hasKey("enabled")){
                enabled = enchant.getBoolean("enabled")
            }

            val creatureEnchantContainer = CreatureEnchantContainer(creatureEnchant!!, level, usedTo,enabled)
            instance.putEnchant(creatureEnchantContainer)
        }
    }
}
