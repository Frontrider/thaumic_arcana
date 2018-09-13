package hu.frontrider.arcana.content.blocks.experiments.tiles.logic;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * checks if the cage is in a valid place.
 * */
public class ArcaneCageAreaScan {

    @GameRegistry.ObjectHolder("minecraft:grass")
    static Block grass = null;

    public boolean isvalid(World world, BlockPos pos) {
        BlockPos blockPos = pos.down().east(5).north(5);
        for (int south = 0; south < 5; south++) {
            blockPos = blockPos.south();
            if (!isValid(world.getBlockState(blockPos)))
                return false;
            for (int west = 0; west < 5; west++) {
                blockPos = blockPos.west();
                if (!isValid(world.getBlockState(blockPos)))
                    return false;
            }
        }
        return true;
    }

    boolean isValid(IBlockState blockState){
        return blockState.getBlock() == grass;
    }

}
