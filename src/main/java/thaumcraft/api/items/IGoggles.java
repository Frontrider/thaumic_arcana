package thaumcraft.api.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * @author Azanor
 * <p>
 * Equipped or held items that extend this class will be able to perform most functions that
 * goggles of revealing can apart from view nodes which is handled by IRevealer.
 */

public interface IGoggles {

    /*
     * If this method returns true things like block essentia contents will be shown.
     */
    boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player);

}
