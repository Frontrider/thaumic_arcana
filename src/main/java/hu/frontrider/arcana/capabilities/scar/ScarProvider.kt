package hu.frontrider.arcana.capabilities.scar

import net.minecraft.nbt.NBTBase
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.ICapabilitySerializable

class ScarProvider: ICapabilitySerializable<NBTBase> {
    private val instance = SCARRED_CAPABILITY!!.defaultInstance

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return capability === SCARRED_CAPABILITY
    }

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (capability === SCARRED_CAPABILITY)SCARRED_CAPABILITY.cast<T>(this.instance) else null
    }

    override fun serializeNBT(): NBTBase? {
        return SCARRED_CAPABILITY.storage.writeNBT(SCARRED_CAPABILITY, this.instance, null)
    }

    override fun deserializeNBT(nbt: NBTBase) {
        SCARRED_CAPABILITY.storage.readNBT(SCARRED_CAPABILITY, this.instance, null, nbt)
    }
    companion object {

        @CapabilityInject(IScarred::class)
        lateinit var SCARRED_CAPABILITY: Capability<IScarred>
    }
}