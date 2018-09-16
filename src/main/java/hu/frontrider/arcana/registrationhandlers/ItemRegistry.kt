package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.ThaumicArcana.NETWORK_WRAPPER
import hu.frontrider.arcana.ThaumicArcana.TABARCANA
import hu.frontrider.arcana.content.items.*
import hu.frontrider.arcana.content.items.formula.Formula
import hu.frontrider.arcana.registrationhandlers.BlockRegistry.Companion.blockArcaneCage
import hu.frontrider.arcana.registrationhandlers.BlockRegistry.Companion.experimentTable
import hu.frontrider.arcana.util.Initialisable
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

        var enchanting_powder_basic: Item = EnchantmentUpgradePowder(1, ResourceLocation(MODID, "enchanting_powder_basic"))
        var enchanting_powder_advanced: Item = EnchantmentUpgradePowder(2, ResourceLocation(MODID, "enchanting_powder_advanced"))
        var enchanting_powder_magical: Item = EnchantmentUpgradePowder(3, ResourceLocation(MODID, "enchanting_powder_magical"))

        var fertiliser: Item = ItemFertiliser()
        var incubated_egg: Item = IncubatedEgg()
        var creature_enchanter: Item = CreatureEnchanter(NETWORK_WRAPPER)
        var plant_ball: Item = PlantBall()
        var formula: Item = Formula()

        val rodent = Rodent()
        val enchantModifier =EnchantModifierDust(NETWORK_WRAPPER)


        val items: Array<out Item> = arrayOf(
                fertiliser,
                incubated_egg,
                creature_enchanter,
                nutrient_mix,
                plant_ball,
                enchanting_powder_basic,
                enchanting_powder_advanced,
                enchanting_powder_magical,
                formula,
                rodent,
                enchantModifier,
                ItemBlock(experimentTable).setRegistryName(experimentTable.registryName!!).setCreativeTab(TABARCANA),
                ItemBlock(blockArcaneCage).setRegistryName(blockArcaneCage.registryName!!).setCreativeTab(TABARCANA)
        )
    }
    @SubscribeEvent
    fun init(event: RegistryEvent.Register<Item>) {

        Arrays.stream(items).forEach { item ->
            if (item is Initialisable) {
                item.init()
            }
        }

        items.iterator().forEachRemaining{
            event.registry.registerAll(it as Item?)
        }

    }
}
