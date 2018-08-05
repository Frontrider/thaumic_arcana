package hu.frontrider.arcana.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.creatureenchant.backend.CEnchantment.CURSE_OF_DECAY;
import static hu.frontrider.arcana.items.ItemRegistry.creature_enchanter;
import static net.minecraft.init.Items.SKULL;
import static thaumcraft.api.items.ItemsTC.salisMundus;

public class InfusionRecipes {

    public static void register() {
        registerCreatureEnchants();
        registerAdvancedCreatureEnchants();
        registerCreatureCurses();
    }

    static void registerCreatureEnchants() {


    }

    static void registerAdvancedCreatureEnchants() {



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
