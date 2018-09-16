package hu.frontrider.arcana.sided.client.gui.formula.text

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
@FunctionalInterface
interface FormulaText {

    fun addText(stack: ItemStack, tagCompound: NBTTagCompound, worldIn: World?, tooltip: MutableList<String>)
}
