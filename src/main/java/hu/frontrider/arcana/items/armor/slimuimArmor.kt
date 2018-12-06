package hu.frontrider.arcana.items.armor

import hu.frontrider.arcana.util.IInfusedArmor
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionEffect
import net.minecraft.potion.PotionUtils
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class SlimiumArmor(slot: EntityEquipmentSlot) : ArmorBase(ARMOR_MATERIAL_INFUSED_SLIME, 0, slot), IInfusedArmor {
    override fun onArmorTick(world: World, player: EntityPlayer, itemStack: ItemStack) {

        val effectsFromStack = PotionUtils.getEffectsFromStack(itemStack)
        val newEffects = ArrayList<PotionEffect>()

        effectsFromStack.forEach {
            val duration = it.duration - 1
            val potionEffect = if (duration > 0) {
                newEffects.add(PotionEffect(it.potion, duration, it.amplifier))
                PotionEffect(it.potion, 1, it.amplifier)
            } else {
                PotionEffect(it.potion, 1 + duration, it.amplifier)
            }
            player.addPotionEffect(potionEffect)
        }

        PotionUtils.appendEffects(itemStack, newEffects)
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: List<String>, flagIn: ITooltipFlag) {
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0f)
    }

    override fun hasEffect(stack: ItemStack): Boolean {
        return PotionUtils.getEffectsFromStack(stack).size > 0
    }
}

fun makeInfusedArmor(effects: Array<PotionEffect>, armor: ItemStack): ItemStack {
    val newEffects = effects.map {
        PotionEffect(it.potion, it.duration * 5, it.amplifier)
    }
    return PotionUtils.appendEffects(armor, newEffects)
}