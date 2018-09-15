package hu.frontrider.arcana.sided.client.gui.formula.text

import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.util.AspectUtil
import hu.frontrider.arcana.util.strings.ChatFormat.YELLOW
import hu.frontrider.arcana.util.strings.formatTranslate
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.aspects.AspectList

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
class EnchantText : FormulaText {

    private val creatureEnchantIForgeRegistry = GameRegistry.findRegistry(CreatureEnchant::class.java)

    override fun addText(stack: ItemStack, tagCompound: NBTTagCompound, worldIn: World?, tooltip: MutableList<String>) {
        val aspectList = AspectList()
        aspectList.readFromNBT(tagCompound)

        creatureEnchantIForgeRegistry.valuesCollection.forEach { creatureEnchant ->
            if (AspectUtil.aspectListEquals(aspectList, creatureEnchant.formula())) {
                tooltip+=("${formatTranslate("enchant.creature_enchant.helper.enchant", YELLOW)}: ${formatTranslate(creatureEnchant.getUnlocalizedName(), YELLOW)}")
            }
        }
    }
}
