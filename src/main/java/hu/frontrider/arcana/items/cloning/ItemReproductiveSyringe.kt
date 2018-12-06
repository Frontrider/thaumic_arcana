package hu.frontrider.arcana.items.cloning

import hu.frontrider.arcana.ThaumicArcana.MODID
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.passive.EntityAnimal
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumHand
import net.minecraftforge.fml.common.registry.GameRegistry

class ItemReproductiveSyringe : Item() {

    companion object {
        @GameRegistry.ObjectHolder("$MODID:reproductive_material")
        lateinit var reproductive_material:Item
    }
    init {

    }

    override fun itemInteractionForEntity(stack: ItemStack, playerIn: EntityPlayer, target: EntityLivingBase, hand: EnumHand): Boolean {

        //check if the right clicked animal is in the mood
        if(target is EntityAnimal){
            if(target.isInLove)
            {
                //get the other hand
                val correctHand = if(hand == EnumHand.MAIN_HAND) EnumHand.OFF_HAND else EnumHand.MAIN_HAND
                val bottle = playerIn.getHeldItem(correctHand)
                if(bottle.item == Items.GLASS_BOTTLE)
                {
                    bottle.shrink(1)

                    val reprMaterial = makeBottle(target)
                    if(!playerIn.inventory.addItemStackToInventory(reprMaterial)){
                        playerIn.apply {
                            world.spawnEntity(EntityItem(world,posX,posY,posZ,reprMaterial))
                        }
                    }
                    return true
                }
            }
        }
        return false
    }

    fun makeBottle(animal:EntityAnimal): ItemStack {
        val itemStack = ItemStack(reproductive_material)
        val nbtTagCompound = NBTTagCompound()

        val uniqueIdString = animal.cachedUniqueIdString.split("-")
        val unique = NBTTagCompound()

        unique.apply {
            for ((index, it) in uniqueIdString.withIndex()) {
                setString("id$index",it)
            }
        }

        val animalNbt = animal.writeToNBT(NBTTagCompound())
        nbtTagCompound.apply {
            setTag("unique",unique)
            setTag("animal",animalNbt)
        }

        itemStack.tagCompound = nbtTagCompound
        return itemStack
    }

}