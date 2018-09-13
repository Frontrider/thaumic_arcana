package hu.frontrider.arcana.registrationhandlers.recipes

import hu.frontrider.arcana.core.formulacrafting.FormulaApplicationRecipe
import hu.frontrider.arcana.core.formulacrafting.FormulaRecipes
import hu.frontrider.arcana.registrationhandlers.ItemRegistry
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.registries.IForgeRegistry

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.content.items.CreatureEnchanter
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle

/**
 * This class initialises all of the formula recipes that are generated from other content.
 */
object FormulaCraftingRecipes {

    @GameRegistry.ObjectHolder("$MODID:enchant_modifier")
    internal var modifier: Item? = null

    fun initRecipes() {
        initEnchantingRecipes()
        initbaseCircleFormula()
    }

    internal fun initEnchantingRecipes() {
        val registry = GameRegistry.findRegistry<CreatureEnchant>(CreatureEnchant::class.java)
        for (enchant in registry) {
            InitEnchaningFormula(enchant, ItemRegistry.enchanting_powder_basic, 1)
            InitEnchaningFormula(enchant, ItemRegistry.enchanting_powder_advanced, 2)
            InitEnchaningFormula(enchant, ItemRegistry.enchanting_powder_magical, 3)
        }
    }

    internal fun InitEnchaningFormula(enchant: CreatureEnchant, item: Item, level: Int) {
        FormulaRecipes.addRecipe(FormulaApplicationRecipe(
                enchant.research,
                Blocks.ENCHANTING_TABLE,
                enchant.formula(),
                ItemStack(item),
                CreatureEnchanter.createEnchantedItem(item, CreatureEnchanter.EnchantmentData(enchant, level)),
                2, 0, true, false
        ))
    }

    internal fun initbaseCircleFormula() {
        val registry = GameRegistry.findRegistry<EnchantingBaseCircle>(EnchantingBaseCircle::class.java)
        for (enchantingBaseCircle in registry) {
            InitEnchaningFormula(enchantingBaseCircle, modifier)
        }
    }

    internal fun InitEnchaningFormula(enchant: EnchantingBaseCircle, item: Item?) {
        FormulaRecipes.addRecipe(FormulaApplicationRecipe(
                enchant.getResearch(),
                Blocks.ENCHANTING_TABLE,
                enchant.getFormula(),
                ItemStack(item!!),
                hu.frontrider.arcana.content.items.EnchantModifierDust.createItem(item, enchant),
                2, 0, true, false
        ))
    }
}
