package hu.frontrider.arcana.blocks

import hu.frontrider.arcana.items.Rodent
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.inventory.InventoryHelper
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.items.CapabilityItemHandler

abstract class BlockCage<TE : TileEntity>(material: Material, name: String) : BlockTileEntity<TE>(material, name) {

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tileEntity = world.getTileEntity(pos)
        val capability = tileEntity!!.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)

        if (!capability!!.getStackInSlot(1).isEmpty)
            InventoryHelper.spawnItemStack(world, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), capability.getStackInSlot(1))

        val rodent = capability.getStackInSlot(0)
        if (Rodent.isDead(rodent, tileEntity.world)) {
            InventoryHelper.spawnItemStack(world, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), capability.getStackInSlot(0))
        }
    }
}
