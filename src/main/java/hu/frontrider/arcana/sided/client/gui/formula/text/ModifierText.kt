package hu.frontrider.arcana.sided.client.gui.formula.text

import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle
import hu.frontrider.arcana.util.AspectUtil
import hu.frontrider.arcana.util.strings.ChatFormat
import hu.frontrider.arcana.util.strings.StringUtil
import hu.frontrider.arcana.util.strings.formatTranslate
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.registries.IForgeRegistry
import thaumcraft.api.aspects.AspectList


/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
class ModifierText : FormulaText {

    private val creatureEnchantIForgeRegistry: IForgeRegistry<EnchantingBaseCircle>

    init {
        creatureEnchantIForgeRegistry = GameRegistry.findRegistry(EnchantingBaseCircle::class.java)
    }

    override fun addText(stack: ItemStack, tagCompound: NBTTagCompound, worldIn: World?, tooltip: MutableList<String>) {
        val aspectList = AspectList()
        aspectList.readFromNBT(tagCompound)
                    creatureEnchantIForgeRegistry
                            .valuesCollection
                            .forEach { modifier ->
                                if (AspectUtil.aspectListEquals(aspectList, modifier.formula)) {
                                    tooltip+=("${formatTranslate("enchant.creature_enchant.helper.modifier", ChatFormat.YELLOW)}: ${formatTranslate(modifier.unlocalizedName, ChatFormat.YELLOW)}")
                                }
                            }

    }
}
