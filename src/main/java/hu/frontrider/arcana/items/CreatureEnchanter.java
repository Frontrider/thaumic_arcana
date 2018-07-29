package hu.frontrider.arcana.items;

import hu.frontrider.arcana.capabilities.CreatureEnchant;
import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.capabilities.CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY;
import static net.minecraft.util.EnumActionResult.FAIL;
import static net.minecraft.util.EnumActionResult.SUCCESS;

public class CreatureEnchanter extends Item {

    public CreatureEnchanter() {
        setRegistryName(MODID, "creature_enchanter");
        setUnlocalizedName("creature_enchanter");
        this.setMaxStackSize(1);
    }

    private static boolean applyEntity(Entity entity, EntityPlayer playerIn, ItemStack stack) {

        NBTTagCompound tagCompound = stack.getTagCompound();
        if (tagCompound == null) return false;
        if (!tagCompound.hasKey("name")) return false;
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
        return false;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase entity, EnumHand hand) {
        Potion warpWard = Potion.REGISTRY.getObject(new ResourceLocation("thaumcraft:warpward"));
        if (playerIn.getActivePotionEffect(warpWard) != null) {
            return applyEntity(playerIn, playerIn, stack);
        }
        if (entity instanceof EntityAgeable) {
            return applyEntity(entity, playerIn, stack);
        }
        return false;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        Potion warpWard = Potion.REGISTRY.getObject(new ResourceLocation("thaumcraft:warpward"));

        if (player.getActivePotionEffect(warpWard) != null) {
            return applyEntity(player, player, stack) ? SUCCESS : FAIL;
        }
        return FAIL;
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

    @Override
    public boolean getHasSubtypes() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == ItemRegistry.TABARCANA) {
            {
                ItemStack enchanter = new ItemStack(ItemRegistry.creature_enchanter, 1);
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setString("name", CreatureEnchant.ENCHANTS.FERTILE.toString());
                nbtTagCompound.setInteger("level", 1);
                enchanter.setTagCompound(nbtTagCompound);
                items.add(enchanter);
            }
            {
                ItemStack enchanter = new ItemStack(ItemRegistry.creature_enchanter, 1);
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setString("name", CreatureEnchant.ENCHANTS.RESPIRATION.toString());
                nbtTagCompound.setInteger("level", 1);
                enchanter.setTagCompound(nbtTagCompound);
                items.add(enchanter);
            }
            for (int level = 1; level < 4; level++) {
                ItemStack enchanter = new ItemStack(ItemRegistry.creature_enchanter, 1);
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setString("name", CreatureEnchant.ENCHANTS.STRENGTH.toString());
                nbtTagCompound.setInteger("level", level);
                enchanter.setTagCompound(nbtTagCompound);
                items.add(enchanter);
            }

            for (int level = 1; level < 4; level++) {
                ItemStack enchanter = new ItemStack(ItemRegistry.creature_enchanter, 1);
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setString("name", CreatureEnchant.ENCHANTS.PROTECTION.toString());
                nbtTagCompound.setInteger("level", level);
                enchanter.setTagCompound(nbtTagCompound);
                items.add(enchanter);
            }
        }
    }
}
