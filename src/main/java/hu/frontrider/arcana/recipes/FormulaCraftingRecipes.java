package hu.frontrider.arcana.recipes;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import hu.frontrider.arcana.items.CreatureEnchanter;
import hu.frontrider.arcana.items.EnchantModifierDust;
import hu.frontrider.arcana.recipes.formulacrafting.FormulaApplicationRecipe;
import hu.frontrider.arcana.recipes.formulacrafting.FormulaRecipes;
import hu.frontrider.arcana.registrationhandlers.ItemRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

/**
 * This class initialises all of the formula recipes that are generated from other content.
 */
public class FormulaCraftingRecipes {

    @GameRegistry.ObjectHolder(MODID+":enchant_modifier")
    static Item modifier = null;

    public static void initRecipes() {
        initEnchantingRecipes();
        initbaseCircleFormula();
    }

    static void initEnchantingRecipes(){
        IForgeRegistry<CreatureEnchant> registry = GameRegistry.findRegistry(CreatureEnchant.class);
        for (CreatureEnchant enchant : registry) {
            InitEnchaningFormula(enchant, ItemRegistry.enchanting_powder_basic, 1);
            InitEnchaningFormula(enchant, ItemRegistry.enchanting_powder_advanced, 2);
            InitEnchaningFormula(enchant, ItemRegistry.enchanting_powder_magical, 3);
        }
    }

    static void InitEnchaningFormula(CreatureEnchant enchant, Item item, int level) {
        FormulaRecipes.INSTANCE.addRecipe(new FormulaApplicationRecipe(
                enchant.getResearch(),
                Blocks.ENCHANTING_TABLE,
                enchant.formula(),
                new ItemStack(item),
                CreatureEnchanter.createEnchantedItem(item, new CreatureEnchanter.EnchantmentData(enchant, level)),
                2, 0, true, false
        ));
    }

    static void initbaseCircleFormula(){
        IForgeRegistry<EnchantingBaseCircle> registry = GameRegistry.findRegistry(EnchantingBaseCircle.class);
        for (EnchantingBaseCircle enchantingBaseCircle : registry) {
            InitEnchaningFormula(enchantingBaseCircle,modifier);
        }
    }

    static void InitEnchaningFormula(EnchantingBaseCircle enchant, Item item) {
        FormulaRecipes.INSTANCE.addRecipe(new FormulaApplicationRecipe(
                enchant.getResearch(),
                Blocks.ENCHANTING_TABLE,
                enchant.getFormula(),
                new ItemStack(item),
                EnchantModifierDust.createItem(item,enchant),
                2, 0, true, false
        ));
    }
}
