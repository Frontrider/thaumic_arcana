package hu.frontrider.arcana.util;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import java.util.Collection;
import java.util.Random;


public class AspectUtil {

    public static boolean aspectEquals(Aspect aspect1, Aspect aspect2) {
        return aspect1.getTag().equals(aspect2.getTag());
    }

    public static boolean aspectListEquals(AspectList aspect1, AspectList aspect2) {

        if (aspect1.aspects.size() != aspect2.aspects.size())
            return false;

        return aspect1.aspects.entrySet().stream().map(aspectIntegerEntry -> {
            Aspect key = aspectIntegerEntry.getKey();
            if (!aspect2.aspects.containsKey(key))
                return false;

            int amount = aspect2.aspects.get(key);
            return amount == aspectIntegerEntry.getValue();

        }).reduce(true, (aBoolean, aBoolean2) -> aBoolean && aBoolean2);

    }

    public static Aspect getRandom(){
        Collection<Aspect> aspects = Aspect.aspects.values();
         return (Aspect) aspects.toArray()[new Random().nextInt(aspects.size())];
    }

}
