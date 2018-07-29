package thaumcraft.api.crafting;

import net.minecraft.item.ItemStack;

/**
 * @author Azanor
 * <p>
 * Used as a placeholder recipe for the thaumonomicon. Currently only used for smelting recipes.
 * Will always be considered a 'fake' recipe.
 */
public class RecipeMisc {

    MiscRecipeType type;
    ItemStack input;
    ItemStack output;

    public RecipeMisc(ItemStack input, ItemStack output, MiscRecipeType type) {
        this.input = input;
        this.output = output;
        this.type = type;
    }

    /**
     * @return the type
     */
    public MiscRecipeType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(MiscRecipeType type) {
        this.type = type;
    }

    /**
     * @return the input
     */
    public ItemStack getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(ItemStack input) {
        this.input = input;
    }

    /**
     * @return the output
     */
    public ItemStack getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(ItemStack output) {
        this.output = output;
    }

    public enum MiscRecipeType {
        SMELTING
    }


}
