package hu.frontrider.arcana.creatureenchant.backend;

import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static hu.frontrider.arcana.capabilities.CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY;

public abstract class CreatureEnchant {

    private static final List<CreatureEnchant> creatureEnchants;

    static{
        creatureEnchants = new ArrayList<>();
    }

    private final ResourceLocation icon;

    public CreatureEnchant(ResourceLocation icon){
        this.icon = icon;
    }

    public int getEnchantLevel(Entity entity,CEnchantment enchantment){
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
            if(capability.hasEnchant(enchantment))
                return capability.getLevel(enchantment);
        }
        return 0;
    }

    public static void setEnchantment(Entity entity,Map<CEnchantment,Integer> enchants){
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
            capability.setStore(enchants);
        }
    }

    public static boolean isEnchanted(Entity entity){
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)){
            ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
            return capability.hasEnchant();
        }
        return false;
    }

    public static List<CreatureEnchant> getCreatureEnchants() {
        return creatureEnchants;
    }

    public ResourceLocation getIcon(){
        return icon;
    }

    public abstract AspectList formula();

}
