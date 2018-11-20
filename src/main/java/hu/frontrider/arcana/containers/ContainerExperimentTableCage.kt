package hu.frontrider.arcana.containers

import hu.frontrider.arcana.blocks.experiments.tiles.TileEntityExperimentTable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Slot
import net.minecraft.util.EnumFacing
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.SlotItemHandler

class ContainerExperimentTableCage(playerInv: InventoryPlayer, experimentTable: TileEntityExperimentTable) : ContainerBase() {

    init {

        val inventory = experimentTable.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)

        val x = 104
        var slotIndex = 0;
        for(i in 0..1)
            for(j in 0..2){
            this.addSlotToContainer(SlotItemHandler(inventory, slotIndex, x+i*36, -3+(j*36)))
                slotIndex++
        }

        for (k in 0..2) {
            for (i1 in 0..8) {
                this.addSlotToContainer(Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 114 + k * 18))
            }
        }

        for (l in 0..8) {
            this.addSlotToContainer(Slot(playerInv, l, 8 + l * 18, 172))
        }
    }

    override fun canInteractWith(playerIn: EntityPlayer): Boolean {
        return true
    }


}
