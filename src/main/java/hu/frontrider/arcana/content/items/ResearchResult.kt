package hu.frontrider.arcana.content.items

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.capabilities.IPlayerKnowledge
import thaumcraft.api.research.ResearchCategories
import thaumcraft.api.research.ResearchCategory

class ResearchResult : ItemBase("research_result") {

    override fun onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        val heldItem = player.getHeldItem(hand)

        val tagCompound = heldItem.tagCompound ?: return EnumActionResult.FAIL

        //check if the players are matching. it is set when the experiment starts.
        val nbtTagList = tagCompound.getTag("research") as NBTTagList
        val playerName = tagCompound.getString("player")

        if(player.persistentID.toString() != playerName)
            return EnumActionResult.FAIL


        val oProg = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.progression
        val tProg = IPlayerKnowledge.EnumKnowledgeType.THEORY.progression

        nbtTagList.forEach {
            val nbtTagCompound = it as NBTTagCompound
            val key = nbtTagCompound.getString("research")
            val research = ResearchCategories.getResearchCategory(key)

            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, research, MathHelper.getInt(player.rng, tProg / 2, tProg))
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, research, MathHelper.getInt(player.rng, oProg / 2, oProg))
        }

        return EnumActionResult.SUCCESS
    }

    companion object {

        fun setResearch(researchCategory: Array<ResearchCategory>, itemStack: ItemStack) {
            val nbtList = NBTTagList()
            researchCategory.forEach {
                val nbtTagCompound = NBTTagCompound()
                nbtTagCompound.setString("key", it.key)
                nbtList.appendTag(nbtTagCompound)
            }

            val nbtTagCompound = itemStack.tagCompound ?: NBTTagCompound()

            nbtTagCompound.setTag("research", nbtList)

            itemStack.tagCompound = nbtTagCompound

        }
    }
}