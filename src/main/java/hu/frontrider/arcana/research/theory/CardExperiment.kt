package hu.frontrider.arcana.research.theory

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.items.Rodent
import hu.frontrider.arcana.util.AspectUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.ThaumcraftApiHelper
import thaumcraft.api.research.theorycraft.ResearchTableData
import thaumcraft.api.research.theorycraft.TheorycraftCard


class CardExperiment : TheorycraftCard() {

    override fun getInspirationCost(): Int {
        return 3
    }

    override fun getLocalizedName(): String {
        return TextComponentTranslation("card.thaumic_arcana.experiment.name").toString()
    }

    override fun getRequiredItemsConsumed(): BooleanArray {
        return booleanArrayOf(false, true, true)
    }

    override fun getLocalizedText(): String {
        return TextComponentTranslation("card.thaumic_arcana.experiment.description").toString()
    }

    override fun getRequiredItems(): Array<ItemStack> {
        return arrayOf(ItemStack(CardExperiment.rodent!!, 1), ThaumcraftApiHelper.makeCrystal(AspectUtil.random), ThaumcraftApiHelper.makeCrystal(AspectUtil.random))
    }

    override fun activate(player: EntityPlayer, data: ResearchTableData): Boolean {
        for (item in player.inventory.mainInventory) {
            if (item.item === rodent)
                if (((item.item is Rodent) && !Rodent.isDead(item, player.entityWorld))) {
                    player.inventory.addItemStackToInventory(ItemStack(CardExperiment.rodent!!, 1))
                    data.addTotal(this.researchCategory, 25)
                    item.shrink(1)
                    return true
                }
        }
        return false
    }

    override fun getResearchCategory(): String {
        return "BIOMANCY"
    }

    companion object {
        @GameRegistry.ObjectHolder("$MODID:rodent")
        internal var rodent: Item? = null
    }
}
