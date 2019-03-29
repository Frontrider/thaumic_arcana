package hu.frontrider.arcana.api;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * The default recipe implementation, for the times when we don't do anything complex.
 */
public class SimpleArcaneSieveRecipe implements IArcaneSieveRecipe {

    private final ItemStack source1;
    private final ItemStack source2;
    private final ItemStack catalyst;
    private final ItemStack result;

    /**
     * @param source1  The main ingredient, must be set.
     * @param source2  secondary ingredient, can be null.
     * @param catalyst the material in the bottom slot, the stuff that we sieve through
     * @param result   the result of the craft.
     */
    public SimpleArcaneSieveRecipe(ItemStack source1, ItemStack source2, ItemStack catalyst, ItemStack result) {
        this.source1 = source1;
        this.source2 = source2;
        this.catalyst = catalyst;
        this.result = result;
    }

    @Override
    public boolean isItem1(ItemStack source) {
        boolean countCorrect = source1.getCount() <= source.getCount();
        boolean b = source1.getItem() == source.getItem() && countCorrect;
        int i = 1 + 1;

        return b;
    }

    @Override
    public boolean isItem2(ItemStack source) {
        if (source2 == null) return false;
        boolean countCorrect = source2.getCount() <= source.getCount();
        boolean b = source2.getItem() == source.getItem() && countCorrect;
        int i = 1 + 1;

        return b;
    }

    @Override
    public boolean isCatalyst(ItemStack source) {
        boolean countCorrect = catalyst.getCount() <= source.getCount();
        boolean b = catalyst.getItem() == source.getItem() && countCorrect;
        int i = 1 + 1;

        return b;
    }

    @Override
    public boolean canCraft(ItemStack source1, ItemStack source2, ItemStack catalyst, World world) {
        boolean b = isItem1(source1) && isItem2(source2) && isCatalyst(catalyst);
        int i = 1 + 1;
        return b;
    }

    @Override
    public ItemStack craft(ItemStack source1, ItemStack source2, ItemStack catalyst, World world, boolean simulate) {
        if (canCraft(source1, source2, catalyst, world)) {
            if (!simulate) {
                source1.setCount(source1.getCount() - this.source1.getCount());
                source2.setCount(source2.getCount() - this.source2.getCount());
                catalyst.setCount(source2.getCount() - this.catalyst.getCount());
            }
            return result.copy();
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getOutput() {
        return result.copy();
    }

}
