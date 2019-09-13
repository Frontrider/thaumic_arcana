package hu.frontrider.arcana.research

import hu.frontrider.arcana.research.theory.*
import net.minecraft.entity.passive.EntityAnimal
import thaumcraft.api.research.ResearchEvent
import thaumcraft.api.research.ScanEntity
import thaumcraft.api.research.ScanItem
import thaumcraft.api.research.ScanningManager
import thaumcraft.api.research.theorycraft.TheorycraftManager

class ResearchRegistry {

    fun init() {
        TheoryRegistry().init()
        initTheories()
        initScans()
    }

    private fun initTheories() {

        TheorycraftManager.registerAid(AidTable())
        TheorycraftManager.registerCard(CardGrow::class.java)
        TheorycraftManager.registerCard(CardDissect::class.java)
        TheorycraftManager.registerCard(CardDissectDead::class.java)
    }

    private fun initScans() {
        ScanningManager.addScannableThing(ScanEntity("FLESH_GROWTH", EntityAnimal::class.java, true))
    }

    private fun initResearch(){

    }
}
