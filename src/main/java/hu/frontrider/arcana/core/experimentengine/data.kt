package hu.frontrider.arcana.core.experimentengine

import net.minecraft.block.Block
import net.minecraft.item.Item
import thaumcraft.api.research.ResearchCategory
import java.util.*
import kotlin.collections.HashMap
/**
 * The way it works:
 * each research category has their own aspects, that must be lined up with the items.
 * the more unrelated aspects you put in, the more focus you need.
 * each item has a "diminishing returns", after a certain number of uses they will not give anything. (tracked by the player)
 *
 *
 * focus is acquired by placing certain blocks around the table.
 *
 * */

//registered blocks that can be used to focus on a research.
data class FocusEntry(val block: Block, val researchCategory: ResearchCategory, val strength: Int, val opposes: Array<ResearchCategory>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FocusEntry

        if (block != other.block) return false
        if (researchCategory != other.researchCategory) return false
        if (strength != other.strength) return false
        if (!Arrays.equals(opposes, other.opposes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = block.hashCode()
        result = 31 * result + researchCategory.hashCode()
        result = 31 * result + strength
        result = 31 * result + Arrays.hashCode(opposes)
        return result
    }
}

val focusRegistry = HashMap<Block, FocusEntry>()

fun addResearchFocus(focusEntry: FocusEntry) {
    focusRegistry[focusEntry.block] = focusEntry
}
