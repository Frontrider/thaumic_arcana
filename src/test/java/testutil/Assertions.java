package testutil;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.opentest4j.AssertionFailedError;
import thaumcraft.api.aspects.Aspect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Assertions {



    public static void assertAspectNameValid(String aspect){
        Map<Integer, String> aspectmaching = new HashMap<>();

        for (String aspectName : Aspect.aspects.keySet()) {
            if(aspect.equals(aspectName))
                return;
            Integer distance = LevenshteinDistance.getDefaultInstance().apply(aspect, aspectName);
            aspectmaching.put(distance,aspectName);

        }
        List<String> matching = aspectmaching.entrySet()
                .stream()
                .filter(integerStringEntry -> integerStringEntry.getKey() < 7)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());


        throw new AssertionFailedError("Invalid aspect found: "+ aspect+" possible matches: "+matching);
    }


    public static void arrayContains(String[] array,String value){

    }
}
