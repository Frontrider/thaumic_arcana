package hu.frontrider.arcana.content.items

import hu.frontrider.arcana.ThaumicArcana.MODID
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import thaumcraft.api.aspects.AspectList

class Rodent : ItemWithAspects(ResourceLocation(MODID, "rodent")) {
    init {

        this.addPropertyOverride(ResourceLocation("dead")) { stack, worldIn, entityIn ->
            val world = Minecraft.getMinecraft().world
            if (isDead(stack, world)) 1f else 0.0f
        }
        setMaxStackSize(1)
    }

    override fun getAspects(itemStack: ItemStack): AspectList {
        val tagCompound = itemStack.tagCompound
        val aspectList = AspectList()
        if (tagCompound == null)
            return aspectList

        aspectList.readFromNBT(tagCompound)

        return aspectList
    }

    override fun setAspects(itemStack: ItemStack, aspectList: AspectList) {
        var tagCompound = itemStack.tagCompound
        if (tagCompound == null)
            tagCompound = NBTTagCompound()
        aspectList.writeToNBT(tagCompound)
        itemStack.tagCompound = tagCompound
    }

    override fun ignoreContainedAspects(): Boolean {
        return false
    }

    override fun addInformation(stack: ItemStack?, worldIn: World?, tooltip: MutableList<String>?, flagIn: ITooltipFlag?) {
        super.addInformation(stack, worldIn, tooltip, flagIn)
        if (isDead(stack!!, worldIn))
            tooltip!!.add(I18n.format("item.thaumic_arcana.rodent.dead.name"))
    }

    companion object {

        fun isDead(itemStack: ItemStack, world: World?): Boolean {
            if (itemStack.item !is Rodent)
                return false

            val tagCompound = itemStack.tagCompound ?: return true

            if (!tagCompound.hasKey("lastFed")) {
                return true
            } else {
                val lastFed = tagCompound.getLong("lastFed")
                return world!!.totalWorldTime - lastFed > 36000
            }
        }
    }
}
