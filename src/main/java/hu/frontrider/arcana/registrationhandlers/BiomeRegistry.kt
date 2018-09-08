package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.worldgen.biome.TaintedLand
import net.minecraftforge.common.BiomeDictionary
import net.minecraftforge.common.BiomeDictionary.Type.*
import net.minecraftforge.common.BiomeManager

/**
 * @author Kis András Gábor
 * 2018.08.30.
 */
object BiomeRegistry {

    fun init() {
        val taintedLand = TaintedLand()
        BiomeDictionary.addTypes(taintedLand, WASTELAND, VOID, MAGICAL, RARE)

        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, BiomeManager.BiomeEntry(taintedLand, 1))
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, BiomeManager.BiomeEntry(taintedLand, 1))
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, BiomeManager.BiomeEntry(taintedLand, 1))
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, BiomeManager.BiomeEntry(taintedLand, 1))
    }
}
