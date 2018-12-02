package hu.frontrider.core.util.inventory

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.InventoryCrafting

/**
 * This class is used to get recipes (IRecipe requires it...) with a Container.
 *
 * @author King Lemming
 */
class InventoryCraftingFalse(width: Int, height: Int) : InventoryCrafting(nullContainer, width, height) {

    /* NULL INNER CLASS */
    class NullContainer : Container() {

        override fun onCraftMatrixChanged(inventory: IInventory?) {

        }

        override fun canInteractWith(player: EntityPlayer): Boolean {

            return false
        }

    }

    companion object {

        private val nullContainer = NullContainer()
    }

}