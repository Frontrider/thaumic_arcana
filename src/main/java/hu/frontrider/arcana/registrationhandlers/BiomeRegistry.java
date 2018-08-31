package hu.frontrider.arcana.registrationhandlers;

import hu.frontrider.arcana.worldgen.biome.TaintedLand;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

/**
 * @author Kis András Gábor
 * 2018.08.30.
 */
public class BiomeRegistry {
    
    public static void init(){
        final TaintedLand taintedLand = new TaintedLand();
        BiomeDictionary.addTypes(taintedLand,WASTELAND,VOID,MAGICAL,RARE);

        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT,new BiomeManager.BiomeEntry(taintedLand,1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM,new BiomeManager.BiomeEntry(taintedLand,1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL,new BiomeManager.BiomeEntry(taintedLand,1));
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY,new BiomeManager.BiomeEntry(taintedLand,1));
    }
}
