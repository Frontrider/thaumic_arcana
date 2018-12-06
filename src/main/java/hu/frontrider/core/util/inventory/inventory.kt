package hu.frontrider.core.util.inventory

import hu.frontrider.arcana.util.items.BlockedInventory
import hu.frontrider.core.util.items.isNotEmpty
import hu.frontrider.core.util.items.sameAs
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler

/**
 * Originally taken from cofh core, modified to fit the situation.
 *
 * Add an ItemStack to an inventory. Return true if the entire stack was added.
 *
 * @param inventory  The inventory.
 * @param stack      ItemStack to add.
 * @param startIndex First slot to attempt to add into. Does not loop around fully.
 * @param endIndex   Final slot to attempt to add into. Should be at most length - 1
 */
fun IItemHandler.addItemStackToInventory(stack: ItemStack, startIndex: Int = 0, endIndex: Int = this.slots - 1): Boolean {
    if (stack.isEmpty) {
        return true
    }
    var openSlot = -1
    for (i in startIndex..endIndex) {
        val inventoryStack = this.getStackInSlot(i)
        if (inventoryStack.sameAs(stack)) {
            val hold = inventoryStack.maxStackSize - inventoryStack.count
            if (hold >= stack.count) {
                inventoryStack.grow(stack.count)
                return true
            } else {
                stack.shrink(hold)
                inventoryStack.grow(hold)
            }
        } else if (inventoryStack.isEmpty && openSlot == -1) {
            openSlot = i
        }
    }
    if (openSlot > -1) {
        if(this is BlockedInventory){
            this.insertItemInternal(openSlot, stack, false)
        }else
        this.insertItem(openSlot, stack, false)
    } else {
        return false
    }
    return true
}

/**
 * Extracts the first available item from an inventory.
 *
 * @param doExtract weather or not we extract it from the inventory for real, or just checking it
 * @param count the amount of items we want to extract. If 0, it will pull the entire stack.
 * */
fun IItemHandler.extractFirst(doExtract: Boolean, count: Int = 0): ItemStack {
    var stackCount = count
    for (i in 0 until this.slots) {
        val itemStack = this.getStackInSlot(i)
        if (itemStack.isNotEmpty()) {
            if (stackCount == 0)
                stackCount = itemStack.count

            return extractItem(i, stackCount, !doExtract)
        }

    }
    return ItemStack.EMPTY
}

fun IItemHandler.extract(stack: ItemStack, startIndex: Int = 0, endIndex: Int = this.slots - 1): ItemStack {
    for (i in startIndex..endIndex) {
        val inventoryStack = this.getStackInSlot(i)
        if (inventoryStack.sameAs(stack)) {
            return extractItem(i, stack.count, false)
        }
    }
    return ItemStack.EMPTY
}