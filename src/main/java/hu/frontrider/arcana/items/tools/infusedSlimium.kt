package hu.frontrider.arcana.items.tools

import hu.frontrider.arcana.util.IInfuseable
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionEffect
import net.minecraft.potion.PotionUtils
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly


class InfusedSlimiumPickAxe : PickAxeBase("infused_slimy_pickaxe", TOOL_MATERIAL_INFUSED_SLIMIUM), IInfuseable {
    override fun hasEffect(stack: ItemStack): Boolean {
        return PotionUtils.getEffectsFromStack(stack).size >0
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: List<String>, flagIn: ITooltipFlag) {
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0f)
    }
}

class InfusedSlimiumAxe : AxeBase("infused_slimy_axe", TOOL_MATERIAL_INFUSED_SLIMIUM) , IInfuseable {
    override fun hasEffect(stack: ItemStack): Boolean {
        return PotionUtils.getEffectsFromStack(stack).size >0
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: List<String>, flagIn: ITooltipFlag) {
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0f)
    }
}

class InfusedSlimiumShovel : ShovelBase("infused_slimy_shovel", TOOL_MATERIAL_INFUSED_SLIMIUM), IInfuseable {
    override fun hasEffect(stack: ItemStack): Boolean {
        return PotionUtils.getEffectsFromStack(stack).size >0
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: List<String>, flagIn: ITooltipFlag) {
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0f)
    }
}

class InfusedSlimiumSword : SwordBase("infused_slimy_sword", TOOL_MATERIAL_INFUSED_SLIMIUM), IInfuseable {
    override fun hasEffect(stack: ItemStack): Boolean {
        return PotionUtils.getEffectsFromStack(stack).size >0
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: List<String>, flagIn: ITooltipFlag) {
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0f)
    }
}

class InfusedSlimiumHoe : HoeBase("infused_slimy_hoe", TOOL_MATERIAL_SLIMIUM), IInfuseable {
    override fun hasEffect(stack: ItemStack): Boolean {
        return PotionUtils.getEffectsFromStack(stack).size > 0
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: List<String>, flagIn: ITooltipFlag) {
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0f)
    }
}


fun makeInfusedTool(effects:Array<PotionEffect>,tool:ItemStack):ItemStack{
    return PotionUtils.appendEffects(tool, mutableListOf(*effects))
}


