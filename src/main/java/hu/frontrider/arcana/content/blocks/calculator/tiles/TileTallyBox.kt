package hu.frontrider.arcana.content.blocks.calculator.tiles

import hu.frontrider.arcana.content.blocks.calculator.BlockTallyBox
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.aspects.Aspect

class TileTallyBox(val block:BlockTallyBox): TileEntity() {
    private val fakeStack = TallyBoxInventory(block)

    init {
        fakeStack.stack = ThaumcraftApiHelper.makeCrystal(Aspect.AURA,10)
    }
    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setTag("stack", fakeStack.serializeNBT())
        return super.writeToNBT(compound)
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        fakeStack.deserializeNBT(compound.getCompoundTag("stack"))

        super.readFromNBT(compound)
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return capability === ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing)
    }

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (capability === ITEM_HANDLER_CAPABILITY)
            ITEM_HANDLER_CAPABILITY.cast(fakeStack)
        else
            super.getCapability(capability, facing)
    }
}

