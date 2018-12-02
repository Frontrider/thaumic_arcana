package hu.frontrider.arcana.util.items

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler

fun IItemHandler.contains(stack: ItemStack): Boolean {
    for (i in 0 until this.slots) {
        if (getStackInSlot(i).isItemEqual(stack))
            return true
    }
    return false
}

fun IItemHandler.insertStack(stack: ItemStack): ItemStack {
    return insertStack(stack) { it, slot, simulte,stack ->
        it.insertItem(slot, stack, simulte)
    }
}

fun IItemHandler.insertStack(stack: ItemStack, insertFunction: (IItemHandler, Int, Boolean,ItemStack) -> ItemStack): ItemStack {

    for (i in 0 until this.slots) {
        val insertAttemptResult = insertFunction(this, i, true,stack)


    }
    return ItemStack.EMPTY
}