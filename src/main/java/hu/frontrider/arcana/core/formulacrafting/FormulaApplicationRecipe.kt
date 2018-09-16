package hu.frontrider.arcana.core.formulacrafting

import hu.frontrider.arcana.registrationhandlers.recipes.MissingResourceException
import hu.frontrider.arcana.registrationhandlers.BlockRegistry.Companion.experimentTable
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
    var required: ItemStack? = null

    lateinit var result: ItemStack
    val vis: Int
    val pollution: Int
    val isConsumeFormula: Boolean
    val isConsumeBlock: Boolean

    lateinit var location:ResourceLocation

    constructor(research: String, blockResource: ResourceLocation? = null, formula: AspectList, itemResource: String? = null, resultResource: String, vis: Int = 0, pollution: Int = 0, consumeFormula: Boolean = true, consumeBlock: Boolean = false) {
        this.research = research

        val blockRegistry = GameRegistry.findRegistry(Block::class.java)
        val itemRegistry = GameRegistry.findRegistry(Item::class.java)


        block = when {
            blockResource == null -> experimentTable
            blockRegistry.containsKey(blockResource) -> blockRegistry.getValue(blockResource)!!
            else -> throw MissingResourceException(blockResource, this.javaClass, "invalid block")
        }


        if(itemResource == null){
            required = null
        }else{
            val split = itemResource.split(";")
            val resourceLocation = ResourceLocation(split[0])
            if(itemRegistry.containsKey(resourceLocation)){
                required = if(split.size>2) {
                    ItemStack(itemRegistry.getValue(resourceLocation)!!,Integer.valueOf(split[2]))
                }
                else {
                    ItemStack(itemRegistry.getValue(resourceLocation)!!)
                }
                if(split.size>1)
                    required!!.count = Integer.valueOf(split[1])
            }else{
                throw MissingResourceException(resourceLocation, this.javaClass, "invalid required item")
            }
        }

        run{
            val split = resultResource.split(";")
            val resourceLocation = ResourceLocation(split[0])
            if(itemRegistry.containsKey(resourceLocation)){
                result = if(split.size>2) {
                    ItemStack(itemRegistry.getValue(resourceLocation)!!,Integer.valueOf(split[2]))
                }
                else {
                    ItemStack(itemRegistry.getValue(resourceLocation)!!)
                }

                if(split.size>1)
                    result.count = Integer.valueOf(split[1])
            }else{
                throw MissingResourceException(resourceLocation, this.javaClass, "invalid required item")
            }
        }

        this.formula = formula
        this.vis = vis
        this.pollution = pollution
        this.isConsumeFormula = consumeFormula
        this.isConsumeBlock = consumeBlock
    }

    constructor(research: String, block: Block = experimentTable, formula: AspectList, required: ItemStack? = null, result: ItemStack, vis: Int = 0, pollution: Int = 0, consumeFormula: Boolean = true, consumeBlock: Boolean = false) {
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
        var equal = this.block === block && AspectUtil.aspectListEquals(formula, this.formula)

        if (this.required != null)
            equal = equal and ItemStack.areItemStacksEqual(this.required!!, item)

        return equal
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as FormulaApplicationRecipe?

        return AspectUtil.aspectListEquals(formula, that!!.formula)
    }

    override fun hashCode(): Int {
        return AspectUtil.hashAspectList(formula)
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
