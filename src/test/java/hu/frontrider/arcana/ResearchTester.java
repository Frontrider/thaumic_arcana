package hu.frontrider.arcana;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static testutil.Assertions.assertAspectNameValid;

@DisplayName("Testing research jsons")
public class ResearchTester {
    static String researchFolder = "/assets/thaumic_arcana/research/";


    @DisplayName("testing the research files")
    @ParameterizedTest(name = "research : {0}")
    @ValueSource(strings = {"biomancy", "metal_transmutation"})
    void aspectnamesCorrect(String researchFile) {
        Scanner researchScanner = new Scanner(getClass().getResourceAsStream(researchFolder + researchFile + ".json"));
        StringBuilder researchData = new StringBuilder();

        while (researchScanner.hasNextLine()) {
            researchData.append(researchScanner.nextLine());
        }

        JSONArray read = JsonPath.read(researchData.toString(), "$.entries[*].stages[*].required_item[*]");

        List<Executable> assertions = new ArrayList<>();

        read.forEach(item->{
            String itemStringValue = item.toString();
            if(itemStringValue.startsWith("thaumcraft:phial;1;1;")){
                String aspect = itemStringValue.substring(47).replaceAll("'}]}","");
                assertions.add(()->assertAspectNameValid(aspect));
            }
        });

        assertAll(assertions.toArray(new Executable[]{}));
    }

}
