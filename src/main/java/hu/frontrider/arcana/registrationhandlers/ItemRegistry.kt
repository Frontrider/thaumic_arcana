package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.ThaumicArcana.NETWORK_WRAPPER
import hu.frontrider.arcana.ThaumicArcana.TABARCANA
import hu.frontrider.arcana.items.*
import hu.frontrider.arcana.items.curio.*
import hu.frontrider.arcana.items.tools.*
import hu.frontrider.arcana.items.IncubatedEgg
import hu.frontrider.arcana.items.ItemFertiliser
import hu.frontrider.arcana.items.ItemPlantBall
import hu.frontrider.arcana.items.armor.SlimiumArmor
import hu.frontrider.arcana.items.cloning.ItemBabyBottle
import hu.frontrider.arcana.items.cloning.ItemReproductiveSyringe
import hu.frontrider.arcana.util.Initialisable
import hu.frontrider.arcana.util.items.ItemFactory
import net.minecraft.block.Block
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemFood
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.util.*

class ItemRegistry {

    companion object {
        var enchanting_powder_basic: Item = EnchantmentUpgradePowder(1, ResourceLocation(MODID, "enchanting_powder_basic"), NETWORK_WRAPPER)
        var enchanting_powder_advanced: Item = EnchantmentUpgradePowder(2, ResourceLocation(MODID, "enchanting_powder_advanced"), NETWORK_WRAPPER)
        var enchanting_powder_magical: Item = EnchantmentUpgradePowder(3, ResourceLocation(MODID, "enchanting_powder_magical"), NETWORK_WRAPPER)

        var fertiliser: Item = ItemFertiliser()
        var incubated_egg: Item = IncubatedEgg()
        var creature_enchanter: Item = CreatureEnchanter(NETWORK_WRAPPER)
        var plant_ball: Item = ItemPlantBall()

        val enchantModifier = EnchantModifierDust(NETWORK_WRAPPER)
        val blocks = ArrayList<Block>()

        val items: MutableList<Item> = mutableListOf(
                Item()
                        .setRegistryName(MODID, "nutrient_mix")
                        .setUnlocalizedName("$MODID.nutrient_mix")
                        .setCreativeTab(TABARCANA),
                fertiliser,
                incubated_egg,
                creature_enchanter,
                plant_ball,
                enchanting_powder_basic,
                enchanting_powder_advanced,
                enchanting_powder_magical,
                enchantModifier,
                ResearchResult(),
                NaturalCuriosity(),
                AuraCuriosity(),
                InfusedCuriosity(),
                AnimatedCuriosity(),

                ItemFactory.start(Item())
                        .setResourourceLocation("ingot_livium")
                        .build(),
                ItemFactory.start(Item())
                        .setResourourceLocation("neutered_flesh")
                        .build(),
                LiviumPickAxe(),
                LiviumAxe(),
                LiviumShovel(),
                LiviumSword(),
                SlimiumPickAxe(),
                SlimiumAxe(),
                SlimiumSword(),
                SlimiumShovel(),
                SlimiumHoe(),
                InfusedSlimiumAxe(),
                InfusedSlimiumPickAxe(),
                InfusedSlimiumSword(),
                InfusedSlimiumHoe(),
                InfusedSlimiumShovel(),
                ItemFactory.start(SlimiumArmor(EntityEquipmentSlot.CHEST))
                        .setResourourceLocation("infused_slimy_chestplate")
                        .build(),
                ItemFactory.start(SlimiumArmor(EntityEquipmentSlot.LEGS))
                        .setResourourceLocation("infused_slimy_leggings")
                        .build(),
                ItemFactory.start(SlimiumArmor(EntityEquipmentSlot.HEAD))
                        .setResourourceLocation("infused_slimy_helmet")
                        .build(),
                ItemFactory.start(ItemFood(3, .3f, false))
                        .setResourourceLocation("slime_meat_raw")
                        .build(),
                ItemFactory.start(ItemFood(10, .9f, false))
                        .setResourourceLocation("slime_meat_cooked")
                        .build(),
                ItemFactory.start(ItemInfusedSlime())
                        .setResourourceLocation("infused_slime")
                        .build(),
                ItemFactory.start(ItemExpStore())
                        .setResourourceLocation("experience_store")
                        .build(),
                ItemFactory.start(Item())
                        .setResourourceLocation("empty_soul_capsule")
                        .build(),
                ItemFactory.start(Item())
                        .setResourourceLocation("soul_capsule")
                        .build(),
                ItemFactory.start(Item())
                        .setResourourceLocation("revival_capsule")
                        .build()/*,
                ItemFactory.start(ItemReproductiveSyringe())
                        .setResourourceLocation("reproductive_syringe")
                        .build(),
                ItemFactory.start(ItemBabyBottle())
                        .setResourourceLocation("baby_bottle")
                        .build(),
                ItemFactory.start(Item())
                        .setResourourceLocation("reproductive_material")
                        .build(),
                ItemFactory.start(Item())
                        .setResourourceLocation("fertile_bottle")
                        .build()*/
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

