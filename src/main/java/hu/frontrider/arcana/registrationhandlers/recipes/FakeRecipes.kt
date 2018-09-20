package hu.frontrider.arcana.registrationhandlers.recipes

import hu.frontrider.arcana.ThaumicArcana
import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.content.items.CreatureEnchanter
import hu.frontrider.arcana.content.items.EnchantmentUpgradePowder
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.registrationhandlers.ItemRegistry
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.ThaumcraftApi
import thaumcraft.api.crafting.CrucibleRecipe

object FakeRecipes {

    fun init() {
        initEnchanting()
    }

    internal fun initEnchanting() {
        val registry = GameRegistry.findRegistry(CreatureEnchant::class.java)

        arrayOf("protection","respiration","fertile").forEach {

            val item = ItemRegistry.enchanting_powder_basic

            val enchant = registry.getValue(ResourceLocation(MODID, it))!!

            val enchantedItem = CreatureEnchanter.createEnchantedItem(item, CreatureEnchanter.EnchantmentData(enchant, (item as EnchantmentUpgradePowder).level))

            val recipe = CrucibleRecipe(
                    enchant.research,
                    enchantedItem,
                    ItemStack(item),
                    enchant.formula()
            )
            ThaumcraftApi.addFakeCraftingRecipe(ResourceLocation(ThaumicArcana.MODID, "ce_" + enchant.registryName!!), recipe)
        }

        arrayOf("strength","speed","spider","vitality").forEach {
            val item = ItemRegistry.enchanting_powder_advanced
            val enchant = registry.getValue(ResourceLocation(MODID, it))!!

            val enchantedItem = CreatureEnchanter.createEnchantedItem(item, CreatureEnchanter.EnchantmentData(enchant, (item as EnchantmentUpgradePowder).level))
            val recipe = CrucibleRecipe(
                    enchant.research,
                    enchantedItem,
                    ItemStack(item),
                    enchant.formula()
            )
            ThaumcraftApi.addFakeCraftingRecipe(ResourceLocation(ThaumicArcana.MODID, "ce_" + enchant.registryName!!), recipe)
        }
    }
}