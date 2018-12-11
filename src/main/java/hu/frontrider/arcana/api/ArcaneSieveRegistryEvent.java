package hu.frontrider.arcana.api;

import hu.frontrider.core.api.RegistryEvent;

import java.util.List;

public class ArcaneSieveRegistryEvent extends RegistryEvent<IArcaneSieveRecipe> {
    public ArcaneSieveRegistryEvent(List<IArcaneSieveRecipe> recipes) {
        super(recipes);
    }
}
