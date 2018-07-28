package hu.frontrider.arcana.items;

import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.capabilities.CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY;

public class CreatureEnchanter extends Item {

    public CreatureEnchanter() {
        setRegistryName(MODID, "creature_enchanter");
        setUnlocalizedName("creature_enchanter");
        this.setMaxStackSize(1);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase entity, EnumHand hand) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        if (tagCompound == null) return false;
        if (!tagCompound.hasKey("name")) return false;

        if (entity instanceof EntityAgeable) {
            if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
                if (!playerIn.world.isRemote) {
                    ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
                    capability.setName(tagCompound.getString("name"));
                    if (tagCompound.hasKey("level"))
                        capability.setLevel(tagCompound.getInteger("level"));
                    stack.shrink(1);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        if (tagCompound != null) {
            if (tagCompound.hasKey("name") && tagCompound.hasKey("level")) {
                String name = tagCompound.getString("name");
                int level = tagCompound.getInteger("level");
                tooltip.add(I18n.format("enchant.thaumic_arcana.creature_enchant." + name.toLowerCase()) + " " + level);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        return (tagCompound != null) && (tagCompound.hasKey("name"));
    }
}
