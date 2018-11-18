package hu.frontrider.arcana.content

import net.minecraft.init.MobEffects
import net.minecraft.potion.Potion
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.Aspect.*

object AspectEffectMap {

    @GameRegistry.ObjectHolder("thaumcraft:sunScorned")
    lateinit var sunScorned: Potion
    @GameRegistry.ObjectHolder("thaumcraft:thaumarhia")
    lateinit var thaumarhia: Potion
    @GameRegistry.ObjectHolder("thaumcraft:infectiousVisExhaust")
    lateinit var infectiousVisExhaust: Potion
    @GameRegistry.ObjectHolder("thaumcraft:blurredVision")
    lateinit var blurredVision: Potion
    @GameRegistry.ObjectHolder("thaumcraft:deathGaze")
    lateinit var deathGaze: Potion
    @GameRegistry.ObjectHolder("thaumcraft:unnaturalHunger")
    lateinit var unnaturalHunger: Potion

    lateinit var map: Map<Aspect, EffectEntry>

    fun init() {
        map = mapOf(
                Pair(AIR, EffectEntry(MobEffects.WATER_BREATHING)),
                Pair(VOID,EffectEntry( MobEffects.UNLUCK)),
                Pair(LIGHT, EffectEntry(MobEffects.GLOWING)),
                Pair(MOTION, EffectEntry(MobEffects.SPEED)),
                Pair(COLD,EffectEntry( infectiousVisExhaust)),
                Pair(LIFE, EffectEntry(MobEffects.INSTANT_HEALTH)),
                Pair(DEATH,EffectEntry( MobEffects.WITHER)),
                Pair(ENERGY, EffectEntry(MobEffects.ABSORPTION)),
                Pair(EXCHANGE, EffectEntry(MobEffects.JUMP_BOOST)),
                Pair(MAGIC, EffectEntry(MobEffects.HEALTH_BOOST)),
                Pair(AURA, EffectEntry(sunScorned)),
                Pair(FLUX, EffectEntry(MobEffects.WITHER)),
                Pair(DARKNESS, EffectEntry(MobEffects.BLINDNESS,1000)),
                Pair(ELDRITCH, EffectEntry(thaumarhia)),
                Pair(FLIGHT,EffectEntry( MobEffects.LEVITATION)),
                Pair(PLANT,EffectEntry( MobEffects.NAUSEA)),
                Pair(TOOL,EffectEntry( MobEffects.HASTE)),
                Pair(TRAP,EffectEntry( MobEffects.SLOWNESS)),
                Pair(SOUL,EffectEntry( blurredVision)),
                Pair(METAL,EffectEntry( MobEffects.FIRE_RESISTANCE)),
                Pair(SENSES, EffectEntry(MobEffects.NIGHT_VISION)),
                Pair(AVERSION, EffectEntry(deathGaze,1000)),
                Pair(PROTECT, EffectEntry(MobEffects.RESISTANCE)),
                Pair(DESIRE, EffectEntry(MobEffects.LUCK)),
                Pair(UNDEAD, EffectEntry(unnaturalHunger)),
                Pair(MAN,EffectEntry( MobEffects.STRENGTH)),
                Pair(BEAST, EffectEntry(MobEffects.SATURATION)),
                Pair(MIND, EffectEntry(MobEffects.MINING_FATIGUE))
        )
    }

    data class EffectEntry(val potion:Potion,val duration:Int=2000)

}