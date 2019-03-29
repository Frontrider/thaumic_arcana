package hu.frontrider.arcana.containers

import hu.frontrider.arcana.blocks.production.tile.TileArcaneSieve
import hu.frontrider.core.gui.container.ContainerBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer

class ContainerSoulManipulator(playerInv: InventoryPlayer, sieve: TileArcaneSieve) : ContainerBase()  {
    override fun canInteractWith(p0: EntityPlayer): Boolean {
        return true

    }
}