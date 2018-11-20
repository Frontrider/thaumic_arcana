package hu.frontrider.arcana.research.researchevents

import hu.frontrider.arcana.worldgen.generators.taintwine.TaintWineGenerator
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import thaumcraft.api.capabilities.ThaumcraftCapabilities
import thaumcraft.api.research.ResearchEvent

class PlantExperiments {

    val genrator = TaintWineGenerator()

    @SubscribeEvent
    fun fires(event: ResearchEvent.Research) {
        val player = event.player
        val researchKey = event.researchKey
        if (researchKey == "PLANT_EXPERIMENTS") {
            val capability = player.getCapability(ThaumcraftCapabilities.KNOWLEDGE, null)
            val researchStage = capability!!.getResearchStage(researchKey)
            if (researchStage == 1) {
                spawnTaint(player.entityWorld, player.position)
            }
        }
    }

    fun spawnTaint(world: World, player:BlockPos){
        genrator.generate(world,world.rand,player)
    }
}