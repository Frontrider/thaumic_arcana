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
    return insertStack(stack,false) { it, slot, simulte, stack ->
        it.insertItem(slot, stack, simulte)
    }
}

fun IItemHandler.insertStack(stack: ItemStack, simulate: Boolean): ItemStack {
    return insertStack(stack, simulate) { it, slot, simulte, stack ->
        it.insertItem(slot, stack, simulte)
    }
}

fun IItemHandler.insertStack(stack: ItemStack, simulate: Boolean, insertFunction: (IItemHandler, Int, Boolean, ItemStack) -> ItemStack): ItemStack {
    var insertAttemptResult = stack
    for (i in 0 until this.slots) {
       insertAttemptResult = insertFunction(this, i, simulate, insertAttemptResult)
        if(insertAttemptResult.isEmpty)
            return ItemStack.EMPTY
    }
    return insertAttemptResult
}

fun IItemHandler.insertStack(stack: ItemStack, insertFunction: (IItemHandler, Int, Boolean, ItemStack) -> ItemStack): ItemStack {
    return insertStack(stack, false, insertFunction)
}