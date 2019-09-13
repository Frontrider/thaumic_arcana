package hu.frontrider.arcana.capabilities.scar


import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability

class ScarredStorage : Capability.IStorage<IScarred> {
    override fun readNBT(capability: Capability<IScarred>?, instance: IScarred, side: EnumFacing?, nbt: NBTBase?) {
        (nbt as NBTTagCompound).apply {
            if (hasKey("requiredDamage"))
                instance.requiredDamage = getInteger("requiredDamage")
            if (hasKey("currentDamage"))
                instance.currentDamage = getFloat("currentDamage")
            if (hasKey("severity"))
                instance.severity = getByte("severity")
            if (hasKey("limbs"))
                instance.limbs.fromNbt(getTag("limbs") as NBTTagCompound)
        }
    }

    override fun writeNBT(capability: Capability<IScarred>, instance: IScarred, side: EnumFacing?): NBTBase {
        val nbtTagCompound = NBTTagCompound()

        instance.apply {
            nbtTagCompound.setInteger("requiredDamage", requiredDamage)
            nbtTagCompound.setFloat("currentDamage", currentDamage)
            nbtTagCompound.setByte("severity", severity)
            nbtTagCompound.setTag("limbs", limbs.toNbt())
        }

        return nbtTagCompound
    }
}