package hu.frontrider.arcana.creatureenchant.backend;

import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.Event;
import thaumcraft.api.aspects.AspectList;

import java.util.ArrayList;
import java.util.List;

import static hu.frontrider.arcana.capabilities.CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY;

public abstract class CreatureEnchant<T extends Event> {

    private final Class<T> eventClass;


    private static final List<CreatureEnchant> creatureEnchants;

    static{
        creatureEnchants = new ArrayList<>();
    }

    public CreatureEnchant(Class<T> eventClass){
        this.eventClass = eventClass;
    }
    public boolean isEvent(Class eventType){
        return eventType.isAssignableFrom(eventClass);
    }

    public int getEnchantLevel(Entity entity,CEnchantment enchantment){
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
            if(capability.hasEnchant(enchantment))
                return capability.getLevel(enchantment);
        }
        return 0;
    }

    public static List<CreatureEnchant> getCreatureEnchants() {
        return creatureEnchants;
    }

    public abstract ResourceLocation getIcon();

    public abstract AspectList formula();

}
