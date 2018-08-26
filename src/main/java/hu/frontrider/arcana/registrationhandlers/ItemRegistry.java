package hu.frontrider.arcana.registrationhandlers;

import hu.frontrider.arcana.items.*;
import hu.frontrider.arcana.util.Initialisable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

import static hu.frontrider.arcana.ThaumicArcana.*;
import static hu.frontrider.arcana.registrationhandlers.BlockRegistry.blockArcaneCage;
import static hu.frontrider.arcana.registrationhandlers.BlockRegistry.experimentTable;

@Mod.EventBusSubscriber
public class ItemRegistry {

    public static Item nutrient_mix = new Item()
            .setRegistryName(MODID, "nutrient_mix")
            .setUnlocalizedName("nutrient_mix")
            .setCreativeTab(TABARCANA);

    public static Item enchanting_powder_basic = new EnchantmentUpgradePowder(1)
            .setRegistryName(MODID, "enchanting_powder_basic")
            .setUnlocalizedName("enchanting_powder_basic");


    public static Item enchanting_powder_advanced = new EnchantmentUpgradePowder(2)
            .setRegistryName(MODID, "enchanting_powder_advanced")
            .setUnlocalizedName("enchanting_powder_advanced");


    public static Item enchanting_powder_magical = new EnchantmentUpgradePowder(3)
            .setRegistryName(MODID, "enchanting_powder_magical")
            .setUnlocalizedName("enchanting_powder_magical");

    public static Item fertiliser = new ItemFertiliser();
    public static Item incubated_egg = new IncubatedEgg();
    public static Item creature_enchanter = new CreatureEnchanter(NETWORK_WRAPPER);
    public static Item plant_ball = new PlantBall();
    public static Item formula = new Formula();

    public static Item[] items = new Item[]{
            fertiliser, incubated_egg, creature_enchanter, nutrient_mix, plant_ball,
            enchanting_powder_basic,enchanting_powder_advanced,enchanting_powder_magical,
            formula,new Rodent(),new EnchantModifierDust(NETWORK_WRAPPER),
            new ItemBlock(experimentTable).setRegistryName(experimentTable.getRegistryName()).setCreativeTab(TABARCANA),
            new ItemBlock(blockArcaneCage).setRegistryName(blockArcaneCage.getRegistryName()).setCreativeTab(TABARCANA)
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
