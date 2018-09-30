package hu.frontrider.arcana.content.items.tools

import hu.frontrider.arcana.ThaumicArcana.TOOL_MATERIAL_LIVIUM
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World

class LiviumPickAxe : PickAxeBase("livium_pickaxe", TOOL_MATERIAL_LIVIUM) {
    override fun onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult<ItemStack> {
        if (worldIn.isRemote)
            if (leech(playerIn, playerIn.getHeldItem(handIn))) return ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn)) else ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn))
        return super.onItemRightClick(worldIn, playerIn, handIn)
    }
}

class LiviumAxe : AxeBase("livium_axe", TOOL_MATERIAL_LIVIUM) {
    override fun onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult<ItemStack> {
        if (worldIn.isRemote)
            if (leech(playerIn, playerIn.getHeldItem(handIn))) return ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn)) else ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn))
        return super.onItemRightClick(worldIn, playerIn, handIn)
    }
}

class LiviumShovel : ShovelBase("livium_shovel", TOOL_MATERIAL_LIVIUM) {
    override fun onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult<ItemStack> {
        if (worldIn.isRemote)
            if (leech(playerIn, playerIn.getHeldItem(handIn))) return ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn)) else ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn))
        return super.onItemRightClick(worldIn, playerIn, handIn)
    }
}

class LiviumSword : SwordBase("livium_sword", TOOL_MATERIAL_LIVIUM) {
    override fun onItemRightClick(worldIn: World, playerIn: EntityPlayer, handIn: EnumHand): ActionResult<ItemStack> {
        if (worldIn.isRemote)
            if (leech(playerIn, playerIn.getHeldItem(handIn))) return ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn)) else ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn))
        return super.onItemRightClick(worldIn, playerIn, handIn)
    }
}

private fun leech(playerIn: EntityPlayer, stack: ItemStack): Boolean {
    val foodLevel = playerIn.foodStats.foodLevel
    if (foodLevel <= 0)
        return false

    if (stack.itemDamage + 25 > stack.maxDamage)
        return false

    playerIn.foodStats.foodLevel--
    stack.itemDamage = stack.itemDamage+25
    return true
}