package hu.frontrider.arcana.capabilities;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;

import java.util.Map;


public interface ICreatureEnchant {

    boolean hasEnchant();

    boolean hasEnchant(CreatureEnchant enchant);

    int getLevel(CreatureEnchant enchant);

    Map<CreatureEnchant, Integer> getStore();

    void setStore(Map<CreatureEnchant, Integer> store);

    void putEnchant(CreatureEnchant enchantment, Integer level);

    EnchantingBaseCircle getCircle();
    void setCircle(EnchantingBaseCircle enchantingBaseCircle);
}
