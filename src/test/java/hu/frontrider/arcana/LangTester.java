package hu.frontrider.arcana;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Checking language files, that")
public class LangTester {
    static String langFolder = "/assets/thaumic_arcana/lang/";


    @DisplayName("no empty entries exist")
    @ParameterizedTest(name = "lang : {0}")
    @ValueSource(strings = {"en_us"})
    void noEmptyKeys(String langFile) throws IOException {

        InputStream resourceAsStream = getClass().getResourceAsStream(langFolder + langFile + ".lang");
        Properties properties = new Properties();
        properties.load(resourceAsStream);


        List<Executable> executables = properties.stringPropertyNames().stream().parallel().map(key -> new Executable[]{
                () -> assertNotNull(properties.getProperty(key), "the value of " + key + " is null!"),
                () -> assertNotEquals("", properties.getProperty(key).trim(), "the value of " + key + " is only whitespace!")
        })
                .flatMap((Arrays::stream))
                .collect(Collectors.toList());

        assertAll(
                executables.toArray(new Executable[]{})
        );
    }
}
