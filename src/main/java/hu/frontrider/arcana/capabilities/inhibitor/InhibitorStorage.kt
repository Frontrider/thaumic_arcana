package hu.frontrider.arcana.capabilities.inhibitor

import hu.frontrider.arcana.entity.ai.InhibitedAI
import hu.frontrider.arcana.entity.ai.aiList
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.capabilities.Capability

class InhibitorStorage : Capability.IStorage<IInhibitor> {
    override fun writeNBT(capability: Capability<IInhibitor>, instance: IInhibitor, side: EnumFacing?): NBTBase? {
        val storage = NBTTagCompound()
        storage.setBoolean("isInhibited", instance.inhibited)
        val inhibitedAI = NBTTagList()
        instance.aiList.forEach {
            val ai = NBTTagCompound()
            it.location.apply {
                ai.setString("domain", this.resourceDomain)
                ai.setString("path", this.resourcePath)
            }
            inhibitedAI.appendTag(ai)

        }
        storage.setTag("ai", inhibitedAI)
        return storage
    }

    override fun readNBT(capability: Capability<IInhibitor>, instance: IInhibitor, side: EnumFacing?, nbt: NBTBase) {
        val nbtTagCompound = nbt as NBTTagCompound
        instance.inhibited = nbtTagCompound.getBoolean("isInhibited")

        (nbt.getTag("ai") as NBTTagList).forEach {
            (it as NBTTagCompound).apply {
                val resourceLocation = ResourceLocation(getString("domain"), getString("path"))
                val selectedAI = ArrayList<InhibitedAI>()
                if (aiList.containsKey(resourceLocation)) {
                    selectedAI.add(aiList[resourceLocation]!!)
                }
                instance.aiList = selectedAI
            }
        }
    }
}
