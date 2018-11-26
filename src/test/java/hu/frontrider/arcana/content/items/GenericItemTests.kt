package hu.frontrider.arcana.items

import net.minecraft.init.Bootstrap
import net.minecraft.item.Item
import net.minecraft.util.NonNullList
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

import java.util.stream.Stream

import hu.frontrider.arcana.ThaumicArcana.TABARCANA
import net.minecraft.item.ItemStack

@Disabled
@DisplayName("Generic tests that run on all items")
class GenericItemTests {

    @BeforeEach
    internal fun setUp() {
        Bootstrap.register()
    }

    @ParameterizedTest
    @DisplayName("Can get item variants before the mod was initialised")
    @MethodSource("getItems")
    internal fun canGetVariantsBeforeInit(item: Item) {
        item.getSubItems(TABARCANA, NonNullList.create<ItemStack>())
    }

    @Suppress("UNREACHABLE_CODE")
    companion object {

        internal val items: Stream<Arguments>
            get() = Stream.of(
                    Arguments.of(CreatureEnchanter(null!!)),
                    Arguments.of(IncubatedEgg()),
                    Arguments.of(ItemFertiliser()),
                    Arguments.of(ItemPlantBall())
            )
    }

}
