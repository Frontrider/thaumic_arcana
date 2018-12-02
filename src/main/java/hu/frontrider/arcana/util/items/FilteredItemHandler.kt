package hu.frontrider.arcana.util.items

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.items.ItemStackHandler

class FilteredItemHandler(val handler: ItemStackHandler, val filterFunction: (ItemStack) -> Boolean = { true }) :
        IItemHandlerModifiable by handler,
        INBTSerializable<NBTTagCompound> by handler {

    override fun insertItem(p0: Int, p1: ItemStack, p2: Boolean): ItemStack {
        if (!filterFunction(p1)) return p1
        return handler.insertItem(p0, p1, p2)
    }
}
