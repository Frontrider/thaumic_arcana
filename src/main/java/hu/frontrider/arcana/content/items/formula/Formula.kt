package hu.frontrider.arcana.content.items.formula

import hu.frontrider.arcana.ThaumicArcana
import hu.frontrider.arcana.content.items.formula.BaseFormula
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.core.formulacrafting.FormulaApplicationRecipe

import hu.frontrider.arcana.core.formulacrafting.FormulaRecipes
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.registries.IForgeRegistry
import java.util.stream.Collector
import java.util.stream.Collectors
import java.util.stream.Stream

class Formula : BaseFormula(ResourceLocation(ThaumicArcana.MODID, "formula")) {
    private val creatureEnchantIForgeRegistry: IForgeRegistry<CreatureEnchant>

    init {
        setMaxStackSize(1)
        creatureEnchantIForgeRegistry = GameRegistry.findRegistry(CreatureEnchant::class.java)
    }

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        super.addInformation(stack, worldIn, tooltip, flagIn)
    }

    override fun getSubItems(tab: CreativeTabs, items: NonNullList<ItemStack>) {
        if (tab !== ThaumicArcana.TABARCANA)
            return

        items.add(ItemStack(this))
        items.addAll(FormulaRecipes.getRecipesCollection()
                .stream()
                .flatMap<FormulaApplicationRecipe> { it.stream() }
                .map { recipe -> getItemStack(recipe.formula) }
                .collect(Collectors.toSet()))
    }
}
