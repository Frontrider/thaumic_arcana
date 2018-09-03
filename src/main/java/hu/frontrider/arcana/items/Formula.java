package hu.frontrider.arcana.items;

import hu.frontrider.arcana.ThaumicArcana;
import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.items.formula.BaseFormula;
import hu.frontrider.arcana.recipes.formulacrafting.FormulaRecipes;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class Formula extends BaseFormula {
    private IForgeRegistry<CreatureEnchant> creatureEnchantIForgeRegistry;

    public Formula() {
        super(new ResourceLocation(ThaumicArcana.MODID, "formula"));
        setMaxStackSize(1);
        creatureEnchantIForgeRegistry = GameRegistry.findRegistry(CreatureEnchant.class);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab != ThaumicArcana.INSTANCE.getTABARCANA())
            return;

        items.add(new ItemStack(this));
        items.addAll(FormulaRecipes.INSTANCE.getRecipesCollection()
                .stream()
                .flatMap(List::stream)
                .map(recipe -> getItemStack(recipe.getFormula()))
                .collect(Collectors.toSet()));
    }
}
