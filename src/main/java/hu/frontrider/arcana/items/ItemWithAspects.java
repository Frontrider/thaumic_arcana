package hu.frontrider.arcana.items;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;

public class ItemWithAspects extends ItemBase implements IEssentiaContainerItem {

    public AspectList getAspects(ItemStack itemStack) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        AspectList aspectList = new AspectList();
        if (tagCompound == null)
            return aspectList;

        aspectList.readFromNBT(tagCompound);

        return aspectList;
    }

    @Override
    public void setAspects(ItemStack itemStack, AspectList aspectList) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound == null)
            tagCompound = new NBTTagCompound();
        aspectList.writeToNBT(tagCompound);
        itemStack.setTagCompound(tagCompound);
    }

    @Override
    public boolean ignoreContainedAspects() {
        return false;
    }
}
