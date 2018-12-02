package hu.frontrider.thaumcraft.researchloader;


import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import java.util.ArrayList;
import java.util.List;

public class ResearchCategoryJson {
    class AspectNotation{
        private final String name;
        private final int amount;

        AspectNotation(
                String name,
                int amount){

            this.name = name;
            this.amount = amount;
        }
    }
    List<AspectNotation> aspects;
    String key;
    String required_research;
    String icon;
    String background;
    String background_overlay;

    public AspectList getAspects() {
        AspectList aspectList = new AspectList();
        for (AspectNotation aspect : aspects) {
            Aspect aspect1 = Aspect.getAspect(aspect.name);
            aspectList.merge(aspect1,aspect.amount);
        }
        return aspectList;
    }

    public ResearchCategoryJson setAspects(List<AspectNotation>aspects) {
        this.aspects = aspects;
        return this;
    }

    public String getKey() {
        return key;
    }

    public ResearchCategoryJson setKey(String key) {
        this.key = key;
        return this;
    }

    public String getRequired_research() {
        return required_research;
    }

    public ResearchCategoryJson setRequired_research(String required_research) {
        this.required_research = required_research;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public ResearchCategoryJson setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getBackground() {
        return background;
    }

    public ResearchCategoryJson setBackground(String background) {
        this.background = background;
        return this;
    }

    public String getBackground_overlay() {
        return background_overlay;
    }

    public ResearchCategoryJson setBackground_overlay(String background_overlay) {
        this.background_overlay = background_overlay;
        return this;
    }

    public ResearchCategoryJson setAspectList(AspectList aspectList){
        aspects = new ArrayList<>();

        for (Aspect aspect : aspectList.getAspects()) {
            int amount = aspectList.getAmount(aspect);
            aspects.add(new AspectNotation(aspect.getName(),amount));
        }

        return this;
    }
}
