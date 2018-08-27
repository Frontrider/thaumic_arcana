package hu.frontrider.arcana.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.ThaumicArcana.TABARCANA;

public class ItemBase extends Item {

    ItemBase(ResourceLocation resourceLocation) {
        setRegistryName(resourceLocation);
        setUnlocalizedName(MODID + "." + resourceLocation.getResourcePath());
        setCreativeTab(TABARCANA);
    }
}
