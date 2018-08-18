package hu.frontrider.arcana.items;

import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static hu.frontrider.arcana.ThaumicArcana.TABARCANA;

@Disabled
@DisplayName("Generic tests that run on all items")
public class GenericItemTests {

    @BeforeEach
    void setUp() {
        Bootstrap.register();
    }

    static Stream<Arguments> getItems(){
        return Stream.of(
                Arguments.of(new CreatureEnchanter(null)),
                Arguments.of(new EnchantmentUpgradePowder(0)),
                Arguments.of(new Formula()),
                Arguments.of(new IncubatedEgg()),
                Arguments.of(new ItemFertiliser()),
                Arguments.of(new ItemBase()),
                Arguments.of(new PlantBall())
        );
    }

    @ParameterizedTest()
    @DisplayName("Can get item variants before the mod was initialised")
    @MethodSource("getItems")
    void canGetVariantsBeforeInit(Item item){
        item.getSubItems(TABARCANA,NonNullList.create());
    }

}
