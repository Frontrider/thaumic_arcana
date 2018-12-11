package hu.frontrider.core.api;

import hu.frontrider.arcana.api.IArcaneSieveRecipe;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Collections;
import java.util.List;

/**
 *Generic registry event.
 */
public class RegistryEvent<T> extends Event {

    private List<T> recipeList;

    public RegistryEvent(List<T> recipes) {
        recipeList = recipes;
    }

    public void registerRecipe(T recipe) {
        recipeList.add(recipe);
    }

    public void registerRecipes(T... recipe) {
        Collections.addAll(recipeList, recipe);
    }

}
