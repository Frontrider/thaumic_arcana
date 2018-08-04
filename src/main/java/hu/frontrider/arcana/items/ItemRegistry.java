package hu.frontrider.arcana.items;

import hu.frontrider.arcana.ThaumicArcana;
import hu.frontrider.arcana.util.Initialisable;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

@Mod.EventBusSubscriber
public class ItemRegistry {

    public static Item nutrient_mix = new Item()
            .setRegistryName(MODID, "nutrient_mix")
            .setUnlocalizedName("nutrient_mix")
            .setCreativeTab(ThaumicArcana.TABARCANA);

    public static Item fertiliser = new ItemFertiliser();
    public static Item incubated_egg = new IncubatedEgg();
    public static Item creature_enchanter = new CreatureEnchanter();
    public static Item plant_ball = new PlantBall();

    public static Item[] items = new Item[]{
            fertiliser, incubated_egg, creature_enchanter, nutrient_mix, plant_ball
    };

    @SubscribeEvent
    static void init(RegistryEvent.Register<Item> event) {
        Arrays.stream(items).forEach((item) -> {
            if(item instanceof Initialisable)
            {
                ((Initialisable) item).init();
            }
        });
        event.getRegistry().registerAll(items);
    }

}
