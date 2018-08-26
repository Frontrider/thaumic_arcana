package hu.frontrider.arcana.creatureenchant;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.effect.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class GenericEnchantmentTests {

    @ParameterizedTest
    @ValueSource(classes = {
            StrengthEnchant.class,
            FertileEnchant.class,
            ProtectionEnchant.class,
            RespirationEnchant.class,
            SpiderFingers.class,
            Speed.class,
    })
    void test(Class<CreatureEnchant> c) throws IllegalAccessException, InstantiationException {
        CreatureEnchant creatureEnchant = c.newInstance();

        assertAll(
                () -> assertNotNull(creatureEnchant.formula(), "aspectlist is null"),
                () -> assertNotNull(creatureEnchant.getIcon(), "icon is null"),
                () -> assertNotNull(creatureEnchant.getResearch(), "research is null"),
                () -> assertNotEquals("", creatureEnchant.getResearch(), "research is empty"),
                () -> assertEquals(creatureEnchant.getResearch().toUpperCase(), creatureEnchant.getResearch(), "research is not uppercase"),
                () -> assertNotEquals("enchant.creature_enchant.", creatureEnchant.getUnlocalizedName(), "no unlocalised name was set")
        );
    }
}
