package hu.frontrider.arcana.items.tools

import hu.frontrider.arcana.ThaumicArcana
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.init.Enchantments
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class SlimiumPickAxe : PickAxeBase("slimy_pickaxe", TOOL_MATERIAL_SLIMIUM) {
    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (tab === ThaumicArcana.TABARCANA) {
            val result = ItemStack(this)
            EnchantmentHelper.setEnchantments(mapOf(
                    Pair(Enchantments.SILK_TOUCH,2)
            ),result)
            items.add(result)
        }
    }
}

class SlimiumAxe : AxeBase("slimy_axe", TOOL_MATERIAL_SLIMIUM) {
    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (tab === ThaumicArcana.TABARCANA) {
            val result = ItemStack(this)
            EnchantmentHelper.setEnchantments(mapOf(
                    Pair(Enchantments.SILK_TOUCH,2)
            ),result)
            items.add(result)
        }
    }
}

class SlimiumShovel : ShovelBase("slimy_shovel", TOOL_MATERIAL_SLIMIUM) {
    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (tab === ThaumicArcana.TABARCANA) {
            val result = ItemStack(this)
            EnchantmentHelper.setEnchantments(mapOf(
                    Pair(Enchantments.SILK_TOUCH,2)
            ),result)
            items.add(result)
        }
    }
}

class SlimiumSword : SwordBase("slimy_sword", TOOL_MATERIAL_SLIMIUM) {
    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (tab === ThaumicArcana.TABARCANA) {
            val result = ItemStack(this)
            EnchantmentHelper.setEnchantments(mapOf(
                    Pair(Enchantments.SILK_TOUCH,2)
            ),result)
            items.add(result)
        }
    }
}
class SlimiumHoe : HoeBase("slimy_hoe", TOOL_MATERIAL_SLIMIUM){
    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (tab === ThaumicArcana.TABARCANA) {
            val result = ItemStack(this)
            EnchantmentHelper.setEnchantments(mapOf(
                    Pair(Enchantments.SILK_TOUCH,2)
            ),result)
            items.add(result)
        }
    }
}