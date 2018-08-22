package hu.frontrider.arcana.research.theory;


import net.minecraft.item.ItemStack;
import thaumcraft.api.items.ItemsTC;

import java.util.ArrayList;

import static net.minecraft.init.Blocks.CACTUS;
import static net.minecraft.init.Blocks.PUMPKIN;
import static net.minecraft.init.Items.*;

public class TheoryRegistry {

    public static void init() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();

        itemStacks.add(new ItemStack(WHEAT_SEEDS));
        itemStacks.add(new ItemStack(POTATO));
        itemStacks.add(new ItemStack(POISONOUS_POTATO));
        itemStacks.add(new ItemStack(BEETROOT_SEEDS));
        itemStacks.add(new ItemStack(APPLE));
        itemStacks.add(new ItemStack(WHEAT));
        itemStacks.add(new ItemStack(MELON));
        itemStacks.add(new ItemStack(PUMPKIN));
        itemStacks.add(new ItemStack(MELON_SEEDS));
        itemStacks.add(new ItemStack(PUMPKIN_SEEDS));
        itemStacks.add(new ItemStack(BEETROOT));
        itemStacks.add(new ItemStack(CARROT));
        itemStacks.add(new ItemStack(CACTUS));
        CardGrow.options = itemStacks.toArray(new ItemStack[0]);

        itemStacks.clear();

        itemStacks.add(new ItemStack(BEEF));
        itemStacks.add(new ItemStack(CHICKEN));
        itemStacks.add(new ItemStack(MUTTON));
        itemStacks.add(new ItemStack(PORKCHOP));

        CardDissect.options = itemStacks.toArray(new ItemStack[0]);

        itemStacks.clear();
        itemStacks.add(new ItemStack(ItemsTC.brain));
        itemStacks.add(new ItemStack(ROTTEN_FLESH));
        itemStacks.add(new ItemStack(BONE));

        CardDissectDead.options = itemStacks.toArray(new ItemStack[0]);



    }
}
