package hu.frontrider.arcana.items;

import hu.frontrider.arcana.util.Initialisable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.ThaumicArcana.TABARCANA;
import static hu.frontrider.arcana.blocks.BlockRegistry.experiment_table;

@Mod.EventBusSubscriber
public class ItemRegistry {

    public static Item nutrient_mix = new Item()
            .setRegistryName(MODID, "nutrient_mix")
            .setUnlocalizedName("nutrient_mix")
            .setCreativeTab(TABARCANA);

    public static Item enchanting_powder_basic = new Item()
            .setRegistryName(MODID, "enchanting_powder_basic")
            .setUnlocalizedName("enchanting_powder_basic")
            .setCreativeTab(TABARCANA);


    public static Item enchanting_powder_advanced = new Item()
            .setRegistryName(MODID, "enchanting_powder_advanced")
            .setUnlocalizedName("enchanting_powder_advanced")
            .setCreativeTab(TABARCANA);

    public static Item enchanting_powder_magical = new Item()
            .setRegistryName(MODID, "enchanting_powder_magical")
            .setUnlocalizedName("enchanting_powder_magical")
            .setCreativeTab(TABARCANA);

    public static Item wood_pulp = new Item()
            .setRegistryName(MODID, "wood_pulp")
            .setUnlocalizedName("wood_pulp")
            .setCreativeTab(TABARCANA);

    public static Item fertiliser = new ItemFertiliser();
    public static Item incubated_egg = new IncubatedEgg();
    public static Item creature_enchanter = new CreatureEnchanter();
    public static Item plant_ball = new PlantBall();

    public static Item[] items = new Item[]{
            fertiliser, incubated_egg, creature_enchanter, nutrient_mix, plant_ball,
            enchanting_powder_basic,enchanting_powder_advanced,enchanting_powder_magical,
            new ItemBlock(experiment_table).setCreativeTab(TABARCANA).setRegistryName(experiment_table.getRegistryName()),
            wood_pulp
    };

    @SubscribeEvent
    static void init(RegistryEvent.Register<Item> event) {
        Arrays.stream(items).forEach((item) -> {
            if (item instanceof Initialisable) {
                ((Initialisable) item).init();
            }
        });

        event.getRegistry().registerAll(items);
    }
}
