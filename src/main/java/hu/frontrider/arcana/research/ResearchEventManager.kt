package hu.frontrider.arcana.research

import hu.frontrider.arcana.research.researchevents.PlantExperiments
import hu.frontrider.arcana.research.researchevents.StartingFires
import hu.frontrider.arcana.research.researchevents.TheScar
import net.minecraftforge.common.MinecraftForge

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
class ResearchEventManager {

    fun initHandlers() {
        registerhandler(StartingFires())
        registerhandler(PlantExperiments())
        registerhandler(TheScar())
    }

    internal fun registerhandler(handler: Any) {
        MinecraftForge.EVENT_BUS.register(handler)
    }
}
