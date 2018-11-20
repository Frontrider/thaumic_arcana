package hu.frontrider.arcana.items.curio

import hu.frontrider.arcana.items.ItemBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.capabilities.IPlayerKnowledge
import thaumcraft.api.research.ResearchCategories

class AnimatedCuriosity : ItemBase("animated_curiosity") {

    override fun onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        onItemRightClick(worldIn,player,hand)
        return EnumActionResult.SUCCESS
    }

    override fun onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult<ItemStack> {
        if (!worldIn.isRemote) {
            val oProg = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.progression
            val tProg = IPlayerKnowledge.EnumKnowledgeType.THEORY.progression
            when(worldIn.rand.nextInt(5)){
                1->{
                    ThaumcraftApi.internalMethods.addKnowledge(playerIn, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("BASIC"), MathHelper.getInt(playerIn.rng, oProg / 2, oProg))
                    ThaumcraftApi.internalMethods.addKnowledge(playerIn, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("BASIC"), MathHelper.getInt(playerIn.rng, tProg / 3, tProg / 2))
                }
                2->{
                    ThaumcraftApi.internalMethods.addKnowledge(playerIn, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("GOLEMANCY"), MathHelper.getInt(playerIn.rng, oProg / 2, oProg))
                    ThaumcraftApi.internalMethods.addKnowledge(playerIn, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("GOLEMANCY"), MathHelper.getInt(playerIn.rng, tProg / 3, tProg / 2))
                }
                else->{
                    ThaumcraftApi.internalMethods.addKnowledge(playerIn, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("GOLEMANCY"), MathHelper.getInt(playerIn.rng, tProg / 3, tProg / 2))
                }
            }
            playerIn.getHeldItem(handIn).shrink(1)
        }
        return super.onItemRightClick(worldIn, playerIn, handIn)
    }
}