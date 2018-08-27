package hu.frontrider.arcana.items;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.recipes.formulacrafting.FormulaRecipes;
import hu.frontrider.arcana.util.AspectUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.ThaumicArcana.TABARCANA;
import static hu.frontrider.arcana.util.StringUtil.swappedDescription;

public class Formula extends BaseFormula {
    private IForgeRegistry<CreatureEnchant> creatureEnchantIForgeRegistry;

    public Formula() {
        super(new ResourceLocation(MODID, "formula"));
        setMaxStackSize(1);
        creatureEnchantIForgeRegistry = GameRegistry.findRegistry(CreatureEnchant.class);

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        NBTTagCompound tagCompound = stack.getTagCompound();
        if (tagCompound == null)
            return;

        AspectList aspectList = new AspectList();
        aspectList.readFromNBT(tagCompound);
        swappedDescription(tooltip)
                .permanent(description -> {
                    creatureEnchantIForgeRegistry.getValuesCollection().forEach(creatureEnchant -> {
                        if (AspectUtil.aspectListEquals(aspectList, creatureEnchant.formula())) {
                            description.add("Enchants: " + I18n.format(creatureEnchant.getUnlocalizedName()));
                        }
                    });
                })
                .render();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab != TABARCANA)
            return;

        items.add(new ItemStack(this));
        items.addAll(FormulaRecipes.INSTANCE.getRecipes()
                .stream()
                .flatMap(List::stream)
                .map(recipe -> getItemStack(recipe.getFormula()))
                .collect(Collectors.toSet()));
    }
}
