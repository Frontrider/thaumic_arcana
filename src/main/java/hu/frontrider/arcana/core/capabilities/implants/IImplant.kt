package hu.frontrider.arcana.core.capabilities.implants

import net.minecraft.entity.EntityLiving
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.registries.IForgeRegistryEntry

abstract class IImplant : IForgeRegistryEntry.Impl<IImplant>() {
    abstract fun supports(type: Class<EntityLiving>): Boolean
    abstract fun getSlot()
    abstract fun getData(): NBTTagCompound
    abstract fun setData(nbt: NBTTagCompound)
}