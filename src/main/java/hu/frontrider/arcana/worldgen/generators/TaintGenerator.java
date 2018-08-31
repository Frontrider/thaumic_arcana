package hu.frontrider.arcana.worldgen.generators;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import thaumcraft.common.blocks.world.taint.TaintHelper;

import java.util.Random;

/**
 * @author Kis András Gábor
 * 2018.08.30.
 */
public class TaintGenerator extends WorldGenerator {
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {

        if (rand.nextInt(10) > 7) {
            TaintHelper.spreadFibres(worldIn, position);
            if (rand.nextInt(10) > 4) {
                TaintHelper.addTaintSeed(worldIn, position);
            }
            return true;
        }
        return false;
    }
}
