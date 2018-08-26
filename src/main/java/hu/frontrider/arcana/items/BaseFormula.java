package hu.frontrider.arcana.items;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;

public class BaseFormula extends ItemWithAspects {

    BaseFormula(ResourceLocation resourceLocation) {
        super(resourceLocation);
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


}
