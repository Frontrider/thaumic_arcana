package hu.frontrider.arcana.research

import hu.frontrider.arcana.research.researchevents.StartingFires
import net.minecraftforge.common.MinecraftForge

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
object ResearchEventManager {

    fun initHandlers() {
        registerhandler(StartingFires())
    }

    internal fun registerhandler(handler: Any) {
        MinecraftForge.EVENT_BUS.register(handler)
    }
}
