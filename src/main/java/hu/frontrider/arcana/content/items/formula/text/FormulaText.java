package hu.frontrider.arcana.content.items.formula.text;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
@FunctionalInterface
public interface FormulaText {
    
    void addText(ItemStack stack, NBTTagCompound tagCompound, @Nullable World worldIn, List<String> tooltip);
}
