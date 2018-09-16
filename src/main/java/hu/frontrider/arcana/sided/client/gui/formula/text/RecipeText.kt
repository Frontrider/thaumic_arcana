package hu.frontrider.arcana.sided.client.gui.formula.text

import hu.frontrider.arcana.core.formulacrafting.FormulaRecipes
import hu.frontrider.arcana.util.AspectUtil
import hu.frontrider.arcana.util.strings.ChatFormat.*
import hu.frontrider.arcana.util.strings.formatString
import hu.frontrider.arcana.util.strings.formatTranslate
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import thaumcraft.api.aspects.AspectList

class RecipeText : FormulaText {

    override fun addText(stack: ItemStack, tagCompound: NBTTagCompound, worldIn: World?, tooltip: MutableList<String>) {
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

                    var name = it.block.localizedName

                    if (it.isConsumeBlock) {
                        name = formatString(name, RED, BOLD)
                    }

                    tooltip += (
                            "${RESET.tag}$name " +
                                    if (it.required != null) {
                                        formatString("+", GREEN) + " ${formatTranslate(it.required!!.item.unlocalizedName + ".name")}*${it.required!!.count} "
                                    } else {
                                        ""
                                    } + formatString(if(it.vis>0)"vis*${it.vis}" else "",LIGHT_PURPLE)+
                                    "${formatString("->", GREEN)} \n${formatTranslate(it.result!!.item.unlocalizedName + ".name")}*${it.result.count}\n"+
                                    formatString(if(it.vis>0)"flux*${it.pollution}\n" else "",DARK_PURPLE))
                }

    }
}