package hu.frontrider.arcana.capabilities.inhibitor

import net.minecraft.nbt.NBTBase
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.ICapabilitySerializable

class InhibitorProvider : ICapabilitySerializable<NBTBase> {

    private val instance = INHIBITOR_CAPABILITY!!.defaultInstance

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return capability === INHIBITOR_CAPABILITY
    }

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (capability === INHIBITOR_CAPABILITY) INHIBITOR_CAPABILITY.cast<T>(this.instance) else null
    }

    override fun serializeNBT(): NBTBase? {
        return INHIBITOR_CAPABILITY!!.storage.writeNBT(INHIBITOR_CAPABILITY, this.instance, null)
    }

    override fun deserializeNBT(nbt: NBTBase) {
        INHIBITOR_CAPABILITY!!.storage.readNBT(INHIBITOR_CAPABILITY, this.instance, null, nbt)
    }

    companion object {

        @CapabilityInject(IInhibitor::class)
        lateinit var INHIBITOR_CAPABILITY: Capability<IInhibitor>
    }
}