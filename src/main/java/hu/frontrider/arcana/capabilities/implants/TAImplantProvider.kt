package hu.frontrider.arcana.capabilities.implants

import net.minecraft.nbt.NBTBase
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.ICapabilitySerializable

class TAImplantProvider : ICapabilitySerializable<NBTBase> {
    private val instance = IMPLANT_CAPABILITY.defaultInstance

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (capability === IMPLANT_CAPABILITY) IMPLANT_CAPABILITY.cast<T>(this.instance) else null
    }

    override fun serializeNBT(): NBTBase? {
        return IMPLANT_CAPABILITY.storage.writeNBT(IMPLANT_CAPABILITY, this.instance, null)
    }

    override fun deserializeNBT(nbt: NBTBase) {
        IMPLANT_CAPABILITY.storage.readNBT(IMPLANT_CAPABILITY, this.instance, null, nbt)
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return capability === IMPLANT_CAPABILITY
    }

    companion object {
        @CapabilityInject(IImplant::class)
        lateinit var IMPLANT_CAPABILITY: Capability<IImplant>
    }
}