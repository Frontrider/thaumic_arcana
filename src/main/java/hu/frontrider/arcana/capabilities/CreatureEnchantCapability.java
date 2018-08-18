package hu.frontrider.arcana.capabilities;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class CreatureEnchantCapability implements ICreatureEnchant {

    @GameRegistry.ObjectHolder(MODID+":normal")
    private static EnchantingBaseCircle baseCircle = null;

    private Map<CreatureEnchant, Integer> enchants;
    private EnchantingBaseCircle enchantingBaseCircle;

    public CreatureEnchantCapability() {
        enchants = new HashMap<>();
        enchantingBaseCircle = baseCircle;
    }

    @Override
    public boolean hasEnchant() {
        return !enchants.isEmpty();
    }


    @Override
    public boolean hasEnchant(CreatureEnchant enchantment) {
        return enchants.containsKey(enchantment);
    }

    @Override
    public int getLevel(CreatureEnchant enchantment) {
        return enchants.get(enchantment) != null ? enchants.get(enchantment):0;
    }

    @Override
    public Map<CreatureEnchant, Integer> getStore() {
        return enchants;
    }

    @Override
    public void setStore(Map<CreatureEnchant, Integer> store) {
        enchants = store;
    }

    public void putEnchant(CreatureEnchant enchantment, Integer level) {
        enchants.put(enchantment, level);
    }

    @Override
    public EnchantingBaseCircle getCircle() {
        return enchantingBaseCircle;
    }

    @Override
    public void setCircle(EnchantingBaseCircle enchantingBaseCircle) {
        this.enchantingBaseCircle = enchantingBaseCircle;
    }
}
