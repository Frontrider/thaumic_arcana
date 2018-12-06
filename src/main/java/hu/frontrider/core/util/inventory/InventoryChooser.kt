package hu.frontrider.core.util.inventory

import net.minecraft.entity.Entity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
import net.minecraftforge.items.IItemHandler

import java.util.*

object InventoryChooser {

    fun getInventory(world: World,blockPos: BlockPos,facing: EnumFacing):Optional<IItemHandler>{
        val blockInventory = getBlockInventory(world, blockPos, facing)
        if(blockInventory.isPresent)
            return blockInventory
        val entityInventory = getEntityInventory(world, blockPos, facing)
        if(entityInventory.isPresent)
            return entityInventory

        return Optional.empty()
    }

    private fun getBlockInventory(world: World,blockPos: BlockPos,facing: EnumFacing):Optional<IItemHandler>{
        val tileEntity = world.getTileEntity(blockPos) ?: return Optional.empty()

        if(tileEntity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,facing)){
            val iItemHandler = tileEntity.getCapability(ITEM_HANDLER_CAPABILITY, facing)!!
            return Optional.of(iItemHandler)
        }

        return Optional.empty()
    }

    private fun getEntityInventory(world: World,blockPos: BlockPos,facing: EnumFacing): Optional<IItemHandler> {
        for (entity in world.getEntitiesWithinAABB(Entity::class.java,AxisAlignedBB(blockPos))) {
            if(entity.hasCapability(ITEM_HANDLER_CAPABILITY,facing)){
                val iItemHandler = entity.getCapability(ITEM_HANDLER_CAPABILITY, facing)!!
                return Optional.of(iItemHandler)
            }

        }
        return Optional.empty()
    }

}