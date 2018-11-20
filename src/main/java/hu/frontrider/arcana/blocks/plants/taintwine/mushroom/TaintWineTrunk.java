package hu.frontrider.arcana.blocks.plants.taintwine.mushroom;

import hu.frontrider.arcana.blocks.BlockTaintBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
public class TaintWineTrunk extends BlockTaintBase {
    public TaintWineTrunk() {
        super(Material.ROCK, "taint_wine_trunk",3);
    }


    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    

}
