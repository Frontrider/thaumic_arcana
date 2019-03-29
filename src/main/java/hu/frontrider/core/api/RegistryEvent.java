package hu.frontrider.core.api;

import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Collections;
import java.util.List;

/**
 *Generic registry event.
 */
public class RegistryEvent<T> extends Event {

    private List<T> list;

    public RegistryEvent(List<T> items) {
        list = items;
    }

    public void register(T item) {
        list.add(item);
    }

    public void register(T... item) {
        Collections.addAll(list, item);
    }

}
