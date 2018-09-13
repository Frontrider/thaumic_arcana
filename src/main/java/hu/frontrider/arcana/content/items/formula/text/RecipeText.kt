package hu.frontrider.arcana.content.items.formula.text

import hu.frontrider.arcana.core.formulacrafting.FormulaRecipes
import hu.frontrider.arcana.util.AspectUtil
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import thaumcraft.api.aspects.AspectList

class RecipeText : FormulaText {
    override fun addText(stack: ItemStack?, tagCompound: NBTTagCompound, worldIn: World?, tooltip: MutableList<String>) {
        val aspectList = AspectList()
        aspectList.readFromNBT(tagCompound)
        FormulaRecipes.getRecipesCollection().stream()
                .flatMap {
                    it.stream()
                }
                .filter {
                    AspectUtil.aspectListEquals(aspectList, it.formula)
                }
                .forEach {
                    tooltip.add("${it.block.localizedName} " +
                            if (it.required != null) {
                                "+ ${I18n.format(it.required!!.item.unlocalizedName)}*${it.required!!.count} "
                            } else {
                                ""
                            } +
                            "-> ${I18n.format(it.result!!.item.unlocalizedName)}*${it.result!!.count}")
                }

    }
}