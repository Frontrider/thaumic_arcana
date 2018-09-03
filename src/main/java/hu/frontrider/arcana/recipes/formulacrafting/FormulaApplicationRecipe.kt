package hu.frontrider.arcana.recipes.formulacrafting

import hu.frontrider.arcana.recipes.MissingResourceException
import hu.frontrider.arcana.registrationhandlers.BlockRegistry.experimentTable
import hu.frontrider.arcana.util.AspectUtil
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.registries.IForgeRegistryEntry
import thaumcraft.api.aspects.AspectList

class FormulaApplicationRecipe : IForgeRegistryEntry.Impl<FormulaApplicationRecipe> {

    val research: String
    val block: Block
    val formula: AspectList
    var required: ItemStack?=null

    val result: ItemStack
    val vis: Int
    val pollution: Int
    val isConsumeFormula: Boolean
    val isConsumeBlock: Boolean

    constructor(research: String, block: ResourceLocation, formula: AspectList, result: ResourceLocation, consumeFormula: Boolean) : this(research, block, formula, null, result, 0, 0, consumeFormula, true) {}

    constructor(research: String, block: ResourceLocation, formula: AspectList, item: ResourceLocation, result: ResourceLocation, consumeFormula: Boolean, vis: Int, pollution: Int) : this(research, block, formula, item, result, vis, pollution, consumeFormula, false) {}

    constructor(research: String, block: Block, formula: AspectList, result: ItemStack, vis: Int, pollution: Int, consumeFormula: Boolean) : this(research, block, formula, null, result, vis, pollution, consumeFormula, true) {}

    constructor(research: String, block: Block, formula: AspectList, item: ItemStack, result: ItemStack, vis: Int, pollution: Int, consumeFormula: Boolean) : this(research, block, formula, item, result, vis, pollution, consumeFormula, true) {}

    constructor(research: String, block: ResourceLocation, formula: AspectList, result: ResourceLocation, vis: Int, pollution: Int) : this(research, block, formula, null, result, vis, pollution, true, true) {}

    constructor(research: String, block: ResourceLocation, formula: AspectList, item: ResourceLocation, result: ResourceLocation, vis: Int, pollution: Int) : this(research, block, formula, item, result, vis, pollution, true, true) {}

    constructor(research: String, block: Block, formula: AspectList, result: ItemStack, vis: Int, pollution: Int) : this(research, block, formula, null, result, vis, pollution, true, true) {}

    constructor(research: String, block: Block, formula: AspectList, item: ItemStack, result: ItemStack, vis: Int, pollution: Int) : this(research, block, formula, item, result, vis, pollution, true, true) {}

    constructor(research: String, blockResource: ResourceLocation, formula: AspectList, itemResource: ResourceLocation?, resultresource: ResourceLocation, vis: Int=0, pollution: Int=0, consumeFormula: Boolean=true, consumeBlock: Boolean= false) {
        this.research = research

        val blockRegistry = GameRegistry.findRegistry(Block::class.java)
        val itemRegistry = GameRegistry.findRegistry(Item::class.java)
        if (blockRegistry.containsKey(blockResource)) {
            block = blockRegistry.getValue(resultresource)!!
        } else
            throw MissingResourceException(blockResource, this.javaClass, "invalid block")


        if (itemRegistry.containsKey(itemResource)) {
            required = ItemStack(itemRegistry.getValue(itemResource)!!)
        } else
            throw MissingResourceException(itemResource, this.javaClass, "invalid required item")

        if (itemRegistry.containsKey(resultresource)) {
            result = ItemStack(itemRegistry.getValue(resultresource)!!)
        } else
            throw MissingResourceException(resultresource, this.javaClass, "invalid required item")

        this.formula = formula
        this.vis = vis
        this.pollution = pollution
        this.isConsumeFormula = consumeFormula
        this.isConsumeBlock = consumeBlock
    }

    constructor(research: String, block: Block= experimentTable, formula: AspectList, required: ItemStack?, result: ItemStack, vis: Int =0, pollution: Int=0, consumeFormula: Boolean=true, consumeBlock: Boolean=false) {
        this.research = research
        this.block = block
        this.formula = formula
        this.required = required
        this.result = result
        this.vis = vis
        this.pollution = pollution
        this.isConsumeFormula = consumeFormula
        this.isConsumeBlock = consumeBlock
    }


    fun canCraft(block: Block, formula: AspectList, item: ItemStack): Boolean {
        var equal = this.block === block && AspectUtil.aspectListEquals(formula, this.formula!!)

        if (this.required != null)
            equal = equal and ItemStack.areItemStacksEqual(this.required!!, item)

        return equal
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as FormulaApplicationRecipe?

        return AspectUtil.aspectListEquals(formula!!, that!!.formula!!)
    }

    override fun hashCode(): Int {
        return AspectUtil.hashAspectList(formula!!)
    }

    override fun toString(): String {
        return "FormulaApplicationRecipe{" +
                "block=" + block +
                ", consumeBlock=" + isConsumeBlock +
                ", formula=" + formula +
                ", required=" + required +
                '}'.toString()
    }
}
