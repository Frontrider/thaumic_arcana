package hu.frontrider.arcana.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nullable;
import java.util.List;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class Rodent extends ItemWithAspects {

    public Rodent() {
        setRegistryName(MODID, "rodent");
        setUnlocalizedName("rodent");

        this.addPropertyOverride(new ResourceLocation("dead"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return isDead(stack, worldIn) ? 0.0F : 1.0F;
            }
        });
        setMaxStackSize(1);
    }

    public AspectList getAspects(ItemStack itemStack) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        AspectList aspectList = new AspectList();
        if (tagCompound == null)
            return aspectList;

        aspectList.readFromNBT(tagCompound);

        return aspectList;
    }

    public boolean isDead(ItemStack itemStack, World world) {
        if (!(itemStack.getItem() instanceof Rodent))
            return false;

        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if(tagCompound == null)
            return true;

        if (!tagCompound.hasKey("lastFed")) {
            return true;
        } else {
            long lastFed = tagCompound.getLong("lastFed");
            //half an our : 36000
            return world.getTotalWorldTime() - lastFed > 1200;
        }
    }

    @Override
    public void setAspects(ItemStack itemStack, AspectList aspectList) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound == null)
            tagCompound = new NBTTagCompound();
        aspectList.writeToNBT(tagCompound);
        itemStack.setTagCompound(tagCompound);
    }

    @Override
    public boolean ignoreContainedAspects() {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if(isDead(stack,worldIn))
            tooltip.add(I18n.format("item.rodent.dead.name"));
    }
}
