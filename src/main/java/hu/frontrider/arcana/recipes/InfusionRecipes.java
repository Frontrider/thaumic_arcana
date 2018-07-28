package hu.frontrider.arcana.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.capabilities.CreatureEnchant.ENCHANTS.*;
import static hu.frontrider.arcana.items.ItemRegistry.creature_enchanter;
import static net.minecraft.init.Items.*;
import static thaumcraft.api.items.ItemsTC.salisMundus;

public class InfusionRecipes {

    static void register() {
        registerCreatureEnchants();
        registerAdvancedCreatureEnchants();
        registerCreatureCurses();
    }

    static void registerCreatureEnchants() {

        {
            ItemStack source = new ItemStack(creature_enchanter);
            source.setTagCompound(null);

            ItemStack itemStack = new ItemStack(creature_enchanter);

            NBTTagCompound compound = new NBTTagCompound();
            compound.setString("name", FERTILE.toString());
            compound.setInteger("level", 1);
            itemStack.setTagCompound(compound);

            ThaumcraftApi.addInfusionCraftingRecipe(
                    new ResourceLocation(MODID, "creature_enchanter_fertile"),
                    new InfusionRecipe("CREATURE_ENCHANT",
                            itemStack,
                            10, (new AspectList())
                            .add(Aspect.LIFE, 10)
                            .add(Aspect.DESIRE, 20)
                            .add(Aspect.ORDER, 20)
                            .add(Aspect.MAGIC, 40),
                            source,
                            new ItemStack(salisMundus),
                            new ItemStack(GOLDEN_APPLE),
                            new ItemStack(salisMundus),
                            new ItemStack(GOLDEN_CARROT),
                            new ItemStack(salisMundus),
                            new ItemStack(GOLDEN_CARROT)
                    )
            );
        }

        {
            ItemStack source = new ItemStack(creature_enchanter);
            source.setTagCompound(null);

            ItemStack itemStack = new ItemStack(creature_enchanter);
            NBTTagCompound compound = new NBTTagCompound();
            compound.setString("name", RESPIRATION.toString());
            compound.setInteger("level", 1);
            itemStack.setTagCompound(compound);
            ThaumcraftApi.addInfusionCraftingRecipe(
                    new ResourceLocation(MODID, "creature_enchanter_respiration"),
                    new InfusionRecipe("CREATURE_ENCHANT",
                            itemStack,
                            10, (new AspectList())
                            .add(Aspect.LIFE, 10)
                            .add(Aspect.DESIRE, 20)
                            .add(Aspect.ORDER, 20)
                            .add(Aspect.MAGIC, 40),
                            source,
                            new ItemStack(salisMundus),
                            new ItemStack(FISH),
                            new ItemStack(salisMundus),
                            new ItemStack(FISH),
                            new ItemStack(salisMundus),
                            new ItemStack(FISH)
                    )
            );
        }

        for (int i = 0; i < 3; i++) {
            ItemStack source = new ItemStack(creature_enchanter);

            ItemStack itemStack = new ItemStack(creature_enchanter);
            NBTTagCompound compound = new NBTTagCompound();

            compound.setString("name", PROTECTION.toString());
            compound.setInteger("level", i + 1);

            itemStack.setTagCompound(compound);

            if (i > 1) {
                NBTTagCompound sourceCompound = new NBTTagCompound();
                sourceCompound.setInteger("level", i);
                sourceCompound.setString("name", PROTECTION.toString());
                source.setTagCompound(sourceCompound);
            }


            ThaumcraftApi.addInfusionCraftingRecipe(
                    new ResourceLocation(MODID, "creature_enchanter_protection_" + (i + 1)),
                    new InfusionRecipe("CREATURE_ENCHANT",
                            itemStack,
                            20, (new AspectList())
                            .add(Aspect.ORDER, 10)
                            .add(Aspect.PROTECT, 100)
                            .add(Aspect.MAGIC, 40),
                            source,
                            new ItemStack(LEATHER),
                            new ItemStack(salisMundus),
                            new ItemStack(IRON_INGOT),
                            new ItemStack(salisMundus),
                            new ItemStack(DIAMOND),
                            new ItemStack(salisMundus),
                            new ItemStack(LEATHER),
                            new ItemStack(salisMundus)
                    )
            );
        }
    }

