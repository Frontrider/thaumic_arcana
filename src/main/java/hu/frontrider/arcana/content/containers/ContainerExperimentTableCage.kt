package hu.frontrider.arcana.content.containers

import hu.frontrider.arcana.content.blocks.experiments.tiles.TileEntityExperimentTable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.EnumFacing
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.SlotItemHandler

class ContainerExperimentTableCage(playerInv: InventoryPlayer, experimentTable: TileEntityExperimentTable) : ContainerBase() {

    init {
        val inventory = experimentTable.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)

        this.addSlotToContainer(object : SlotItemHandler(inventory, 0, 80, 35) {
            override fun onSlotChanged() {
                experimentTable.markDirty()
            }
        })

        this.addSlotToContainer(object : SlotItemHandler(inventory, 1, 135, 35) {
            override fun onSlotChanged() {
                experimentTable.markDirty()
            }
        })


        placePlayerInv(playerInv)
    }


    override fun canInteractWith(playerIn: EntityPlayer): Boolean {
        return true
    }


}
