package hu.frontrider.arcana.content.research

import hu.frontrider.arcana.content.research.theory.*
import net.minecraft.entity.passive.EntityAnimal
import net.minecraft.util.ResourceLocation
import thaumcraft.api.research.ScanEntity
import thaumcraft.api.research.ScanningManager
import thaumcraft.api.research.theorycraft.TheorycraftManager

class ResearchRegistry {
    private val BACK_OVER = ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png")

    fun init() {
        TheoryRegistry().init()
        initTheories()
        initScans()
    }


    internal fun initTheories() {
        TheorycraftManager.registerAid(AidTable())
        TheorycraftManager.registerCard(CardGrow::class.java)
        TheorycraftManager.registerCard(CardDissect::class.java)
        TheorycraftManager.registerCard(CardDissectDead::class.java)
    }

    internal fun initScans() {
        ScanningManager.addScannableThing(ScanEntity("FLESH_GROWTH", EntityAnimal::class.java, true))
    }
}
