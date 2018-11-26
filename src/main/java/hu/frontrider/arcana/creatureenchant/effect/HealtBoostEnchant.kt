package hu.frontrider.arcana.creatureenchant.effect

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.creatureenchant.EffectEnchantBase
import net.minecraft.init.MobEffects
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

class HealtBoostEnchant:EffectEnchantBase(MobEffects.HEALTH_BOOST,MobEffects.HEALTH_BOOST,-2.0, ResourceLocation(MODID,"health_boost"),"health_boost") {

    companion object {
        @GameRegistry.ObjectHolder("$MODID:protection")
        lateinit var protection: CreatureEnchant

        @GameRegistry.ObjectHolder("$MODID:speed")
        lateinit var speed:CreatureEnchant
    }

    override val research: String
        get() = "CREATURE_ENCHANT_ADVANCED2"

    //incompatible with speed and protection.
    @SubscribeEvent
    override fun tick(event: LivingEvent.LivingUpdateEvent) {
        super.tick(event)
        val entity = event.entityLiving
        val enchantLevel = getEnchantLevel(entity, protection)
        val speedLevel = getEnchantLevel(entity, speed)

        val thisLevel = getEnchantLevel(entity, this)
        if (speedLevel>0 && thisLevel != 0) {
            if (entity.getActivePotionEffect(MobEffects.HUNGER) == null || entity.getActivePotionEffect(MobEffects.HUNGER)!!.duration < 30)
                entity.addPotionEffect(PotionEffect(MobEffects.HUNGER, 240, speedLevel*3, true, false))
        }

        if (enchantLevel>0 && thisLevel != 0) {
            if (entity.getActivePotionEffect(MobEffects.WITHER) == null || entity.getActivePotionEffect(MobEffects.WITHER)!!.duration < 30)
                entity.addPotionEffect(PotionEffect(MobEffects.WITHER, 240, enchantLevel, true, false))
        }
    }
    override fun formula(): AspectList {
        return AspectList().merge(Aspect.LIFE,600)
                .merge(Aspect.ORDER,150)
                .merge(Aspect.MAGIC,300)
    }
}