package hu.frontrider.arcana.research;

import hu.frontrider.arcana.Configuration;
import hu.frontrider.arcana.research.theory.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ScanEntity;
import thaumcraft.api.research.ScanningManager;
import thaumcraft.api.research.theorycraft.TheorycraftManager;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class ResearchRegistry {
    private static final ResourceLocation BACK_OVER = new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png");

    public static void init() {



        initCategories();
        TheoryRegistry.init();
        initTheories();
        initScans();
        for (String research :
                Configuration.research) {
            ThaumcraftApi.registerResearchLocation(new ResourceLocation(research.toLowerCase()));
        }

        ThaumcraftApi.registerResearchLocation(new ResourceLocation(MODID, "research/scans"));
    }

    static void initCategories() {
        ResearchCategories.registerCategory("BIOMANCY", "MINDBIOTHAUMIC", (new AspectList()).add(Aspect.ALCHEMY, 30).add(Aspect.LIFE, 10).add(Aspect.MAGIC, 10).add(Aspect.LIGHT, 5).add(Aspect.AVERSION, 5).add(Aspect.EARTH, 5).add(Aspect.WATER, 5), new ResourceLocation(MODID, "textures/research/cat_biomancy.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_7.jpg"), BACK_OVER);
    }

    static void initTheories() {
        TheorycraftManager.registerAid(new AidTable());
        TheorycraftManager.registerCard(CardGrow.class);
        TheorycraftManager.registerCard(CardDissect.class);
        TheorycraftManager.registerCard(CardDissectDead.class);
        TheorycraftManager.registerCard(CardExperiment.class);
    }

    static void initScans() {
        ScanningManager.addScannableThing(new ScanEntity("FLESH_GROWTH", EntityAnimal.class, true));
    }
}
