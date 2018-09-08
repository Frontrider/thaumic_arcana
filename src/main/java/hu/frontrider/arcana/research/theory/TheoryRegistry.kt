package hu.frontrider.arcana.research.theory


import net.minecraft.init.Blocks.CACTUS
import net.minecraft.init.Blocks.PUMPKIN
import net.minecraft.init.Items.*
import net.minecraft.item.ItemStack
import thaumcraft.api.items.ItemsTC
import java.util.*

object TheoryRegistry {

    fun init() {
        val itemStacks = ArrayList<ItemStack>()

        itemStacks.add(ItemStack(WHEAT_SEEDS))
        itemStacks.add(ItemStack(POTATO))
        itemStacks.add(ItemStack(POISONOUS_POTATO))
        itemStacks.add(ItemStack(BEETROOT_SEEDS))
        itemStacks.add(ItemStack(APPLE))
        itemStacks.add(ItemStack(WHEAT))
        itemStacks.add(ItemStack(MELON))
        itemStacks.add(ItemStack(PUMPKIN))
        itemStacks.add(ItemStack(MELON_SEEDS))
        itemStacks.add(ItemStack(PUMPKIN_SEEDS))
        itemStacks.add(ItemStack(BEETROOT))
        itemStacks.add(ItemStack(CARROT))
        itemStacks.add(ItemStack(CACTUS))
        CardGrow.options = itemStacks.toTypedArray()

        itemStacks.clear()

        itemStacks.add(ItemStack(BEEF))
        itemStacks.add(ItemStack(CHICKEN))
        itemStacks.add(ItemStack(MUTTON))
        itemStacks.add(ItemStack(PORKCHOP))

        CardGrow.options = itemStacks.toTypedArray()

        itemStacks.clear()
        itemStacks.add(ItemStack(ItemsTC.brain))
        itemStacks.add(ItemStack(ROTTEN_FLESH))
        itemStacks.add(ItemStack(BONE))

        CardGrow.options = itemStacks.toTypedArray()

    }
}
