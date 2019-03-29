package crafttweaker.api.event;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.entity.IEntity;
import stanhebben.zenscript.annotations.*;

@ZenClass("crafttweaker.event.IEntityEvent")
@ZenRegister
public interface IEntityEvent {
    @ZenGetter("entity")
    IEntity getEntity();
}
