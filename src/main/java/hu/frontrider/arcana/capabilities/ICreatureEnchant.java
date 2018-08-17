package hu.frontrider.arcana.capabilities;

import net.minecraft.util.ResourceLocation;

import java.util.Map;


public interface ICreatureEnchant {

    boolean hasEnchant();

    boolean hasEnchant(ResourceLocation enchantment);

    int getLevel(ResourceLocation enchantment);

    Map<ResourceLocation, Integer> getStore();

    void setStore(Map<ResourceLocation, Integer> store);

    void putEnchant(ResourceLocation enchantment, Integer level);
}
