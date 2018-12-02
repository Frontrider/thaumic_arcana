package hu.frontrider.arcana.blocks.production.tile

import hu.frontrider.arcana.api.IArcaneSieveRecipe
import hu.frontrider.arcana.util.items.BlockedInventory
import hu.frontrider.arcana.util.items.FilteredItemHandler
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler
import thaumcraft.api.aura.AuraHelper
import java.util.*

class TileArcaneSieve : TileEntity() {

    private val input = FilteredItemHandler(ItemStackHandler(2))
    private val output = BlockedInventory(ItemStackHandler(9))
    private val catalyst = FilteredItemHandler(ItemStackHandler(1))
    private var progress = 0
    private var currentRecipe: IArcaneSieveRecipe? = null

    val progression: Int
        get() = progress

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setTag("catalyst", catalyst.serializeNBT())
        compound.setTag("input", input.serializeNBT())
        compound.setTag("output", output.serializeNBT())

        return super.writeToNBT(compound)
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        catalyst.deserializeNBT(compound.getCompoundTag("catalyst"))
        input.deserializeNBT(compound.getCompoundTag("input"))
        output.deserializeNBT(compound.getCompoundTag("output"))

        super.readFromNBT(compound)
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        if (facing == null) return false
        return capability === CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing)
    }

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        if (facing == null) return null
        return if (capability === CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return when (facing) {
                EnumFacing.UP -> input as T
                EnumFacing.DOWN -> output as T
                else -> catalyst as T
            }
        } else super.getCapability<T>(capability, facing)
    }

    fun update(world: World, pos: BlockPos) {
        if (currentRecipe == null) {
            currentRecipe = recipes.firstOrNull {
                val canCraft = it.canCraft(input.getStackInSlot(0), input.getStackInSlot(1), catalyst.getStackInSlot(0), world)
                canCraft
            }
        }
        if (currentRecipe != null) {
            if (currentRecipe!!.canCraft(input.getStackInSlot(0), input.getStackInSlot(1), catalyst.getStackInSlot(0), world)) {
                if (AuraHelper.drainVis(world, pos, .4f, true) == .4f) {
                    progress++
                    AuraHelper.drainVis(world, pos, .4f, false)
                }
            } else {
                progress = 0
            }
            if (progress == 10) {
                val craft = currentRecipe!!.craft(input.getStackInSlot(0), input.getStackInSlot(1), catalyst.getStackInSlot(0), world)
                for (i in 0 until output.slots) {
                    val insertAttemptResult = output.insertItemInternal(i, craft, true)
                    if (insertAttemptResult.count < craft.count) {
                        craft.count -= insertAttemptResult.count
                        output.insertItemInternal(i, craft, false)
                    }
                    if (craft.isEmpty) {
                        break
                    }
                }
                progress = 0
            }
        } else {
            progress = 0
        }
    }

    fun getStoredItems(): List<ItemStack> {
        return arrayOf(
                input.getStackInSlot(0),
                input.getStackInSlot(1),
                output.getStackInSlot(0),
                catalyst.getStackInSlot(0)
        ).filter {
            !it.isEmpty
        }
    }

    companion object {
        var recipes: List<IArcaneSieveRecipe> = ArrayList()
    }
}