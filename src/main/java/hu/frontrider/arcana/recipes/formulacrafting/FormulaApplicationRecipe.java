package hu.frontrider.arcana.recipes.formulacrafting;

import hu.frontrider.arcana.recipes.MissingResourceException;
import hu.frontrider.arcana.util.AspectUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nullable;

public class FormulaApplicationRecipe extends IForgeRegistryEntry.Impl<FormulaApplicationRecipe> {

    private final String research;
    private Block block;
    private  AspectList formula;
    @Nullable
    private  ItemStack required;
    private  ItemStack result;
    private final int vis;
    private final int pollution;
    private final boolean consumeFormula;
    private final boolean consumeBlock;

    public FormulaApplicationRecipe(String research,ResourceLocation block, AspectList formula, ResourceLocation result,boolean consumeFormula) {
        this(research,block, formula, null, result,0,0, consumeFormula,true);
    }

    public FormulaApplicationRecipe(String research,ResourceLocation block, AspectList formula, ResourceLocation item, ResourceLocation result,boolean consumeFormula,int vis, int pollution) {
        this(research,block, formula, item, result, vis,pollution,consumeFormula,false);
    }

    public FormulaApplicationRecipe(String research,Block block, AspectList formula, ItemStack result,int vis, int pollution,boolean consumeFormula) {
        this(research,block, formula, null, result,vis,pollution,consumeFormula, true);
    }

    public FormulaApplicationRecipe(String research,Block block, AspectList formula, ItemStack item, ItemStack result,int vis, int pollution,boolean consumeFormula) {
        this(research,block, formula, item, result,vis,pollution,consumeFormula,true);
    }

    public FormulaApplicationRecipe(String research,ResourceLocation block, AspectList formula, ResourceLocation result,int vis, int pollution) {
        this(research,block, formula, null, result,vis,pollution,true, true);
    }

    public FormulaApplicationRecipe(String research,ResourceLocation block, AspectList formula, ResourceLocation item, ResourceLocation result,int vis, int pollution) {
        this(research,block, formula, item, result,vis,pollution,true, true);
    }

    public FormulaApplicationRecipe(String research,Block block, AspectList formula, ItemStack result,int vis, int pollution) {
        this(research,block, formula, null, result,vis,pollution,true, true);
    }

    public FormulaApplicationRecipe(String research,Block block, AspectList formula, ItemStack item, ItemStack result,int vis, int pollution) {
        this(research,block, formula, item, result,vis,pollution,true, true);
    }

    public FormulaApplicationRecipe(String research,ResourceLocation blockResource, AspectList formula, ResourceLocation itemResource, ResourceLocation resultresource,int vis, int pollution,boolean consumeFormula, boolean consumeBlock) {
        this.research = research;
        {
                IForgeRegistry<Block> registry = GameRegistry.findRegistry(Block.class);
        if (registry.containsKey(blockResource)) {
            block = registry.getValue(resultresource);
        } else
            throw new MissingResourceException(blockResource, this.getClass(), "invalid block");
        }
        {
            IForgeRegistry<Item> registry = GameRegistry.findRegistry(Item.class);

            if (registry.containsKey(itemResource)) {
                required = new ItemStack(registry.getValue(itemResource));
            } else
                throw new MissingResourceException(itemResource, this.getClass(), "invalid required item");
        }

        {
            IForgeRegistry<Item> registry = GameRegistry.findRegistry(Item.class);

            if (registry.containsKey(resultresource)) {
                required = new ItemStack(registry.getValue(resultresource));
            } else
                throw new MissingResourceException(resultresource, this.getClass(), "invalid required item");
        }
        this.formula = formula;
        this.vis = vis;
        this.pollution = pollution;
        this.consumeFormula = consumeFormula;
        this.consumeBlock = consumeBlock;
    }

    public FormulaApplicationRecipe(String research,Block block, AspectList formula, ItemStack required, ItemStack result, int vis, int pollution, boolean consumeFormula, boolean consumeBlock) {
        this.research = research;
        this.block = block;
        this.formula = formula;
        this.required = required;
        this.result = result;
        this.vis = vis;
        this.pollution = pollution;
        this.consumeFormula = consumeFormula;
        this.consumeBlock = consumeBlock;
    }


    public boolean canCraft(Block block, AspectList formula, ItemStack item) {
        boolean equal = this.block == block && AspectUtil.aspectListEquals(formula, this.formula);

        if (this.required != null)
            equal &= ItemStack.areItemStacksEqual(this.required, item);

        return equal;
    }

    public Block getBlock() {
        return block;
    }

    public AspectList getFormula() {
        return formula;
    }

    @Nullable
    public ItemStack getRequired() {
        return required;
    }

    public boolean isConsumeBlock() {
        return consumeBlock;
    }

    public ItemStack getResult() {
        return result;
    }

    public boolean isConsumeFormula() {
        return consumeFormula;
    }

    public int getVis() {
        return vis;
    }

    public int getPollution() {
        return pollution;
    }

    public String getResearch() {
        return research;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormulaApplicationRecipe that = (FormulaApplicationRecipe) o;

        return AspectUtil.aspectListEquals(getFormula(), that.getFormula());
    }

    @Override
    public int hashCode() {
        return AspectUtil.hashAspectList(formula);
    }

    @Override
    public String toString() {
        return "FormulaApplicationRecipe{" +
                "block=" + block +
                ", consumeBlock=" + consumeBlock +
                ", formula=" + formula +
                ", required=" + required +
                '}';
    }
}
