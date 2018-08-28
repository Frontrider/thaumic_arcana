package hu.frontrider.arcana.items.formula;

import hu.frontrider.arcana.items.ItemWithAspects;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nullable;
import java.util.List;

public class BaseFormula extends ItemWithAspects {
    FormulaTextManager formulaTextManager;
    public BaseFormula(ResourceLocation resourceLocation) {
        super(resourceLocation);
        formulaTextManager = new FormulaTextManager();
        formulaTextManager.registerFormulaTexts();
    }

    @Override
    public boolean hasEffect(ItemStack itemStack) {
        return itemStack.hasTagCompound();
    }

    public ItemStack getItemStack(AspectList aspectList) {
        ItemStack itemStack = new ItemStack(this);

        if (aspectList == null)
            return itemStack;

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        aspectList.writeToNBT(nbtTagCompound);
        itemStack.setTagCompound(nbtTagCompound);

        return itemStack;
    }

    @Override
    public boolean ignoreContainedAspects() {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        formulaTextManager.addText(stack, worldIn, tooltip, flagIn);
    }
}
