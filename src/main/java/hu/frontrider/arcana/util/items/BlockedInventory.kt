package hu.frontrider.arcana.util.items

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.items.ItemStackHandler

class BlockedInventory(val handler: ItemStackHandler) :
        IItemHandlerModifiable by handler,
        INBTSerializable<NBTTagCompound> by handler {

    override fun insertItem(slot: Int,stack : ItemStack, simulate: Boolean): ItemStack {
        return stack
    }

    fun insertItemInternal(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
        return handler.insertItem(slot, stack, simulate)
    }
}