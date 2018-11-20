package hu.frontrider.arcana.creatureenchant.effect

import hu.frontrider.arcana.creatureenchant.EffectEnchantBase
import net.minecraft.init.MobEffects
import net.minecraft.util.ResourceLocation
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.creatureenchant.CreatureEnchant
import net.minecraft.potion.PotionEffect
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.event.entity.living.LivingFallEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry

class Speed : EffectEnchantBase(MobEffects.SPEED, MobEffects.SLOWNESS, -1.0, ResourceLocation(MODID, "speed"), "speed") {

    companion object {
        @GameRegistry.ObjectHolder("$MODID:protection")
        lateinit var protection:CreatureEnchant


        @GameRegistry.ObjectHolder("$MODID:strength")
        lateinit var strength:CreatureEnchant
    }


    override val research: String
        get() = "CREATURE_ENCHANT_ADVANCED"

    //incompatible with protection, hunger.
    @SubscribeEvent
    override fun tick(event: LivingEvent.LivingUpdateEvent) {
        super.tick(event)
        val entity = event.entityLiving
        val enchantLevel = getEnchantLevel(entity, protection)

        val effect = MobEffects.HUNGER
        if (enchantLevel > 0) {
            if (entity.getActivePotionEffect(effect) == null || entity.getActivePotionEffect(effect)!!.duration < 30)
                entity.addPotionEffect(PotionEffect(effect, 240, enchantLevel - 1, true, false))
        }
    }

    //incompatible with strength, increased fall damage
    @SubscribeEvent
    fun fall(event: LivingFallEvent){
        val entityLiving = event.entityLiving
        val thisLevel = getEnchantLevel(entityLiving, this)
        val strengthLevel = getEnchantLevel(entityLiving, strength)

        if(thisLevel >0 && strengthLevel >0)
            event.damageMultiplier *= thisLevel
    }


    override fun formula(): AspectList {
        return AspectList()
                .merge(Aspect.SENSES, 20)
                .merge(Aspect.ENERGY, 100)
                .merge(Aspect.MAGIC, 20)
    }


}
