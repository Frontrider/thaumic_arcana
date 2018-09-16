package hu.frontrider.arcana.content.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import static hu.frontrider.arcana.ThaumicArcana.INSTANCE;
import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class ItemBase extends Item {

    ItemBase(ResourceLocation resourceLocation) {
        setRegistryName(resourceLocation);
        setUnlocalizedName(MODID + "." + resourceLocation.getResourcePath());
        setCreativeTab(INSTANCE.getTABARCANA());
    }
}
