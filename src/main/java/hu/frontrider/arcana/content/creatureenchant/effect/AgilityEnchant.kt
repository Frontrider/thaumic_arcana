package hu.frontrider.arcana.content.creatureenchant.effect

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.capabilities.creatureenchant.CreatureEnchantProvider
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.core.creatureenchant.EffectEnchantBase
import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.MobEffects
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.entity.living.LivingFallEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList

class AgilityEnchant :EffectEnchantBase(MobEffects.JUMP_BOOST,MobEffects.JUMP_BOOST,-2.0, ResourceLocation(MODID,"agility"),"agility") {

    companion object {
        @GameRegistry.ObjectHolder("$MODID:protection")
        lateinit var protection:CreatureEnchant
    }

    override val research: String
        get() ="CREATURE_ENCHANT_ADVANCED2"

    //reduces fall damage, if protection is not on.
    @SubscribeEvent
    fun fall(event: LivingFallEvent){
        val entityLiving = event.entityLiving
        val thisLevel = getEnchantLevel(entityLiving, this)
        val protLevel = getEnchantLevel(entityLiving, protection)

        if(thisLevel >0 && protLevel ==0)
            event.damageMultiplier =0f
    }

    override fun formula(): AspectList {
        return AspectList().merge(Aspect.FLIGHT,200)
                .merge(Aspect.EARTH,100)
                .merge(Aspect.MOTION,50)
                .merge(Aspect.ENTROPY,50)
    }

}