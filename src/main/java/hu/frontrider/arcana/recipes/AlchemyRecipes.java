package hu.frontrider.arcana.recipes;

import hu.frontrider.arcana.items.ItemRegistry;
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

import static hu.frontrider.arcana.Configuration.enablePlatinum;
import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static net.minecraft.init.Items.*;

public class AlchemyRecipes {

    static void register() {
        initMetalTransmutation();
        initGrowingBasic();
        initRecipes();
        initHardenFleshToLeather();
        initGrowingAdvanced();
        initGrowingFlesh();
    }

    private static void initMetalTransmutation() {

        GameRegistry.findRegistry(Item.class).getValue(new ResourceLocation("minecraft:stone"));
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

    }

    private static void initGrowingFlesh() {

        String KEY = "FLESH_GROWTH";
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(Items.BEEF, 2),
                    new ItemStack(Items.BEEF),
                    new AspectList().add(Aspect.LIFE, 3)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_beef"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(Items.MUTTON, 2),
                    new ItemStack(Items.MUTTON),
                    new AspectList().add(Aspect.LIFE, 3)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_mutton"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(Items.PORKCHOP, 2),
                    new ItemStack(Items.PORKCHOP),
                    new AspectList().add(Aspect.LIFE, 3)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_pork"), recipe);
        }
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    KEY,
                    new ItemStack(Items.CHICKEN, 2),
                    new ItemStack(Items.CHICKEN),
                    new AspectList().add(Aspect.LIFE, 3)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "grow_chicken"), recipe);
        }
    }

    private static void initHardenFleshToLeather() {
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "FLESH_TO_LEATHER",
                    new ItemStack(Items.LEATHER, 1),
                    new ItemStack(Items.BEEF),
                    new AspectList().add(Aspect.EXCHANGE, 1)
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

    private static void initRecipes() {
        {
            CrucibleRecipe recipe = new CrucibleRecipe(
                    "ARCANE_FERTILISER",
                    new ItemStack(ItemRegistry.fertiliser, 1),
                    ThaumcraftApiHelper.makeCrystal(Aspect.PLANT, 1),
                    new AspectList().add(Aspect.LIGHT, 2).merge(Aspect.EARTH, 2).merge(Aspect.WATER, 2).merge(Aspect.CRAFT, 4)
            );
            ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MODID, "fertiliser_recipe"), recipe);
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
}
