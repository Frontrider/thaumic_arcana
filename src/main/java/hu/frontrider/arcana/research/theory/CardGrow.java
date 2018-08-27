package hu.frontrider.arcana.research.theory;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemGenericEssentiaContainer;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

import java.util.Random;

import static net.minecraft.init.Items.WHEAT_SEEDS;

public class CardGrow extends TheorycraftCard {
    static ItemStack[] options;
    private ItemStack itemStack = new ItemStack(WHEAT_SEEDS);

    @Override
    public int getInspirationCost() {
        return 1;
    }

    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        Random r = new Random(this.getSeed());
        this.itemStack = options[r.nextInt(options.length)].copy();
        return true;
    }

    @Override
    public boolean[] getRequiredItemsConsumed() {
        return new boolean[]{true, true};
    }

    @Override
    public ItemStack[] getRequiredItems() {
        ItemStack stack = new ItemStack(ItemsTC.phial, 1, 0);
        ((ItemGenericEssentiaContainer) ItemsTC.crystalEssence).setAspects(stack, new AspectList().add(Aspect.WATER, 10));

        return new ItemStack[]{
                new ItemStack(itemStack.getItem(), 1), stack
        };
    }

    @Override
    public String getLocalizedName() {
        return (new TextComponentTranslation("card.thaumic_arcana.study_plants.name", TextFormatting.DARK_BLUE + this.itemStack.getDisplayName() + TextFormatting.RESET + "" + TextFormatting.BOLD)).getFormattedText();
    }

    @Override
    public String getLocalizedText() {
        return (new TextComponentTranslation("card.thaumic_arcana.study_plants.text", TextFormatting.DARK_BLUE + this.itemStack.getDisplayName() + TextFormatting.RESET)).getFormattedText();
    }

    @Override
    public boolean activate(EntityPlayer entityPlayer, ResearchTableData researchTableData) {
        researchTableData.addTotal(this.getResearchCategory(), 10);
        return true;
    }

    public NBTTagCompound serialize() {
        NBTTagCompound nbt = super.serialize();
        nbt.setString("item", this.itemStack.getItem().getRegistryName().toString());
        return nbt;
    }

    public void deserialize(NBTTagCompound nbt) {
        super.deserialize(nbt);
        Item item = Item.REGISTRY.getObject(new ResourceLocation(nbt.getString("item")));
        //in case the item got nulled.
        if (item == null)
            item = WHEAT_SEEDS;

        this.itemStack = new ItemStack(item, 1);
    }

    @Override
    public String getResearchCategory() {
        return "BIOMANCY";
    }
}
