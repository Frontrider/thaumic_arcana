package hu.frontrider.core.recipes

import net.minecraft.init.Items
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemTool
import net.minecraft.item.crafting.IRecipe
import net.minecraft.potion.PotionUtils
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.registries.IForgeRegistryEntry

class RecipeTippedTool : IForgeRegistryEntry.Impl<IRecipe>(), IRecipe {

    override fun matches(inv: InventoryCrafting, worldIn: World): Boolean {
        val items = ArrayList<ItemStack>()
        for (i in 0 until inv.width) {
            for (j in 0 until inv.height) {
                val itemstack = inv.getStackInRowAndColumn(i, j)
                if (!itemstack.isEmpty)
                    items.add(itemstack)
            }
        }
        return if (items.size == 2) {
            items.asSequence()
                    .map {
                        it.item
                    }.filter {
                        it !is ItemTool
                    }.filter {
                        it != Items.POTIONITEM
                    }.count() == 0
        } else
            false
    }

    override fun getCraftingResult(inv: InventoryCrafting): ItemStack {
        val items = ArrayList<ItemStack>()
        for (i in 0 until inv.width) {
            for (j in 0 until inv.height) {
                val itemstack = inv.getStackInRowAndColumn(i, j)
                if (!itemstack.isEmpty)
                    items.add(itemstack)
            }
        }
        return if (items.size != 2) {
            ItemStack.EMPTY
        } else{
            val tool = items.asSequence().filter {
                it.item is ItemTool
            }.first()
            val potion = items.asSequence().filter {
                it.item ==Items.POTIONITEM
            }.first()

            PotionUtils.addPotionToItemStack(tool, PotionUtils.getPotionFromItem(potion))
            PotionUtils.appendEffects(tool, PotionUtils.getFullEffectsFromItem(potion))
            PotionUtils.addPotionTooltip(tool, arrayOf<String>().toMutableList(),0f)

            tool
        }

    }

    override fun getRecipeOutput(): ItemStack {
        return ItemStack.EMPTY
    }

    override fun getRemainingItems(inv: InventoryCrafting): NonNullList<ItemStack> {
        return NonNullList.withSize(inv.sizeInventory, ItemStack.EMPTY)
    }

    override fun isDynamic(): Boolean {
        return true
    }

    override fun canFit(width: Int, height: Int): Boolean {
        return width*height>=2
    }
}
