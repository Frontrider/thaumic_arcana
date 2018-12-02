package hu.frontrider.arcana.api;

import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Collections;
import java.util.List;

/**
 * Registers arcane sieve recipes. fired during init.
 */
public class ArcaneSieveRecipeRegistryEvent extends Event {

    private List<IArcaneSieveRecipe> recipeList;

    public ArcaneSieveRecipeRegistryEvent(List<IArcaneSieveRecipe> recipes) {
        recipeList = recipes;
    }

    public void registerRecipe(IArcaneSieveRecipe recipe) {
        recipeList.add(recipe);
    }

    public void registerRecipes(IArcaneSieveRecipe... recipe) {
        Collections.addAll(recipeList, recipe);
    }

}
