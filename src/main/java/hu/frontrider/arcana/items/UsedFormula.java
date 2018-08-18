package hu.frontrider.arcana.items;

import net.minecraft.item.ItemStack;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class UsedFormula extends BaseFormula {
    public UsedFormula()
    {
        setRegistryName(MODID, "formula_used");
        setUnlocalizedName("formula");
        setMaxStackSize(1);
        setMaxDamage(16);
    }

    @Override
    public boolean showDurabilityBar(ItemStack p_showDurabilityBar_1_) {
        return true;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack p_getRGBDurabilityForDisplay_1_) {
        return super.getRGBDurabilityForDisplay(p_getRGBDurabilityForDisplay_1_);
    }
}
