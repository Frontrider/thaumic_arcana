package hu.frontrider.arcana.util.items

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient

class NbtAwareIngredient(vararg items: ItemStack) :Ingredient(*items) {
    override fun apply(targetStack: ItemStack?): Boolean {

        if (targetStack == null) {
            return false
        } else {
            for (itemstack in this.matchingStacks) {
                if (itemstack.item === targetStack.item) {
                    val i = itemstack.metadata
                    if (i == 32767 || i == targetStack.metadata) {
                        return ItemStack.areItemStacksEqual(itemstack,targetStack)
                    }
                }
            }

            return false
        }
    }
}