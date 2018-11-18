package hu.frontrider.thaumcraft.researchloader.util;

import hu.frontrider.thaumcraft.researchloader.crafting.RawAspect;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class Aspects {

    public static AspectList parseAspects(AspectList aspectList, RawAspect aspect){
        Aspect aspect1 = Aspect.getAspect(aspect.getTag());
        return aspectList.merge(aspect1,aspect.getAmount());
    }
}
