package hu.frontrider.arcana.api;

import hu.frontrider.core.api.RegistryEvent;

import java.util.List;


public class PeopleRegistryEvent extends RegistryEvent<String> {
    public PeopleRegistryEvent(List<String> recipes) {
        super(recipes);
    }
}
