package hu.frontrider.arcana.capabilities;

import hu.frontrider.arcana.creatureenchant.backend.CEnchantment;

import java.util.HashMap;
import java.util.Map;

public class CreatureEnchantCapability implements ICreatureEnchant {
    private Map<CEnchantment,Integer> enchants;

    public CreatureEnchantCapability() {
        enchants = new HashMap<>();
    }

    @Override
    public boolean hasEnchant(CEnchantment enchantment) {
        return enchants.containsKey(enchantment);
    }

    @Override
    public int getLevel(CEnchantment enchantment) {
        return enchants.get(enchantment);
    }

    @Override
    public Map<CEnchantment, Integer> getStore() {
        return enchants;
    }

    @Override
    public void setStore(Map<CEnchantment, Integer> store) {
        enchants = store;
    }

    public void putEnchant(CEnchantment enchantment,Integer level){
        enchants.put(enchantment, level);
    }
}
