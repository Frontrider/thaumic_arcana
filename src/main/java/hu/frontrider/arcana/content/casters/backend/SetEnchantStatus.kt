package hu.frontrider.arcana.content.casters.backend

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.capabilities.CreatureEnchantProvider.Companion.CREATURE_ENCHANT_CAPABILITY
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.casters.FocusEffect
import thaumcraft.api.casters.Trajectory

open class SetEnchantStatus(private val researchName:String, private val name:String, private val status: Boolean, private val requiredAspect: Aspect) : FocusEffect() {

    override fun execute(rayTraceResult: RayTraceResult, trajectory: Trajectory?, v: Float, i: Int): Boolean {

        val selectors = ArrayList<EnchantSelectorEffect>()
        `package`.nodes.forEach {
            if (it is EnchantSelectorEffect)
                selectors.add(it)
        }
        if (selectors.size == 0)
            return false

        supplyTargets().iterator().forEachRemaining { traceResult ->
            val entityHit = traceResult.entityHit
            if (entityHit != null && entityHit.hasCapability(CREATURE_ENCHANT_CAPABILITY!!, null)) {
                val capability = entityHit.getCapability(CREATURE_ENCHANT_CAPABILITY!!, null)!!
                selectors.forEach {
                    capability.setEnabledStatus(it.enchant, status)
                }
            }
        }

        return true
    }

    override fun renderParticleFX(world: World, v: Double, v1: Double, v2: Double, v3: Double, v4: Double, v5: Double) {

    }

    override fun getComplexity(): Int {
        return 4
    }

    override fun getAspect(): Aspect? {
        return requiredAspect
    }

    override fun getKey(): String? {
        return "$MODID:$name"
    }

    override fun getResearch(): String? {
        return researchName
    }

}
