package hu.frontrider.arcana.content.blocks.calculator.tiles

import hu.frontrider.arcana.content.blocks.calculator.BlockTallyBox
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.IItemHandlerModifiable

class TallyBoxInventory(val block:BlockTallyBox) : IItemHandler, IItemHandlerModifiable, INBTSerializable<NBTTagCompound> {
    var stack: ItemStack? = null

    override fun setStackInSlot(slot: Int, stack: ItemStack) {

    }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        stack?.deserializeNBT(nbt)
    }

    override fun serializeNBT(): NBTTagCompound {
        return stack?.serializeNBT() ?: NBTTagCompound()
    }

    override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
        return stack
    }

    override fun getStackInSlot(slot: Int): ItemStack {
        return stack?: ItemStack.EMPTY
    }

    override fun getSlotLimit(slot: Int): Int {
        return stack?.maxStackSize?: 0
    }

    override fun getSlots(): Int {
        return 1
    }

    override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack {
        if (!simulate && stack != null) {
            block.emitRedstone()
            return ItemStack.EMPTY
        }

        return stack?:ItemStack.EMPTY
    }
}