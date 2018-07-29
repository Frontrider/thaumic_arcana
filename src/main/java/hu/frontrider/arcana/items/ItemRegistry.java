package hu.frontrider.arcana.items;

import hu.frontrider.arcana.util.CreativeTabArcana;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

@Mod.EventBusSubscriber
public class ItemRegistry {

    public static Item nutrient_mix = new Item().setRegistryName(MODID, "nutrient_mix").setUnlocalizedName("nutrient_mix");


    public static Item fertiliser = new ItemFertiliser();
    public static Item incubated_egg = new IncubatedEgg();
    public static Item creature_enchanter = new CreatureEnchanter();
    public static Item relief_item = new Item().setRegistryName(MODID, "relief_item").setUnlocalizedName("relief_item");
    public static Item[] items = new Item[]{
            fertiliser, incubated_egg, creature_enchanter, relief_item, nutrient_mix
    };
    static CreativeTabs TABARCANA = new CreativeTabArcana(CreativeTabs.getNextID(), "thaumic_arcana");

    @SubscribeEvent
    static void init(RegistryEvent.Register<Item> event) {
        Arrays.stream(items).forEach((item) -> {
            item.setCreativeTab(TABARCANA);
        });
        event.getRegistry().registerAll(items);
    }

}
