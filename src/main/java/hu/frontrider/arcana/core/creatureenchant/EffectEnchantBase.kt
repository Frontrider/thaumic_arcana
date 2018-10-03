package hu.frontrider.arcana.core.creatureenchant

import net.minecraft.potion.Potion
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

abstract class EffectEnchantBase(private val positive: Potion, private val negative: Potion, private val negativeMultiplier: Double,resourceLocation: ResourceLocation, unlocalisedName: String) : CreatureEnchant(resourceLocation, unlocalisedName) {

    constructor(positive: Potion,negative: Potion,negativeMultiplier: Double, resourceLocation: ResourceLocation):this(positive,negative,negativeMultiplier,resourceLocation,resourceLocation.resourcePath)

    @SubscribeEvent
    open fun tick(event: LivingEvent.LivingUpdateEvent) {

        val entity = event.entityLiving
        val enchantLevel = getEnchantLevel(entity, this)

        if (enchantLevel > 0) {
            if (entity.getActivePotionEffect(positive) == null || entity.getActivePotionEffect(positive)!!.duration < 30)
                entity.addPotionEffect(PotionEffect(positive, 240, enchantLevel - 1, true, false))
        }

        if (enchantLevel < 0) {
            if (entity.getActivePotionEffect(negative) == null || entity.getActivePotionEffect(negative)!!.duration < 30)
                entity.addPotionEffect(PotionEffect(negative, 240, ((enchantLevel + 1) * negativeMultiplier).toInt(), true, false))
        }
    }

}
