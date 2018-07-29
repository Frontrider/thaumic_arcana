package thaumcraft.api.aspects;

import net.minecraft.item.ItemStack;

/**
 * @author azanor
 * <p>
 * Used by wispy essences and essentia phials to hold their aspects.
 * Useful for similar item containers that store their aspect information in nbt form so TC
 * automatically picks up the aspects they contain.
 */
public interface IEssentiaContainerItem {
    AspectList getAspects(ItemStack itemstack);

    void setAspects(ItemStack itemstack, AspectList aspects);

    /**
     * Return true if the contained aspect should not be used to calculate the actual item aspects. For example: jar labels.
     */
    boolean ignoreContainedAspects();
}

//Example implementation
/*  
	@Override
	public AspectList getAspects(ItemStack itemstack) {
		if (itemstack.hasTagCompound()) {
			AspectList aspects = new AspectList();
			aspects.readFromNBT(itemstack.getTagCompound());
			return aspects.size()>0?aspects:null;
		}
		return null;
	}
	
	@Override
	public void setAspects(ItemStack itemstack, AspectList aspects) {
		if (!itemstack.hasTagCompound()) itemstack.setTagCompound(new NBTTagCompound());
		aspects.writeToNBT(itemstack.getTagCompound());
	}
	
	@Override
	public boolean ignoreContainedAspects() {return false;}
*/