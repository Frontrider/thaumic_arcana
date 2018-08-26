package hu.frontrider.arcana.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class UsedFormula extends BaseFormula {
    public UsedFormula()
    {
        super(new ResourceLocation(MODID, "formula_used"));
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
