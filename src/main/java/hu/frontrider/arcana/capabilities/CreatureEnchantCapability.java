package hu.frontrider.arcana.capabilities;

import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CreatureEnchantCapability implements ICreatureEnchant {
    private Map<ResourceLocation,Integer> enchants;

    public CreatureEnchantCapability() {
        enchants = new HashMap<>();
    }


    @Override
    public boolean hasEnchant() {
        return !enchants.isEmpty();
    }

    @Override
    public boolean hasEnchant(ResourceLocation enchantment) {
        return enchants.containsKey(enchantment);
    }

    @Override
    public int getLevel(ResourceLocation enchantment) {
        return enchants.get(enchantment);
    }

    @Override
    public Map<ResourceLocation, Integer> getStore() {
        return enchants;
    }

    @Override
    public void setStore(Map<ResourceLocation, Integer> store) {
        enchants = store;
    }

    public void putEnchant(ResourceLocation enchantment,Integer level){
        enchants.put(enchantment, level);
    }
}
