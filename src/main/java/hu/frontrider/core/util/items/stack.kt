package hu.frontrider.core.util.items

import net.minecraft.item.ItemStack

fun ItemStack.sameAs(stack: ItemStack):Boolean{

    val thisNBT = this.tagCompound
    val stackNBT = stack.tagCompound
    var nbtmatch = true

    if(thisNBT != null && stackNBT != null){
            nbtmatch = thisNBT == stackNBT
    }

    return this.item == stack.item && this.metadata == stack.metadata && nbtmatch

}


fun ItemStack.isNotEmpty(): Boolean {
    return !this.isEmpty
}
