package hu.frontrider.arcana.capabilities.inhibitor

import hu.frontrider.arcana.api.InhibitorAiWrapper
import hu.frontrider.arcana.entity.inhibitor.InhibitedAI
import hu.frontrider.arcana.entity.inhibitor.aiList
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
            it.id.apply {
                ai.setString("domain", this.resourceDomain)
                ai.setString("path", this.resourcePath)
            }
            ai.setInteger("priority",it.priority)
            inhibitedAI.appendTag(ai)

        }
        storage.setTag("inhibitor", inhibitedAI)
        return storage
    }

    override fun readNBT(capability: Capability<IInhibitor>, instance: IInhibitor, side: EnumFacing?, nbt: NBTBase) {
        val nbtTagCompound = nbt as NBTTagCompound
        instance.inhibited = nbtTagCompound.getBoolean("isInhibited")

        (nbt.getTag("inhibitor") as NBTTagList).forEach {
            (it as NBTTagCompound).apply {
                if(hasKey("priority")){

                }
                val resourceLocation = ResourceLocation(getString("domain"), getString("path"))
                val selectedAI = ArrayList<InhibitorAiWrapper>()

                if (aiList.containsKey(resourceLocation)) {
                    selectedAI.add(aiList[resourceLocation]!!)
                }
                instance.aiList = selectedAI
            }
        }
    }
}
