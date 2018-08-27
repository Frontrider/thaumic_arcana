package hu.frontrider.arcana.recipes.formulacrafting;

import net.minecraft.block.Block;

import java.util.*;

public class FormulaRecipes {
    public static FormulaRecipes INSTANCE;

    Map<Block,List<FormulaApplicationRecipe>> recipes;

    public static void create(){
        INSTANCE = new FormulaRecipes();
    }

    private FormulaRecipes(){
        recipes = new LinkedHashMap<>();
    }

    public void addRecipe(FormulaApplicationRecipe recipe)
    {
        if(recipes.containsKey(recipe.getBlock())) {
            recipes.get(recipe.getBlock()).add(recipe);
        }
        else
        {
            List<FormulaApplicationRecipe> formulaApplicationRecipeArrayList = new LinkedList<>();
            formulaApplicationRecipeArrayList.add(recipe);
            recipes.put(recipe.getBlock(),formulaApplicationRecipeArrayList);
        }
    }

    public Collection<List<FormulaApplicationRecipe>> getRecipes() {
        return recipes.values();
    }
}
