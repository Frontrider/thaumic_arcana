package hu.frontrider.arcana.content.research

import hu.frontrider.arcana.Configuration
import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.content.research.theory.*
import net.minecraft.entity.passive.EntityAnimal
import net.minecraft.util.ResourceLocation
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import thaumcraft.api.research.ResearchCategories
import thaumcraft.api.research.ScanEntity
import thaumcraft.api.research.ScanningManager
import thaumcraft.api.research.theorycraft.TheorycraftManager

object ResearchRegistry {
    private val BACK_OVER = ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png")

    fun init() {

        initCategories()
        TheoryRegistry.init()
        initTheories()
        initScans()
        for (research in Configuration.research) {
            ThaumcraftApi.registerResearchLocation(ResourceLocation(research.toLowerCase()))
        }

        ThaumcraftApi.registerResearchLocation(ResourceLocation(MODID, "research/scans"))
    }

    internal fun initCategories() {
        ResearchCategories.registerCategory("BIOMANCY", "MINDBIOTHAUMIC", AspectList().add(Aspect.ALCHEMY, 30).add(Aspect.LIFE, 10).add(Aspect.MAGIC, 10).add(Aspect.LIGHT, 5).add(Aspect.AVERSION, 5).add(Aspect.EARTH, 5).add(Aspect.WATER, 5), ResourceLocation(MODID, "textures/research/cat_biomancy.png"), ResourceLocation("thaumcraft", "textures/gui/gui_research_back_7.jpg"), BACK_OVER)
    }

    internal fun initTheories() {
        TheorycraftManager.registerAid(AidTable())
        TheorycraftManager.registerCard(CardGrow::class.java)
        TheorycraftManager.registerCard(CardDissect::class.java)
        TheorycraftManager.registerCard(CardDissectDead::class.java)
        TheorycraftManager.registerCard(CardExperiment::class.java)
    }

    internal fun initScans() {
        ScanningManager.addScannableThing(ScanEntity("FLESH_GROWTH", EntityAnimal::class.java, true))
    }
}
