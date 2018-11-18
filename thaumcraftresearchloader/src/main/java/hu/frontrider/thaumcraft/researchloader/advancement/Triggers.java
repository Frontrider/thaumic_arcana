package hu.frontrider.thaumcraft.researchloader.advancement;

import thaumcraft.api.research.ResearchCategories;

import java.util.ArrayList;
import java.util.List;

public class Triggers {
    /*
     * Stores all the research advancements, that are generated dynamically from the registered research.
     */
    public static final List<ResearchAdvancement> TRIGGER_ARRAY = new ArrayList<>();

    public static void init() {
        ResearchCategories.researchCategories.values()
                .stream()
                .flatMap(category->category.research.values().stream())
                .forEach(entry->{
                    for (int i = 0; i < entry.getStages().length; i++) {
                        TRIGGER_ARRAY.add(new ResearchAdvancement(entry.getKey()+"_"+i));
                    }
                });
    }
}