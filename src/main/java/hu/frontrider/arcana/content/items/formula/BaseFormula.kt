package hu.frontrider.arcana.content.items.formula;

import hu.frontrider.arcana.content.items.ItemWithAspects
import hu.frontrider.arcana.sided.client.gui.formula.FormulaTextManager
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import thaumcraft.api.aspects.AspectList

open class BaseFormula(resourceLocation: ResourceLocation) : ItemWithAspects(resourceLocation) {


    override fun hasEffect(itemStack: ItemStack): Boolean {
        return itemStack.hasTagCompound()
    }

    fun getItemStack(aspectList: AspectList?): ItemStack {
        val itemStack = ItemStack(this)

        if (aspectList == null)
            return itemStack

        val nbtTagCompound = NBTTagCompound()
        aspectList.writeToNBT(nbtTagCompound)
        itemStack.tagCompound = nbtTagCompound

        return itemStack
    }

    override fun ignoreContainedAspects(): Boolean {
        return false
    }

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        super.addInformation(stack, worldIn, tooltip, flagIn)
        FormulaTextManager.addText(stack, worldIn, tooltip, flagIn)
        run{

        }
    }
}
