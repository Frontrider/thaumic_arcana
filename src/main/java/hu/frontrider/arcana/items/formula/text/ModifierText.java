package hu.frontrider.arcana.items.formula.text;

import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
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
public class ModifierText implements FormulaText {

    private IForgeRegistry<EnchantingBaseCircle> creatureEnchantIForgeRegistry;

    public ModifierText() {
        creatureEnchantIForgeRegistry = GameRegistry.findRegistry(EnchantingBaseCircle.class);
    }

    @Override
    public void addText(ItemStack stack, NBTTagCompound tagCompound, @Nullable World worldIn, List<String> tooltip) {
        AspectList aspectList = new AspectList();
        aspectList.readFromNBT(tagCompound);
        swappedDescription(tooltip)
                .permanent(description -> creatureEnchantIForgeRegistry
                        .getValuesCollection()
                        .forEach(modifier -> {
                            if (AspectUtil.aspectListEquals(aspectList, modifier.getFormula())) {
                                description.add(I18n.format("enchant.creature_enchant.helper.modifier") + ": " + I18n.format(modifier.getUnlocalizedName()));
                            }
                        }))
                .render();
    }
}
