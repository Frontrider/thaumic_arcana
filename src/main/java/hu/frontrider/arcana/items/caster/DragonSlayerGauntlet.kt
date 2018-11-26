package hu.frontrider.arcana.items.caster

import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage
import net.minecraftforge.items.CapabilityItemHandler

//drains all of the forge energy from the attacked entity. Good luck keeping your shields up with no power.
class DragonSlayerGauntlet(area:Int):BasicBattleGauntlet(area,ToolMaterial.DIAMOND) {

    override fun hitEntity(stack: ItemStack, target: EntityLivingBase, attacker: EntityLivingBase): Boolean {
        //check if the entity is powered
        if(target.hasCapability(CapabilityEnergy.ENERGY,null)){
            //if it does, drain everything.
            val capability = target.getCapability(CapabilityEnergy.ENERGY, null)!!
            drain(capability)
        }
        if(target.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,null)){
            //if it does, drain everything.
            val capability = target.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)!!
            for(i in 0 until capability.slots){
                val stackInSlot = capability.getStackInSlot(i)
                if(stackInSlot.hasCapability(CapabilityEnergy.ENERGY,null)){
                    val energy = stackInSlot.getCapability(CapabilityEnergy.ENERGY, null)!!
                    drain(energy)
                }
            }
        }
        return true
    }

    fun drain(capability: IEnergyStorage){
        capability.extractEnergy(capability.energyStored,false)
    }
}