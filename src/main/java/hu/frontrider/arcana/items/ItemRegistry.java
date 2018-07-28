package hu.frontrider.arcana.items;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

@Mod.EventBusSubscriber
public class ItemRegistry {

    public static Item fertiliser = new ItemFertiliser();
    public static Item incubated_egg = new IncubatedEgg();
    public static Item creature_enchanter = new CreatureEnchanter();
    public static Item relief_item = new Item().setRegistryName(MODID, "relief_item").setUnlocalizedName("relief_item");

    public static Item[] items = new Item[]{
            fertiliser, incubated_egg, creature_enchanter, relief_item
    };

    @SubscribeEvent
    static void init(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(items);
    }

}