    static void registerAdvancedCreatureEnchants() {

        ItemStack strength = new ItemStack(Items.POTIONITEM, 1);
        NBTTagCompound potionCompound = new NBTTagCompound();
        potionCompound.setString("Potion", "minecraft:strong_strength");
        strength.setTagCompound(potionCompound);

        ItemStack fireRes = new ItemStack(Items.POTIONITEM, 1);
        NBTTagCompound fireResPotionCompound = new NBTTagCompound();
        potionCompound.setString("Potion", "minecraft:fire_resistance");
        fireRes.setTagCompound(fireResPotionCompound);

        for (int i = 0; i < 3; i++) {
            ItemStack source = new ItemStack(creature_enchanter);

            ItemStack itemStack = new ItemStack(creature_enchanter);
            NBTTagCompound compound = new NBTTagCompound();

            compound.setString("name", STRENGTH.toString());
            compound.setInteger("level", i + 1);

            itemStack.setTagCompound(compound);

            if (i > 1) {
                NBTTagCompound sourceCompound = new NBTTagCompound();
                sourceCompound.setInteger("level", i);
                sourceCompound.setString("name", STRENGTH.toString());
                source.setTagCompound(sourceCompound);
            }


            ThaumcraftApi.addInfusionCraftingRecipe(
                    new ResourceLocation(MODID, "creature_enchanter_attack_" + (i + 1)),
                    new InfusionRecipe("CREATURE_ENCHANT_ADVANCED",
                            itemStack,
                            20, (new AspectList())
                            .add(Aspect.PROTECT, 10)
                            .add(Aspect.ENERGY, 200)
                            .add(Aspect.MAGIC, 40)
                            .add(Aspect.EARTH, 10)
                            .add(Aspect.MECHANISM, 10)
                            .add(Aspect.LIFE, 200)
                            .add(Aspect.METAL, 20),
                            source,
                            new ItemStack(salisMundus),
                            new ItemStack(ItemsTC.fabric),
                            new ItemStack(ItemsTC.plate, 1, 2),
                            new ItemStack(ItemsTC.fabric),
                            new ItemStack(salisMundus),
                            strength,
                            strength,
                            strength,
                            new ItemStack(salisMundus),
                            new ItemStack(ItemsTC.fabric),
                            new ItemStack(ItemsTC.plate, 1, 2),
                            new ItemStack(ItemsTC.fabric),
                            fireRes,
                            fireRes,
                            fireRes,
                            new ItemStack(salisMundus),
                            new ItemStack(ItemsTC.fabric),
                            new ItemStack(ItemsTC.plate, 1, 2),
                            new ItemStack(ItemsTC.fabric)
                    )
            );
        }

    }

    static void registerCreatureCurses() {

        {
            ItemStack source = new ItemStack(creature_enchanter);
            source.setTagCompound(null);

            ItemStack itemStack = new ItemStack(creature_enchanter);
            NBTTagCompound compound = new NBTTagCompound();
            compound.setString("name", CURSE_OF_DECAY.toString());
            compound.setInteger("level", 1);
            itemStack.setTagCompound(compound);
            ThaumcraftApi.addInfusionCraftingRecipe(
                    new ResourceLocation(MODID, "creature_enchanter_decay"),
                    new InfusionRecipe("CREATURE_CURSE",
                            itemStack,
                            10, (new AspectList())
                            .add(Aspect.LIFE, 10)
                            .add(Aspect.DESIRE, 20)
                            .add(Aspect.ORDER, 20)
                            .add(Aspect.MAGIC, 40),
                            source,
                            new ItemStack(salisMundus),
                            new ItemStack(SKULL, 1, 1),
                            new ItemStack(salisMundus),
                            new ItemStack(SKULL, 1, 1),
                            new ItemStack(salisMundus),
                            new ItemStack(SKULL, 1, 1)
                    )
            );
        }
    }
}
