package hu.frontrider.arcana.items;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.util.AspectUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nullable;
import java.util.List;

import static hu.frontrider.arcana.ThaumicArcana.TABARCANA;
import static hu.frontrider.arcana.util.StringUtil.swappedDescription;

public class BaseFormula extends ItemWithAspects {

    private IForgeRegistry<CreatureEnchant> creatureEnchantIForgeRegistry;

    public BaseFormula() {

        creatureEnchantIForgeRegistry = GameRegistry.findRegistry(CreatureEnchant.class);
    }

    @Override
    public boolean hasEffect(ItemStack itemStack) {
        return itemStack.hasTagCompound();
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
                   creatureEnchantIForgeRegistry.getValuesCollection().forEach(creatureEnchant -> {
                       if (AspectUtil.aspectListEquals(aspectList, creatureEnchant.formula())) {
                            description.add("- " + I18n.format(creatureEnchant.getUnlocalizedName()));
                       }
                    });
                })
                .render();
    }

    public ItemStack getItemStack(AspectList aspectList) {
        ItemStack itemStack = new ItemStack(this);

        if (aspectList == null)
            return itemStack;

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        aspectList.writeToNBT(nbtTagCompound);
        itemStack.setTagCompound(nbtTagCompound);

        return itemStack;
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
        creatureEnchantIForgeRegistry.getValuesCollection().forEach((creatureEnchant -> items.add(getItemStack(creatureEnchant.formula()))));
    }
}
