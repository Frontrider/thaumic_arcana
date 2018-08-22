package hu.frontrider.arcana.recipes.formulacrafting;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.items.CreatureEnchanter;
import hu.frontrider.arcana.util.AspectUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static hu.frontrider.arcana.registrationhandlers.ItemRegistry.*;

public class FormulaRecipe {

    static List<FormulaRecipe> formulaRecipeList;

    private ItemStack item;
    private final AspectList formula;
    private final ItemStack result;
    private BiConsumer<ItemStack, ItemStack> craftingCallback;
    private BiFunction<ItemStack, ItemStack, Boolean> canCraftOverride;
    private static IForgeRegistry<CreatureEnchant> creatureEnchantIForgeRegistry = GameRegistry.findRegistry(CreatureEnchant.class);

    public FormulaRecipe(ItemStack item, AspectList formula, ItemStack result) {
        this.item = item;
        this.formula = formula;
        this.result = result;
    }

    void setCallback(BiConsumer<ItemStack, ItemStack> craftingCallback) {
        this.craftingCallback = craftingCallback;
    }

    void canCraftOverride(BiFunction<ItemStack, ItemStack, Boolean> canCraftOverride) {
        this.canCraftOverride = canCraftOverride;
    }


    public boolean canCraft(ItemStack main, ItemStack formula) {
        if (canCraftOverride != null) {
            return canCraftOverride.apply(main, formula);
        }

        if (!OreDictionary.itemMatches(main, item, false))
            return false;

        if (item.hasTagCompound()) {
            if (!main.hasTagCompound())
                return false;

            if (!item.getTagCompound().equals(main.getTagCompound()))
                return false;
        }
        AspectList objectAspects = AspectHelper.getObjectAspects(formula);

        return AspectUtil.aspectListEquals(objectAspects, this.formula);
    }


    public ItemStack craft(ItemStack main, ItemStack formula) {

        if (!canCraft(main, formula))
            return null;

        if (craftingCallback == null) {
            main.shrink(1);
            formula.shrink(1);
        } else {
            craftingCallback.accept(main, formula);
        }
        return result;
    }

    public ItemStack getItem() {
        return item;
    }

    public AspectList getFormula() {
        return formula;
    }

    public ItemStack getResult() {
        return result;
    }

    public BiConsumer<ItemStack, ItemStack> getCraftingCallback() {
        return craftingCallback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormulaRecipe that = (FormulaRecipe) o;
        return Objects.equals(item, that.item) &&
                AspectUtil.aspectListEquals(formula, that.formula) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, formula, result);
    }

    public static void registerRecipes() {

        if (formulaRecipeList == null)
            formulaRecipeList = new ArrayList<>();

        creatureEnchantIForgeRegistry.getValuesCollection().forEach(creatureEnchant -> {
            formulaRecipeList.add(buildFor(creatureEnchant, enchanting_powder_advanced));
            formulaRecipeList.add(buildFor(creatureEnchant, enchanting_powder_basic));
            formulaRecipeList.add(buildFor(creatureEnchant, enchanting_powder_magical));
        });
    }

    static FormulaRecipe buildFor(CreatureEnchant creatureEnchant, Item enchanter) {
        ItemStack enchantedItem = CreatureEnchanter.createEnchantedItem(
                enchanter, new CreatureEnchanter.EnchantmentData(creatureEnchant, 3));
        enchantedItem.setCount(1);
        return new FormulaRecipe(
                new ItemStack(enchanter),
                creatureEnchant.formula(),
                enchantedItem
        );
    }

    public static List<FormulaRecipe> getFormulaRecipeList() {
        if (formulaRecipeList == null)
            formulaRecipeList = new ArrayList<>();
        return formulaRecipeList;
    }
}
