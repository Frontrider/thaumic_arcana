package hu.frontrider.arcana.content.items

import hu.frontrider.arcana.ThaumicArcana.TABARCANA
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

class EnchantmentUpgradePowder(val level: Int, resourceLocation: ResourceLocation) : ItemBase(resourceLocation) {


    override fun isEnchantable(stack: ItemStack): Boolean {
        return false
    }


    override fun addInformation(stack: ItemStack?, worldIn: World?, tooltip: MutableList<String>?, flagIn: ITooltipFlag?) {
        val tagCompound = stack!!.tagCompound
        if (tagCompound != null) {
            if (tagCompound.hasKey("creature_enchants")) {
                val creature_enchants = tagCompound.getTag("creature_enchants") as NBTTagList

                creature_enchants.iterator().forEachRemaining { enchant ->
                    val name = (enchant as NBTTagCompound).getString("name")
                    val level = enchant.getInteger("level")
                    tooltip!!.add(I18n.format("enchant.creature_enchant." + name.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].toLowerCase()) + " " + level)
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun hasEffect(stack: ItemStack): Boolean {
        val tagCompound = stack.tagCompound
        return tagCompound != null && tagCompound.hasKey("creature_enchants")
    }

    override fun getHasSubtypes(): Boolean {
        return true
    }

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (tab === TABARCANA) {
            items.add(ItemStack(this))
            registry.valuesCollection.forEach { enchant -> items.add(CreatureEnchanter.createEnchantedItem(this, CreatureEnchanter.EnchantmentData(enchant, level))) }
        }
    }

    fun transferEnchants(from: ItemStack, to: ItemStack) {
        if (from.hasTagCompound()) {
            val tagCompound = from.tagCompound
            to.tagCompound = tagCompound
        }
    }

    companion object {

        private val registry = GameRegistry.findRegistry(CreatureEnchant::class.java)
    }

}
