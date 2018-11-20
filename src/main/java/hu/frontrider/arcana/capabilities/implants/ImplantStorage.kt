package hu.frontrider.arcana.capabilities.implants

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fml.common.registry.GameRegistry

class ImplantStorage: Capability.IStorage<ITAImplants> {
    override fun writeNBT(capability: Capability<ITAImplants>, instance: ITAImplants, side: EnumFacing): NBTBase? {
        val tagList = NBTTagList()

        instance.getImplants().map {(slot,it)->
            val tag = NBTTagCompound()
            tag.setTag("data",it.getData())
            tag.setString("name",it.registryName.toString())
            tag.setString("slot",slot.name)
            tag
        }.forEach {
            tagList.appendTag(it)
        }

        return tagList
    }

    override fun readNBT(capability: Capability<ITAImplants>?, instance: ITAImplants, side: EnumFacing, nbt: NBTBase) {

        val implantRegistry = GameRegistry.findRegistry(IImplant::class.java)
        (nbt as NBTTagList).forEach{
            val compound = it as NBTTagCompound
            val regname = ResourceLocation(compound.getString("name"))

            if(implantRegistry.containsKey(regname)){
                val implant = implantRegistry.getValue(regname)!!
                implant.setData(compound.getCompoundTag("data"))
                val slot = Slot.valueOf(it.getString("slot"))
                instance.setImplant(slot, implant)
            }
        }

    }

}