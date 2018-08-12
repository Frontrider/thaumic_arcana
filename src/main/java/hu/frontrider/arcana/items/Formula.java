package hu.frontrider.arcana.items;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.recipes.formulacrafting.FormulaRecipe;
import hu.frontrider.arcana.util.AspectUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;

import javax.annotation.Nullable;
import java.util.List;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.ThaumicArcana.TABARCANA;
import static hu.frontrider.arcana.items.ItemRegistry.formula;
import static hu.frontrider.arcana.util.StringUtil.swappedDescription;

public class Formula extends ItemBase implements IEssentiaContainerItem {

    Formula() {
        setRegistryName(MODID, "formula");
        setUnlocalizedName("formula");
        setMaxStackSize(1);
        setMaxDamage(16);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return stack.getItemDamage() !=0;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return 16744447;
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
                    description.add("Enchants:");
                    CreatureEnchant.getCreatureEnchants().forEach(creatureEnchant -> {
                       if (AspectUtil.aspectListEquals(aspectList, creatureEnchant.formula())) {

                            description.add("- " + I18n.format(creatureEnchant.getUnlocalizedName()));
                       }
                    });
                })
                .render();
    }

    public static ItemStack getItemStack(AspectList aspectList) {
        ItemStack itemStack = new ItemStack(formula);

        if (aspectList == null)
            return itemStack;

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        aspectList.writeToNBT(nbtTagCompound);
        itemStack.setTagCompound(nbtTagCompound);

        return itemStack;
    }

    public AspectList getAspects(ItemStack itemStack) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        AspectList aspectList = new AspectList();
        if (tagCompound == null)
            return aspectList;

        aspectList.readFromNBT(tagCompound);

        return aspectList;
    }

    @Override
    public void setAspects(ItemStack itemStack, AspectList aspectList) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound == null)
            tagCompound = new NBTTagCompound();
        aspectList.writeToNBT(tagCompound);
        itemStack.setTagCompound(tagCompound);
    }

    @Override
    public boolean ignoreContainedAspects() {
        return false;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab != TABARCANA)
            return;
        items.add(new ItemStack(this));
        FormulaRecipe.getFormulaRecipeList().forEach((formulaRecipe -> items.add(getItemStack(formulaRecipe.getFormula()))));
    }
}
