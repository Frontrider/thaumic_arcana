package hu.frontrider.arcana.content.containers

import hu.frontrider.arcana.content.blocks.experiments.tiles.TileEntityArcaneCage
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.SlotItemHandler

class ContainerArcaneCage(playerInv: InventoryPlayer, tileEntityArcaneCage: TileEntityArcaneCage) : ContainerBase() {

    init {
        val inventory = tileEntityArcaneCage.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)

        this.addSlotToContainer(object : SlotItemHandler(inventory, 0, 80, 35) {
            override fun onSlotChanged() {
                tileEntityArcaneCage.markDirty()
            }
        })

        this.addSlotToContainer(object : SlotItemHandler(inventory, 1, 135, 35) {
            override fun onSlotChanged() {
                tileEntityArcaneCage.markDirty()
            }
        })


        placePlayerInv(playerInv)
    }


    override fun canInteractWith(playerIn: EntityPlayer): Boolean {
        return true
    }


}
