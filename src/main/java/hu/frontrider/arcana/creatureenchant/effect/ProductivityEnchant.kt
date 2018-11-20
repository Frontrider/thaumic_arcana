package hu.frontrider.arcana.creatureenchant.effect

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.util.items.contains
import net.minecraft.entity.item.EntityItem
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.entity.living.LivingDropsEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

class ProductivityEnchant : CreatureEnchant(ResourceLocation(MODID, "productivity"), "productivity") {


    override val research: String
        get() = "CREATURE_ENCHANT_ADVANCED2"

    @SubscribeEvent
    fun entityDrop(event: LivingDropsEvent) {
        val entity = event.entityLiving

        val enchantLevel = this.getEnchantLevel(entity, this)
        if (enchantLevel != 0) {
            if (enchantLevel > 0) {
                if (entity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
                    val iItemHandler = entity.getCapability(ITEM_HANDLER_CAPABILITY, null)!!

                    for (it in event.drops) {
                        if (!entity.armorInventoryList.contains(it.item) && !iItemHandler.contains(it.item)) {
                            for (i in 0 until enchantLevel) {
                                    it.world.spawnEntity(EntityItem(it.world, it.posX, it.posY, it.posZ, it.item))
                            }
                        }
                    }
                } else {
                    for (it in event.drops) {
                        if (!entity.armorInventoryList.contains(it.item)) {
                            for (i in 0 until enchantLevel) {
                                    it.world.spawnEntity(EntityItem(it.world, it.posX, it.posY, it.posZ, it.item))
                            }
                        }
                    }
                }
            }
            if (enchantLevel < 0) {
                event.drops.forEach {
                    it.world.removeEntity(it)
                }
            }

        }
    }

    override fun formula(): AspectList {
        return AspectList()
                .merge(Aspect.LIFE, 300)
                .merge(Aspect.EARTH, 30)
                .merge(Aspect.MAGIC, 500)
                .merge(Aspect.PLANT, 10)
    }
}
