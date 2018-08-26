package hu.frontrider.arcana.capabilities;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class CreatureEnchantCapability implements ICreatureEnchant {

    @GameRegistry.ObjectHolder(MODID + ":normal")
    private static EnchantingBaseCircle baseCircle = null;

    private Map<CreatureEnchant, CreatureEnchantContainer> enchants;
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
        return enchants.get(enchantment) != null ? enchants.get(enchantment).level : 0;
    }

    @Override
    public Map<CreatureEnchant, CreatureEnchantContainer> getStore() {
        return enchants;
    }

    @Override
    public void setStore(Map<CreatureEnchant, CreatureEnchantContainer> store) {
        enchants = store;
    }

    @Override
    public void putEnchant(CreatureEnchantContainer enchantment) {
        enchants.put(enchantment.creatureEnchant,enchantment);
    }

    @Override
    public EnchantingBaseCircle getCircle() {
        return enchantingBaseCircle;
    }

    @Override
    public void setCircle(EnchantingBaseCircle enchantingBaseCircle) {
        this.enchantingBaseCircle = enchantingBaseCircle;
    }

    public static class CreatureEnchantContainer {
        private final CreatureEnchant creatureEnchant;
        private final int level;
        private final int usedTo;

        public CreatureEnchantContainer(CreatureEnchant creatureEnchant, int level, int usedTo){
            this.creatureEnchant = creatureEnchant;
            this.level = level;
            this.usedTo = usedTo;
        }

        public CreatureEnchant getCreatureEnchant() {
            return creatureEnchant;
        }

        public int getLevel() {
            return level;
        }

        public int getUsedTo() {
            return usedTo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CreatureEnchantContainer that = (CreatureEnchantContainer) o;
            return level == that.level &&
                    usedTo == that.usedTo &&
                    Objects.equals(creatureEnchant, that.creatureEnchant);
        }

        @Override
        public int hashCode() {

            return Objects.hash(creatureEnchant, level, usedTo);
        }

        @Override
        public String toString() {
            return "CreatureEnchantContainer{" +
                    "creatureEnchant=" + creatureEnchant +
                    ", level=" + level +
                    ", usedTo=" + usedTo +
                    '}';
        }
    }
}
