package hu.frontrider.arcana.blocks.experiments.tiles

import hu.frontrider.arcana.capabilities.EssentiaRecieverTrait
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
import net.minecraftforge.items.ItemStackHandler
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.IEssentiaTransport

class TileEntitySoulManipulator(val essentiaReciever:EssentiaRecieverTrait=EssentiaRecieverTrait(Aspect.SOUL, arrayOf(EnumFacing.DOWN))):
        TileEntity(),
        IEssentiaTransport by essentiaReciever {

    private val cage = ItemStackHandler(1)

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setTag("slot", cage.serializeNBT())
        compound.setInteger("amount",essentiaReciever.amount)
        return super.writeToNBT(compound)
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        cage.deserializeNBT(compound.getCompoundTag("slot"))
        essentiaReciever.amount = compound.getInteger("amount")
        super.readFromNBT(compound)
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return capability === ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing)
    }

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (capability === ITEM_HANDLER_CAPABILITY)
            ITEM_HANDLER_CAPABILITY.cast(cage)
        else
            super.getCapability(capability, facing)
    }
}