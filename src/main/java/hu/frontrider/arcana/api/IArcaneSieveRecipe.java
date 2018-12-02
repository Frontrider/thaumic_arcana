package hu.frontrider.arcana.api;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Interface for the sieving recipes.
 * */
public interface IArcaneSieveRecipe {

    /**
     * is used to determine if the item can fit into a input slot 1
     * If not implemented correctly, the item might not be useable for crafting.
     * */
    boolean isItem1(ItemStack source);

    /**
     * is used to determine if the item can fit into a input slot 2
     * If not implemented correctly, the item might not be useable for crafting.
     * */
    boolean isItem2(ItemStack source);

    /**
     * is used to determine if the item can fit into the sieve material slot
     * If not implemented correctly, the item might not be useable for crafting.
     * */
    boolean isCatalyst(ItemStack source);

    /**
     * determines if this recipe can craft from the available ingredients
     * */
    boolean canCraft(ItemStack source1, ItemStack source2, ItemStack catalyst, World world);

    /**
     * Returns the result of the recipe, you also need to reduce the counts of the inputs yourself.
     * */
    ItemStack craft(ItemStack source1, ItemStack source2, ItemStack catalyst, World world);
}
