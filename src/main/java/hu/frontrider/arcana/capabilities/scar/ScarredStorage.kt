package hu.frontrider.arcana.capabilities.scar


import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability

class ScarredStorage : Capability.IStorage<IScarred> {
    override fun readNBT(capability: Capability<IScarred>?, instance: IScarred, side: EnumFacing?, nbt: NBTBase?) {
        (nbt as NBTTagCompound).apply {
            instance.requiredDamage = getInteger("requiredDamage")
            instance.currentDamage = getFloat("currentDamage")
            instance.severity = getByte("severity")
            instance.requiresResearch = getBoolean("requiresResearch")
        }
    }

    override fun writeNBT(capability: Capability<IScarred>, instance: IScarred, side: EnumFacing?): NBTBase {
        val nbtTagCompound = NBTTagCompound()

        instance.apply {
            nbtTagCompound.setInteger("requiredDamage", requiredDamage)
            nbtTagCompound.setFloat("currentDamage", currentDamage)
            nbtTagCompound.setByte("severity", severity)
            nbtTagCompound.setBoolean("requiresResearch",requiresResearch)
        }

        return nbtTagCompound
    }
}