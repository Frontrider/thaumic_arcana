package hu.frontrider.arcana.items;

import net.minecraft.item.Item;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

/**
 * This item is "rewarded" after the player did the self enchantment research.
 */
public class EnchantmentAttempt extends Item {

    public EnchantmentAttempt() {
        setRegistryName(MODID, "enchantment_attempt");
        setMaxStackSize(1);
        setUnlocalizedName("enchantment_attempt");
    }
}
