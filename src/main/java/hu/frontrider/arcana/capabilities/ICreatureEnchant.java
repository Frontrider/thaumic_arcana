package hu.frontrider.arcana.capabilities;

import hu.frontrider.arcana.creatureenchant.backend.CEnchantment;

import java.util.Map;

public interface ICreatureEnchant {
    boolean hasEnchant(CEnchantment enchantment);

    int getLevel(CEnchantment enchantment);

    Map<CEnchantment, Integer> getStore();

    void setStore(Map<CEnchantment, Integer> store);

    void putEnchant(CEnchantment enchantment, Integer level);
}
