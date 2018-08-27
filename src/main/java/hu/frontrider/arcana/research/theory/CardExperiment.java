package hu.frontrider.arcana.research.theory;

import hu.frontrider.arcana.items.Rodent;
import hu.frontrider.arcana.util.AspectUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

import static hu.frontrider.arcana.ThaumicArcana.MODID;


public class CardExperiment extends TheorycraftCard {
    @GameRegistry.ObjectHolder(MODID + ":rodent")
    static Item rodent = null;

    @Override
    public int getInspirationCost() {
        return 3;
    }

    @Override
    public String getLocalizedName() {
        return (new TextComponentTranslation("card.thaumic_arcana.experiment.name")).toString();
    }

    @Override
    public boolean[] getRequiredItemsConsumed() {
        return new boolean[]{false, true, true};
    }

    @Override
    public String getLocalizedText() {
        return (new TextComponentTranslation("card.thaumic_arcana.experiment.description")).toString();
    }

    @Override
    public ItemStack[] getRequiredItems() {
        return new ItemStack[]{
                new ItemStack(CardExperiment.rodent, 1),
                ThaumcraftApiHelper.makeCrystal(AspectUtil.getRandom()),
                ThaumcraftApiHelper.makeCrystal(AspectUtil.getRandom())
        };
    }

    @Override
    public boolean activate(EntityPlayer player, ResearchTableData data) {
        for (ItemStack item : player.inventory.mainInventory) {
            if (item.getItem() == rodent)
                if (!((Rodent) item.getItem()).isDead(item, player.getEntityWorld())) {
                    player.inventory.addItemStackToInventory(new ItemStack(CardExperiment.rodent, 1));
                    data.addTotal(this.getResearchCategory(), 25);
                    return true;
                }
        }
        return false;
    }

    @Override
    public String getResearchCategory() {
        return "BIOMANCY";
    }
}
