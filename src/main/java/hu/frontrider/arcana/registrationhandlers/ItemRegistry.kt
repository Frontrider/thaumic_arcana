package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.ThaumicArcana.NETWORK_WRAPPER
import hu.frontrider.arcana.ThaumicArcana.TABARCANA
import hu.frontrider.arcana.content.items.*
import hu.frontrider.arcana.registrationhandlers.BlockRegistry.Companion.experimentTable
import hu.frontrider.arcana.util.Initialisable
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.util.*

class ItemRegistry {

    companion object {
        var nutrient_mix = Item()
                .setRegistryName(MODID, "nutrient_mix")
                .setUnlocalizedName("$MODID.nutrient_mix")
                .setCreativeTab(TABARCANA)

        var enchanting_powder_basic: Item = EnchantmentUpgradePowder(1, ResourceLocation(MODID, "enchanting_powder_basic"), NETWORK_WRAPPER)
        var enchanting_powder_advanced: Item = EnchantmentUpgradePowder(2, ResourceLocation(MODID, "enchanting_powder_advanced"), NETWORK_WRAPPER)
        var enchanting_powder_magical: Item = EnchantmentUpgradePowder(3, ResourceLocation(MODID, "enchanting_powder_magical"), NETWORK_WRAPPER)

        var fertiliser: Item = ItemFertiliser()
        var incubated_egg: Item = IncubatedEgg()
        var creature_enchanter: Item = CreatureEnchanter(NETWORK_WRAPPER)
        var plant_ball: Item = PlantBall()

        val enchantModifier = EnchantModifierDust(NETWORK_WRAPPER)
        val blocks = ArrayList<Block>()

        val items: MutableList<Item> = mutableListOf(
                fertiliser,
                incubated_egg,
                creature_enchanter,
                nutrient_mix,
                plant_ball,
                enchanting_powder_basic,
                enchanting_powder_advanced,
                enchanting_powder_magical,
                enchantModifier,
                ResearchResult(),
                NaturalCuriosity()
        )
    }

    @SubscribeEvent
    fun init(event: RegistryEvent.Register<Item>) {


        items.forEach { item ->
            if (item is Initialisable) {
                item.init()
            }
        }

        blocks.forEach {
            items += ItemBlock(it).setRegistryName(it.registryName!!).setCreativeTab(it.creativeTabToDisplayOn)
        }

        items.iterator().forEachRemaining {
            event.registry.registerAll(it as Item?)
        }
    }
}
