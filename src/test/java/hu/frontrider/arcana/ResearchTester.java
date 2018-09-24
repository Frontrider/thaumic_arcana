package hu.frontrider.arcana;

import com.jayway.jsonpath.JsonPath;
import hu.frontrider.minecrafttestkit.MineCraftTest;
import net.minidev.json.JSONArray;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static testutil.Assertions.assertAspectNameValid;
/**
 * Checks the validity of a thaumcraft research json.
 *
 *
 * */

@MineCraftTest
@DisplayName("Testing research jsons")
public class ResearchTester {

    static String researchFolder = "/assets/thaumic_arcana/research/";
    static String langFolder = "/assets/thaumic_arcana/lang/";

    @BeforeEach
    void setUp() {

    }

    @DisplayName("testing the research files")
    @ParameterizedTest(name = "research : {0}")
    @ValueSource(strings = {"biomancy",
            "metal_transmutation",
            "biomancy/animalproducts",
            "biomancy/golems",
            "biomancy/enchanting",
            "biomancy/plantproducts",
            "biomancy/plantexperiments"})
    void testingResearch(String researchFile) throws IOException {

        Properties language = new Properties();
        InputStream resourceAsStream = getClass().getResourceAsStream(langFolder + "en_us" + ".lang");
        language.load(resourceAsStream);

        Scanner researchScanner = new Scanner(getClass().getResourceAsStream(researchFolder + researchFile + ".json"));
        StringBuilder researchData = new StringBuilder();

        while (researchScanner.hasNextLine()) {
            researchData.append(researchScanner.nextLine());
        }
        //checking the required items
        JSONArray read = JsonPath.read(researchData.toString(), "$.entries[*].stages[*].required_item[*]");

        List<Executable> assertions = new ArrayList<>();

        //all phials are valid
        read.forEach(item -> {
            String itemStringValue = item.toString();
            if (itemStringValue.startsWith("thaumcraft:phial;1;1;")) {
                String aspect = itemStringValue.substring(47).replaceAll("'}]}", "");
                assertions.add(() -> assertAspectNameValid(aspect));
            }else{
                String[] split = itemStringValue.split(":");
                String[] validnameSpaces = new String[]{"minecraft","thaumcraft","thaumic_arcana"};
                assertions.add(()->assertTrue(ArrayUtils.contains(validnameSpaces,split[0]),split[0]));

                assertions.add(()->assertDoesNotThrow(()->assertFalse(split[0].contains("-"),split[0]),"invalid item entry "+itemStringValue));
                assertions.add(()->assertDoesNotThrow(()->assertFalse(split[1].contains("-"),split[1]),"invalid item entry "+itemStringValue));
            }
        });

        JSONArray required_research1 = JsonPath.read(researchData.toString(), "$.entries[*].stages[*].required_research[*]");
        JSONArray required_research2 = JsonPath.read(researchData.toString(), "$.entries[*].addenda[*].required_research[*]");
        JSONArray required_research3 = JsonPath.read(researchData.toString(), "$.entries[*].parent[*]");
        JSONArray required_research4 = JsonPath.read(researchData.toString(), "$.entries[*].siblings[*]");
        required_research1.merge(required_research2);
        required_research1.merge(required_research3);
        required_research1.merge(required_research4);

        //checking if all research reference is correctly formatted
        required_research1.forEach(item -> {
            String itemStringValue = item.toString();
            assertions.add(() -> assertEquals(itemStringValue.toUpperCase(), itemStringValue));
            String[] strings = itemStringValue.split("@");
            assertions.add(() -> assertTrue(strings.length <= 2));

            if (strings.length == 2)
                assertions.add(() -> Integer.valueOf(strings[1]));
        });

        JSONArray meta = JsonPath.read(researchData.toString(), "$.entries[*].meta[*]");

        String[] metavalues = new String[]{
                "ROUND","SPIKY","HIDDEN","REVERSE","AUTOUNLOCK"
        };
        //checking if the metadata is spelled correctly
        meta.forEach(item->{
            String itemStringValue = item.toString();
            assertions.add(()->assertTrue(ArrayUtils.contains(metavalues,itemStringValue),"invalid meta value "+itemStringValue));
        });

        JSONArray recipes = JsonPath.read(researchData.toString(), "$.entries[*].stages[*].recipes[*]");

        //checking if the recipes are correct
        recipes.forEach(item->{
            String itemStringValue = item.toString();
            String[] split = itemStringValue.split(":");
            assertions.add(()->assertEquals(2,split.length,"invalid recipe domain size must be 2 "+itemStringValue));
            assertions.add(()->assertEquals("thaumic_arcana",split[0],"invalid recipe domain "+split[0]));
        });

        JSONArray location = JsonPath.read(researchData.toString(), "$.entries[*].location[*]");

        location.forEach(entry->{
            String s = entry.toString();
            assertions.add(()->assertDoesNotThrow(()->Integer.valueOf(s),"location is not a number"));
        });

        //checking if all names are correct.
        JSONArray languageKeys = JsonPath.read(researchData.toString(), "$.entries[*].name");
        JSONArray languageKeys1 = JsonPath.read(researchData.toString(), "$.entries[*].stages[*].text");
        JSONArray languageKeys2 = JsonPath.read(researchData.toString(), "$.entries[*].addenda[*].title");
        languageKeys.merge(languageKeys1);
        languageKeys.merge(languageKeys2);

        languageKeys.forEach(lang->{
            ArrayList<?> keys = Collections.list(language.propertyNames());
            assertions.add(()->assertTrue(keys.contains(lang.toString()),"no language key registered for : "+lang));
        });

        assertAll(assertions.toArray(new Executable[]{}));
    }

}
