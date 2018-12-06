package hu.frontrider.arcana.containers

import hu.frontrider.arcana.blocks.production.tile.TileArcaneSieve
import hu.frontrider.core.gui.container.ContainerBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Slot
import net.minecraft.util.EnumFacing
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.SlotItemHandler

class ContainerArcaneSieve(playerInv: InventoryPlayer, sieve: TileArcaneSieve) : ContainerBase() {
    init {

        val input = sieve.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP)
        val output = sieve.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN)
        val catalyst = sieve.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)

        var l = 0
        var i1: Int
        while (l < 3) {
            i1 = 0
            while (i1 < 3) {
                this.addSlotToContainer(SlotItemHandler(output, i1 + l * 3, 98 + i1 * 18, 17 + l * 18))
                ++i1
            }
            ++l
        }
        this.addSlotToContainer(SlotItemHandler(catalyst, 0, 58, 58))

        this.addSlotToContainer(SlotItemHandler(input, 0, 26, 17))
        this.addSlotToContainer(SlotItemHandler(input, 1, 26, 35))

        l = 0
        while (l < 3) {
            i1 = 0
            while (i1 < 9) {
                this.addSlotToContainer(Slot(playerInv, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18))
                ++i1
            }
            ++l
        }

        l = 0
        while (l < 9) {
            this.addSlotToContainer(Slot(playerInv, l, 8 + l * 18, 142))
            ++l
        }

    }

    override fun canInteractWith(playerIn: EntityPlayer): Boolean {
        return true
    }


}