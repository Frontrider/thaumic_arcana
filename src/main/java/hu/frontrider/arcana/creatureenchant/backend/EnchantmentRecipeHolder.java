package hu.frontrider.arcana.creatureenchant.backend;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;

import java.util.Arrays;
import java.util.stream.Stream;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class EnchantmentRecipeHolder {

    private final AspectList aspects;
    private final NonNullList<Ingredient> items;

    EnchantmentRecipeHolder(AspectList aspects, Ingredient... items) {
        this.aspects = aspects;
        this.items = NonNullList.create();
        this.items.addAll(Arrays.asList(items));

    }

    void registerAsInfusionRecipe(String location, String research, ItemStack source, ItemStack target) {
        ThaumcraftApi.addInfusionCraftingRecipe(
                new ResourceLocation(MODID, location),
                new InfusionRecipe(research, target, 10, aspects, source, items
                )
        );
    }

    void registerArcaneCraftingRecipe(String location, String research, ItemStack source, int vis, ItemStack result) {

        Stream<Aspect> primals = Arrays.stream(aspects.getAspects())
                .parallel()
                .flatMap(aspect -> asPrimalStream(Stream.of(aspect)));

        AspectList aspectList = new AspectList();

        primals.forEach((aspect -> aspectList.merge(aspect, 1)));

        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.addAll(items);
        ingredients.add(Ingredient.fromStacks(source));
    }

    Stream<Aspect> asPrimalStream(Stream<Aspect> aspectStream) {
        return aspectStream.flatMap(aspect -> {
                    if (aspect.isPrimal())
                        return Stream.of(aspect);
                    else
                        return asPrimalStream(Arrays.stream(aspect.getComponents()));
                }
        );
    }
}