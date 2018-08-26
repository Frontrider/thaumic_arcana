package hu.frontrider.arcana.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.ThaumicArcana.TABARCANA;

public class BlockBase extends Block {

    public BlockBase(Material materialIn,String name) {
        super(materialIn);
        setRegistryName(MODID,name);
        setUnlocalizedName(MODID+"."+name);
        setCreativeTab(TABARCANA);
    }
}
