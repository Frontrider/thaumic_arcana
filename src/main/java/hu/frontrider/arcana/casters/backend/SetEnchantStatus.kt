package hu.frontrider.arcana.casters.backend

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.ThaumicArcana.NETWORK_WRAPPER
import hu.frontrider.arcana.capabilities.creatureenchant.CreatureEnchantProvider.Companion.CREATURE_ENCHANT_CAPABILITY
import hu.frontrider.arcana.sided.network.creatureenchants.CreatureEnchantSyncMessage
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.NetworkRegistry
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.casters.FocusEffect
import thaumcraft.api.casters.Trajectory

open class SetEnchantStatus(private val researchName: String, private val name: String, private val status: Boolean, private val requiredAspect: Aspect) : FocusEffect() {

    override fun execute(rayTraceResult: RayTraceResult, trajectory: Trajectory?, v: Float, i: Int): Boolean {

        val entityHit = rayTraceResult.entityHit
        if (entityHit.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            val capability = entityHit.getCapability(CREATURE_ENCHANT_CAPABILITY, null)!!
            capability.setEnabledStatus(status)
            val entityId = entityHit.entityId

            NETWORK_WRAPPER.sendToAllAround(CreatureEnchantSyncMessage(capability, entityId),
                    NetworkRegistry.TargetPoint(entityHit.dimension,entityHit.posX,entityHit.posY,entityHit.posZ,128.0)
            )

            return true
        }
        return false
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
        return "$MODID.$name"
    }

    override fun getResearch(): String? {
        return researchName
    }
}
