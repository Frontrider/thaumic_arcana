package hu.frontrider.arcana.items.formula.text;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.util.AspectUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nullable;
import java.util.List;

import static hu.frontrider.arcana.util.StringUtil.swappedDescription;

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
public class EnchantText implements FormulaText {
    private IForgeRegistry<CreatureEnchant> creatureEnchantIForgeRegistry;

    public EnchantText() {
        creatureEnchantIForgeRegistry = GameRegistry.findRegistry(CreatureEnchant.class);
    }

    @Override
    public void addText(ItemStack stack, NBTTagCompound tagCompound, @Nullable World worldIn, List<String> tooltip) {
        AspectList aspectList = new AspectList();
        aspectList.readFromNBT(tagCompound);
        swappedDescription(tooltip)
                .permanent(description -> creatureEnchantIForgeRegistry.getValuesCollection().forEach(creatureEnchant -> {
                    if (AspectUtil.aspectListEquals(aspectList, creatureEnchant.formula())) {
                        description.add(I18n.format("enchant.creature_enchant.helper.enchant") + ": " + I18n.format(creatureEnchant.getUnlocalizedName()));
                    }
                }))
                .render();
    }
}
