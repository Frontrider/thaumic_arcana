package hu.frontrider.arcana.capabilities.creatureenchant

import hu.frontrider.arcana.capabilities.inhibitor.IInhibitor
import net.minecraft.nbt.NBTBase
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.ICapabilitySerializable

class CreatureEnchantProvider : ICapabilitySerializable<NBTBase> {

    private val instance = CREATURE_ENCHANT_CAPABILITY!!.defaultInstance

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return capability === CREATURE_ENCHANT_CAPABILITY
    }

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (capability === CREATURE_ENCHANT_CAPABILITY) CREATURE_ENCHANT_CAPABILITY.cast<T>(this.instance) else null
    }

    override fun serializeNBT(): NBTBase? {
        return CREATURE_ENCHANT_CAPABILITY.storage.writeNBT(CREATURE_ENCHANT_CAPABILITY, this.instance, null)
    }

    override fun deserializeNBT(nbt: NBTBase) {
        CREATURE_ENCHANT_CAPABILITY.storage.readNBT(CREATURE_ENCHANT_CAPABILITY, this.instance, null, nbt)
    }

    companion object {

        @CapabilityInject(ICreatureEnchant::class)
        lateinit var CREATURE_ENCHANT_CAPABILITY: Capability<ICreatureEnchant>
    }
}