package hu.frontrider.core.util

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler

fun Int.cap(cap: Int): Int {
    return if (this > cap)
        cap
    else
        this
}

fun Double.cap(cap: Double): Double {
    return if (this > cap)
        cap
    else
        this
}

