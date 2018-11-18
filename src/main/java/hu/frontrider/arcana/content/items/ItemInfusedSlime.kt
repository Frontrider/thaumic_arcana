package hu.frontrider.arcana.content.items

import hu.frontrider.arcana.ThaumicArcana
import hu.frontrider.arcana.content.AspectEffectMap
import hu.frontrider.arcana.content.blocks.effect.tiles.TileEssentiaMine
import hu.frontrider.arcana.util.strings.ChatFormat
import hu.frontrider.arcana.util.strings.formatString
import net.minecraft.block.Block
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.aspects.Aspect

class ItemInfusedSlime : Item() {

    override fun onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        var pos = pos
        val iblockstate = worldIn.getBlockState(pos)
        val block = iblockstate.block

        if (!block.isReplaceable(worldIn, pos)) {
            pos = pos.offset(facing)
        }
        if (worldIn.getBlockState(pos.down()).isFullBlock) {

            val itemstack = player.getHeldItem(hand)
            if (!itemstack.hasTagCompound())
                return EnumActionResult.FAIL

            //check if the slime has an effect associated with it.
            val tag = itemstack.tagCompound!!.getString("aspect")
            if (!AspectEffectMap.map.containsKey(Aspect.getAspect(tag)))
                return EnumActionResult.FAIL

            worldIn.setBlockState(pos, essentia_mine.defaultState)
            (worldIn.getTileEntity(pos) as TileEssentiaMine).setAspect(tag)

            return EnumActionResult.SUCCESS
        }

        return EnumActionResult.FAIL
    }

    override fun getHasSubtypes(): Boolean {
        return true
    }

    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (tab === ThaumicArcana.TABARCANA) {
            Aspect.aspects.forEach { _, aspect ->
                items.add(createSlimeFor(aspect, this))
            }
        }
    }

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        super.addInformation(stack, worldIn, tooltip, flagIn)
        val nbtTagCompound = stack.tagCompound ?: return
        if (nbtTagCompound.hasKey("aspect")) {
            val aspect = Aspect.getAspect(nbtTagCompound.getString("aspect"))
            tooltip.add(formatString(I18n.format("thaumic_arcana.tooltip.infused_with", aspect.tag), ChatFormat.AQUA))
        }
    }

    companion object {
        fun createSlimeFor(aspect: Aspect, item: Item): ItemStack {
            val itemStack = ItemStack(item)
            val nbtTagCompound = NBTTagCompound()
            nbtTagCompound.setString("aspect", aspect.tag)
            itemStack.tagCompound = nbtTagCompound
            return itemStack
        }

        @GameRegistry.ObjectHolder("${ThaumicArcana.MODID}:essentia_mine")
        lateinit var essentia_mine: Block
    }

}