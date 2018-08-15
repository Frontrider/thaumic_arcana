package testutil;

import org.opentest4j.AssertionFailedError;
import thaumcraft.api.aspects.Aspect;

public class Assertions {

    public static void assertAspectNameValid(String aspect){
        for (String aspectName : Aspect.aspects.keySet()) {
            if(aspect.equals(aspectName))
                return;
        }
        throw new AssertionFailedError("Invalid aspect found: "+ aspect);
    }

}
