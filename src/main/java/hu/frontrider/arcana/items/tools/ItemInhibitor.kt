package hu.frontrider.arcana.items.tools

import hu.frontrider.arcana.ThaumicArcana
import hu.frontrider.arcana.capabilities.inhibitor.InhibitorProvider
import hu.frontrider.arcana.capabilities.inhibitor.InhibitorProvider.Companion.INHIBITOR_CAPABILITY
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumHand
import net.minecraftforge.fml.common.registry.GameRegistry

class ItemInhibitor: Item() {
    companion object {

        @GameRegistry.ObjectHolder("${ThaumicArcana.MODID}:experience_store")
        lateinit var experience_store: Item

    }

    override fun itemInteractionForEntity(stack: ItemStack, playerIn: EntityPlayer, target: EntityLivingBase, hand: EnumHand): Boolean {
        if(target is EntityLiving){
            if(target.hasCapability(InhibitorProvider.INHIBITOR_CAPABILITY, null)) {
                val capability = target.getCapability(INHIBITOR_CAPABILITY, null)!!
                capability.inhibited = true
                target.tasks.taskEntries.clear()
                playerIn.setHeldItem(hand,ItemStack(experience_store))
                return true
            }
        }
        return false
    }

}