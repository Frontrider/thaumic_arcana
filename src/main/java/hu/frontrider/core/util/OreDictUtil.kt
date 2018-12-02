package hu.frontrider.core.util

import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import net.minecraftforge.oredict.OreDictionary

object OreDictUtil {

    fun blockMatchesName(block: Block, name: String): Boolean {
        val oredictEntries = OreDictionary.getOres(name)
        var foundMatch = false
        for (itemStack in oredictEntries) {
            if (foundMatch)
                break
            val item = itemStack.item
            if (item is ItemBlock) {
                if (item.block === block)
                    foundMatch = true
            }
        }
        return foundMatch
    }

}
