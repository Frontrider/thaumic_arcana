package hu.frontrider.arcana.items.formula;

import hu.frontrider.arcana.items.formula.text.EnchantText;
import hu.frontrider.arcana.items.formula.text.FormulaText;
import hu.frontrider.arcana.items.formula.text.ModifierText;
import hu.frontrider.arcana.util.AspectUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static hu.frontrider.arcana.util.StringUtil.swappedDescription;

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
class FormulaTextManager {
    final List<FormulaText> texts;
    FormulaTextManager(){
        texts = new LinkedList<>();
    }
    void addText(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(!stack.hasTagCompound())
            return;
        final NBTTagCompound tagCompound = stack.getTagCompound();
        for (FormulaText formulaText : texts) {
            formulaText.addText(stack,tagCompound, worldIn, tooltip);
        }
    }

    void registerFormulaTexts() {
        texts.add(new EnchantText());
        texts.add(new ModifierText());
    }
}
