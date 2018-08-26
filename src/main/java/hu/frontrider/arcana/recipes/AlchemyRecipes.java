package hu.frontrider.arcana.recipes;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.items.Formula;
import hu.frontrider.arcana.items.PlantBall;
import hu.frontrider.arcana.registrationhandlers.ItemRegistry;
import hu.frontrider.arcana.util.AspectUtil;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;

import java.util.List;

import static hu.frontrider.arcana.Configuration.enablePlatinum;
import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.ThaumicArcana.TABARCANA;
import static hu.frontrider.arcana.registrationhandlers.ItemRegistry.enchanting_powder_basic;
import static net.minecraft.init.Items.*;

public class AlchemyRecipes {

    @GameRegistry.ObjectHolder("thaumic_arcana:formula")
    static Formula formula = null;

    public static void register() {
        initMetalTransmutation();
        initGrowingBasic();
        initFertilizer();
        initHardenFleshToLeather();
        initGrowingAdvanced();
        initGrowingFlesh();
        initPlantProducts();
        initEnchanting();
    }

    private static void initMetalTransmutation() {

        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "METAL_TRANSMUTATION",
                    new ItemStack(Items.GOLD_NUGGET, 1, 0),
                    "nuggetIron",
                    new AspectList().add(Aspect.DESIRE, 1)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "transmutation_gold"), recipe);
        }

        if (OreDictionary.doesOreNameExist("ingotCopper")) {
            NonNullList<ItemStack> copper = OreDictionary.getOres("nuggetCopper");
            if (copper.size() > 0) {
                ItemStack itemStack = copper.get(0);
                itemStack.setCount(1);
                CrucibleRecipe recipe = new CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        new AspectList().add(Aspect.EXCHANGE, 1)
                );
                ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "transmutation_copper"), recipe);
            }
        }

        if (OreDictionary.doesOreNameExist("ingotLead")) {
            NonNullList<ItemStack> lead = OreDictionary.getOres("nuggetLead");
            if (lead.size() > 0) {
                ItemStack itemStack = lead.get(0);
                itemStack.setCount(1);
                CrucibleRecipe recipe = new CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        new AspectList().add(Aspect.ORDER, 1)
                );
                ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "transmutation_lead"), recipe);
            }
        }

        if (OreDictionary.doesOreNameExist("ingotSilver")) {
            NonNullList<ItemStack> silver = OreDictionary.getOres("nuggetSilver");
            if (silver.size() > 0) {
                ItemStack itemStack = silver.get(0);
                itemStack.setCount(1);
                CrucibleRecipe recipe = new CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        new AspectList().add(Aspect.METAL, 1)
                );
                ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "transmutation_silver"), recipe);
            }
        }

        if (OreDictionary.doesOreNameExist("ingotNickel")) {
            NonNullList<ItemStack> nickel = OreDictionary.getOres("nuggetNickel");
            if (nickel.size() > 0) {
                ItemStack itemStack = nickel.get(0);
                itemStack.setCount(1);
                CrucibleRecipe recipe = new CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        new AspectList().add(Aspect.ALCHEMY, 1)
                );
                ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "transmutation_nickel"), recipe);
            }
        }

        if (enablePlatinum && OreDictionary.doesOreNameExist("ingotPlatinum")) {
            NonNullList<ItemStack> platinum = OreDictionary.getOres("nuggetPlatinum");
            if (platinum.size() > 0) {
                ItemStack itemStack = platinum.get(0);
                itemStack.setCount(1);
                CrucibleRecipe recipe = new CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        new AspectList().add(Aspect.CRYSTAL, 1)
                                .merge(Aspect.DESIRE, 5)
                );
                ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "transmutation_platinum"), recipe);
            }
        }

        if (OreDictionary.doesOreNameExist("ingotTin")) {
            NonNullList<ItemStack> tin = OreDictionary.getOres("nuggetTin");
            if (tin.size() > 0) {
                ItemStack itemStack = tin.get(0);
                itemStack.setCount(1);
                CrucibleRecipe recipe = new CrucibleRecipe(
                        "METAL_TRANSMUTATION",
                        itemStack,
                        "nuggetIron",
                        new AspectList().add(Aspect.CRYSTAL, 1)
                );
                ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "transmutation_tin"), recipe);
            }
        }

    }

    private static void initGrowingBasic() {
        String KEY = "PLANT_GROWTH";
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(WHEAT, 1),
                    new ItemStack(WHEAT_SEEDS),
                    new AspectList().add(Aspect.LIGHT, 2).merge(Aspect.EARTH, 2).merge(Aspect.WATER, 2)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_wheat"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(CARROT, 2),
                    new ItemStack(CARROT),
                    new AspectList().add(Aspect.LIGHT, 2).merge(Aspect.EARTH, 2).merge(Aspect.WATER, 2)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_carrot"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(Items.POTATO, 2),
                    new ItemStack(POTATO),
                    new AspectList().add(Aspect.LIGHT, 2).merge(Aspect.EARTH, 2).merge(Aspect.WATER, 2)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_potato"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(BEETROOT, 1),
                    new ItemStack(BEETROOT_SEEDS),
                    new AspectList().add(Aspect.LIGHT, 2).merge(Aspect.EARTH, 2).merge(Aspect.WATER, 2)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_beetroot"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(REEDS, 3),
                    new ItemStack(REEDS),
                    new AspectList().add(Aspect.LIGHT, 2).merge(Aspect.EARTH, 2).merge(Aspect.WATER, 3)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_reeds"), recipe);
        }
        {
            Item object = Item.REGISTRY.getObject(new ResourceLocation("minecraft:melon"));
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(object, 1),
                    new ItemStack(MELON_SEEDS),
                    new AspectList().add(Aspect.LIGHT, 3).merge(Aspect.EARTH, 3).merge(Aspect.WATER, 3)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_melon"), recipe);
        }

        {
            Item object = Item.REGISTRY.getObject(new ResourceLocation("minecraft:pumpkin"));
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(object, 1),
                    new ItemStack(PUMPKIN_SEEDS),
                    new AspectList().add(Aspect.LIGHT, 3).merge(Aspect.EARTH, 3).merge(Aspect.WATER, 3)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_beetroot"), recipe);
        }
    }

    private static void initGrowingAdvanced() {
        String KEY = "PLANT_GROWTH_ADVANCED";
        PlantBall ball = (PlantBall) ItemRegistry.plant_ball;

        List<ItemStack> treeItems = PlantBall.getTreeItems();
        int index = 0;
        for (ItemStack treeItem : treeItems) {
            ItemStack seedItem = PlantBall.getProductByIndex(treeItem, 0);

            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    treeItem,
                    new ItemStack(seedItem.getItem(), 1, seedItem.getMetadata()),
                    new AspectList()
                            .add(Aspect.LIGHT, 5)
                            .merge(Aspect.EARTH, 10)
                            .merge(Aspect.WATER, 12)
                            .merge(Aspect.VOID, 5)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "tree_" + index
            ), recipe);
            index++;
        }

        List<ItemStack> seedItems = PlantBall.getSeedItems();
        index = 0;
        for (ItemStack seedlingItem : seedItems) {
            ItemStack seedItem = PlantBall.getProductByIndex(seedlingItem, 0);

            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    seedlingItem,
                    new ItemStack(seedItem.getItem(), 1, seedItem.getMetadata()),
                    new AspectList()
                            .add(Aspect.LIGHT, 5)
                            .merge(Aspect.EARTH, 10)
                            .merge(Aspect.WATER, 12)
                            .merge(Aspect.VOID, 5)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "seed_1" + index
            ), recipe);
            index++;
        }

    }

    private static void initGrowingFlesh() {

        String KEY = "FLESH_GROWTH";
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(Items.BEEF, 2),
                    new ItemStack(Items.BEEF),
                    new AspectList().add(Aspect.LIFE, 10)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_beef"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(Items.MUTTON, 2),
                    new ItemStack(Items.MUTTON),
                    new AspectList().add(Aspect.LIFE, 10)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_mutton"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(Items.PORKCHOP, 2),
                    new ItemStack(Items.PORKCHOP),
                    new AspectList().add(Aspect.LIFE, 10)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_pork"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(Items.CHICKEN, 2),
                    new ItemStack(Items.CHICKEN),
                    new AspectList().add(Aspect.LIFE, 10)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_chicken"), recipe);
        }

        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(ItemRegistry.nutrient_mix),
                    new ItemStack(Items.SUGAR),
                    new AspectList().add(Aspect.LIFE, 3).add(Aspect.PLANT, 2).add(Aspect.MAGIC, 2)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "nutrient_mix"), recipe);
        }

    }

    private static void initHardenFleshToLeather() {
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "FLESH_TO_LEATHER",
                    new ItemStack(Items.LEATHER, 1),
                    new ItemStack(Items.BEEF),
                    new AspectList().add(Aspect.EXCHANGE, 1).merge(Aspect.CRAFT, 1)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "beef_to_leather"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "FLESH_TO_LEATHER",
                    new ItemStack(Items.LEATHER, 1),
                    new ItemStack(Items.PORKCHOP),
                    new AspectList().add(Aspect.EXCHANGE, 1).merge(Aspect.CRAFT, 1)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "pork_to_leather"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "FLESH_TO_LEATHER",
                    new ItemStack(Items.LEATHER, 1),
                    new ItemStack(Items.MUTTON),
                    new AspectList().add(Aspect.EXCHANGE, 1).merge(Aspect.CRAFT, 1)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "mutton_to_leather"), recipe);
        }
    }

    private static void initFertilizer() {

        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "ARCANE_FERTILIZER",
                    new ItemStack(ItemRegistry.fertiliser, 16),
                    ThaumcraftApiHelper.makeCrystal(Aspect.PLANT, 1),
                    new AspectList().add(Aspect.LIGHT, 4).merge(Aspect.EARTH, 4).merge(Aspect.WATER, 4).merge(Aspect.CRAFT, 4).add(Aspect.PLANT, 10)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "fertilizer_recipe"), recipe);
        }

        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "INCUBATED_EGG",
                    new ItemStack(ItemRegistry.incubated_egg, 1),
                    new ItemStack(EGG),
                    new AspectList().add(Aspect.FIRE, 2).merge(Aspect.LIFE, 2)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "incubated_egg_recipe"), recipe);
        }
    }

    private static void initPlantProducts() {
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "PLANT_PRODUCTS",
                    new ItemStack(Items.PAPER, 16, 0),
                    "logWood",
                    new AspectList()
                            .add(Aspect.WATER, 1)
                            .add(Aspect.ENTROPY, 1)
                            .add(Aspect.ORDER, 1)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "paper"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "PLANT_PRODUCTS",
                    new ItemStack(Items.DYE, 2, 1),
                    new ItemStack(Items.DYE, 1, 1),
                    new AspectList()
                            .add(Aspect.WATER, 1)
                            .add(Aspect.LIGHT, 1)
                            .add(Aspect.EARTH, 1)
                            .add(Aspect.LIFE, 1)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "dye_red"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "PLANT_PRODUCTS",
                    new ItemStack(Items.DYE, 2, 2),
                    new ItemStack(Items.DYE, 1, 2),
                    new AspectList()
                            .add(Aspect.WATER, 1)
                            .add(Aspect.LIGHT, 1)
                            .add(Aspect.EARTH, 1)
                            .add(Aspect.LIFE, 1)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "dye_green"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "PLANT_PRODUCTS",
                    new ItemStack(Items.DYE, 2, 11),
                    new ItemStack(Items.DYE, 1, 11),
                    new AspectList()
                            .add(Aspect.WATER, 1)
                            .add(Aspect.LIGHT, 1)
                            .add(Aspect.EARTH, 1)
                            .add(Aspect.LIFE, 1)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "dye_yellow"), recipe);
        }
    }

    private static void initEnchanting() {
        {

            CrucibleRecipe recipe = new CrucibleRecipe(
                    "CREATURE_ENCHANT",
                    new ItemStack(enchanting_powder_basic, 1, 0),
                    new ItemStack(Items.DYE, 1, 4),
                    new AspectList()
                            .add(Aspect.MAGIC, 10)
                            .add(Aspect.ENTROPY, 3)
                            .add(Aspect.ORDER, 3)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "enchant_powder_basic"), recipe);
        }
        NonNullList<ItemStack> formulaSubItems = NonNullList.create();
        formulaSubItems.clear();
        formula.getSubItems(TABARCANA, formulaSubItems);

        for (ItemStack itemStack : formulaSubItems) {
            if (itemStack.hasTagCompound()) {
                AspectList aspectList = AspectUtil.getStoredAspects(itemStack);

                CreatureEnchant creatureEnchant = CreatureEnchant.getForFormula(aspectList);
                if (creatureEnchant != null) {
                    CrucibleRecipe recipe = new CrucibleRecipe(
                            creatureEnchant.getResearch(),
                            itemStack,
                            formula,
                            aspectList
                    );
                    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "ce_" + creatureEnchant.getRegistryName().getResourcePath()), recipe);
                }
            }
        }
    }
}
