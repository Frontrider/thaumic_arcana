package hu.frontrider.core.traits

import hu.frontrider.core.util.inventory.InventoryChooser
import hu.frontrider.core.util.inventory.extract
import hu.frontrider.core.util.items.isNotEmpty
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object InventoryConsumer {

    fun consumeOne(stacks: Array<Item>, world: World, pos: BlockPos, sides: Array<EnumFacing>): Boolean {
        sides.forEach {
            val inventory = InventoryChooser.getInventory(world, pos.offset(it), it.opposite)
            if (inventory.isPresent) {
                val iItemHandler = inventory.get()
                stacks.forEach {
                    val item = iItemHandler.extract(ItemStack(it))
                    if (item.isNotEmpty()) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun <T> consumeOne(clazz:Class<T>, world: World, pos: BlockPos, sides: Array<EnumFacing>): ItemStack {
        sides.forEach {
            val inventory = InventoryChooser.getInventory(world, pos.offset(it), it.opposite)
            if (inventory.isPresent) {
                val iItemHandler = inventory.get()
                for (index in 0 until iItemHandler.slots) {
                    val itemStack = iItemHandler.extractItem(index, 1, true)
                    if (itemStack.isNotEmpty()) {
                        if (clazz.isInstance(itemStack.item)) {
                            return iItemHandler.extractItem(index, 1, false)
                        }
                    }
                }
            }
        }
        return ItemStack.EMPTY
    }
}