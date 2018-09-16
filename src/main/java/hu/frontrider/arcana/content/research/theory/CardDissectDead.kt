package hu.frontrider.arcana.content.research.theory


import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items.ROTTEN_FLESH
import net.minecraft.init.Items.WHEAT_SEEDS
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.text.TextFormatting
import thaumcraft.api.research.theorycraft.ResearchTableData
import thaumcraft.api.research.theorycraft.TheorycraftCard
import java.util.*

class CardDissectDead : TheorycraftCard() {
    private var itemStack = ItemStack(WHEAT_SEEDS)

    override fun getInspirationCost(): Int {
        return 1
    }

    override fun initialize(player: EntityPlayer?, data: ResearchTableData?): Boolean {
        val r = Random(this.seed)
        this.itemStack = options!![r.nextInt(options!!.size)].copy()
        return true
    }

    override fun getRequiredItemsConsumed(): BooleanArray {
        return booleanArrayOf(true, true)
    }

    override fun getRequiredItems(): Array<ItemStack> {
        return arrayOf(ItemStack(itemStack.item, 1))
    }

    override fun getLocalizedName(): String {
        return TextComponentTranslation("card.thaumic_arcana.study_dead_flesh.name", TextFormatting.DARK_BLUE.toString() + this.itemStack.displayName + TextFormatting.RESET + "" + TextFormatting.BOLD).formattedText
    }

    override fun getLocalizedText(): String {

        return TextComponentTranslation("card.thaumic_arcana.study_dead_flesh.text", TextFormatting.DARK_BLUE.toString() + this.itemStack.displayName + TextFormatting.RESET).formattedText
    }

    override fun activate(entityPlayer: EntityPlayer, researchTableData: ResearchTableData): Boolean {
        researchTableData.addTotal(this.researchCategory, 16)
        return true
    }

    override fun serialize(): NBTTagCompound {
        val nbt = super.serialize()
        nbt.setString("item", this.itemStack.item.registryName!!.toString())
        return nbt
    }

    override fun deserialize(nbt: NBTTagCompound?) {
        super.deserialize(nbt)
        var item = Item.REGISTRY.getObject(ResourceLocation(nbt!!.getString("item")))
        //in case the item got nulled.
        if (item == null)
            item = ROTTEN_FLESH

        this.itemStack = ItemStack(item!!, 1)
    }

    override fun getResearchCategory(): String {
        return "BIOMANCY"
    }

    companion object {
        internal var options: Array<ItemStack>? = null
    }
}
