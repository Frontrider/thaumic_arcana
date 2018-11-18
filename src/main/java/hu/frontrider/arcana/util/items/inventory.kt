package hu.frontrider.arcana.util.items

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler

fun IItemHandler.contains(stack: ItemStack): Boolean {
    for (i in 0 until this.slots) {
        if(getStackInSlot(i).isItemEqual(stack))
            return true
    }
    return false
}