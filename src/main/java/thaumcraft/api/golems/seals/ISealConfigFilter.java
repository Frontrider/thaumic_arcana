package thaumcraft.api.golems.seals;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface ISealConfigFilter {

    NonNullList<ItemStack> getInv();

    int getFilterSize();

    ItemStack getFilterSlot(int i);

    int getFilterSlotSize(int i);

    void setFilterSlot(int i, ItemStack stack);

    void setFilterSlotSize(int i, int size);

    boolean isBlacklist();

    void setBlacklist(boolean black);

    boolean hasStacksizeLimiters();
	
}
