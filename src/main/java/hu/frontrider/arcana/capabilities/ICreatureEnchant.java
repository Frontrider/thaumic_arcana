package hu.frontrider.arcana.capabilities;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;

import java.util.Map;


public interface ICreatureEnchant {

    boolean hasEnchant();

    boolean hasEnchant(CreatureEnchant enchant);

    int getLevel(CreatureEnchant enchant);

    Map<CreatureEnchant, CreatureEnchantCapability.CreatureEnchantContainer> getStore();

    void setStore(Map<CreatureEnchant, CreatureEnchantCapability.CreatureEnchantContainer> store);

    void putEnchant(CreatureEnchantCapability.CreatureEnchantContainer creatureEnchantContainer);

    EnchantingBaseCircle getCircle();
    void setCircle(EnchantingBaseCircle enchantingBaseCircle);
}
