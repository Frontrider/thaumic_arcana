package hu.frontrider.arcana.worldgen.biome;

import hu.frontrider.arcana.worldgen.generators.taintwine.TaintGenerator;
import hu.frontrider.arcana.worldgen.generators.taintwine.TaintWineGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * @author Kis András Gábor
 * 2018.08.30.
 */
public class TaintedLand extends Biome {
    public TaintedLand() {
        super(getProperties());
    }
    static BiomeProperties getProperties(){
        final BiomeProperties tainted_land = new BiomeProperties("tainted_land");
        tainted_land.setRainDisabled();
        tainted_land.setWaterColor(8389737);
        return tainted_land;
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos) {
        return 8389737;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        final WorldGenAbstractTree superFeature = super.getRandomTreeFeature(rand);
        final int range = rand.nextInt(1);
        if(range >0&& range <5)
            return new TaintWineGenerator();
        return superFeature;
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        final WorldGenerator superFeature = super.getRandomWorldGenForGrass(rand);
        final int range = rand.nextInt(1);
        if(range >0&& range <5)
            return new TaintGenerator();
        return superFeature;    }
}
