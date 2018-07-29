package hu.frontrider.arcana.util;

import hu.frontrider.arcana.items.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabArcana extends CreativeTabs {
    public CreativeTabArcana(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemRegistry.creature_enchanter);
    }
}
