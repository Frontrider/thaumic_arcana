package hu.frontrider.arcana.recipes.formulacrafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class FormulaApplicationRecipe  extends IForgeRegistryEntry.Impl<FormulaApplicationRecipe>{

    private final Block block;
    private final AspectList formula;
    @Nullable
    private final ItemStack itemStack;
    private final boolean consumeBlock;


    public FormulaApplicationRecipe(ResourceLocation block, AspectList formula){
        this(block,formula,null,true);
    }

    public FormulaApplicationRecipe(ResourceLocation block, AspectList formula, ResourceLocation item){
        this(block,formula,item,true);
    }

    public FormulaApplicationRecipe(Block block, AspectList formula){
        this(block,formula,null,true);
    }

    public FormulaApplicationRecipe(Block block, AspectList formula, ItemStack item){
        this(block,formula,item,true);
    }

    public FormulaApplicationRecipe(ResourceLocation block, AspectList formula, ResourceLocation item, boolean consumeBlock){
        //immediately executed functions in java
        this(((Supplier<Block>)()-> GameRegistry.findRegistry(Block.class).getValue(block)).get()
                ,formula
                ,((Supplier<ItemStack>)()->
                {
                    if(item == null)
                        return null;

                    Item item1 = GameRegistry.findRegistry(Item.class).getValue(item);
                    return new ItemStack(item1);
                }).get()
                ,consumeBlock);
    }

    public FormulaApplicationRecipe(Block block, AspectList formula, ItemStack itemStack,boolean consumeBlock){
        this.block = block;
        this.formula = formula;
        this.itemStack = itemStack;
        this.consumeBlock = consumeBlock;
    }
}
