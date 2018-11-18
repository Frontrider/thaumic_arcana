package hu.frontrider.arcana.core.eventhandlers

import hu.frontrider.arcana.ThaumicArcana
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.living.LivingDropsEvent
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.event.world.BlockEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.capabilities.ThaumcraftCapabilities
import thaumcraft.api.casters.ICaster

class CurioDropEvents {

    //add the organic curio to the drops.
    @SubscribeEvent
    fun entityDrop(event: LivingDropsEvent) {
        val source = (event.source.trueSource ?: return)

        if(source !is EntityPlayer && source !is EntityPlayer)
            return
        val knowsResearchStrict = ThaumcraftCapabilities.knowsResearch(source, "TA_RESEARCH")
        if (knowsResearchStrict) {
            val entity = event.entity
            val world = entity.world

            if(world.rand.nextInt(10)<=1){
                return
            }

            val item = EntityItem(world, entity.posX, entity.posY, entity.posZ)
            val itemStack = ItemStack(organic_curio, world.rand.nextInt(2))
            item.item = itemStack
            world.spawnEntity(item)
        }
    }


    companion object {

        @GameRegistry.ObjectHolder("${ThaumicArcana.MODID}:organic_curiosity")
       private lateinit var organic_curio: Item


    }
}