package hu.frontrider.arcana.items;

import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.CEnchantment;
import hu.frontrider.arcana.network.CreatureEnchantSyncMessage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nullable;
import java.util.List;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.ThaumicArcana.TABARCANA;
import static hu.frontrider.arcana.capabilities.CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY;
import static hu.frontrider.arcana.creatureenchant.backend.CEnchantment.*;
import static net.minecraft.util.EnumActionResult.FAIL;
import static net.minecraft.util.EnumActionResult.SUCCESS;

public class CreatureEnchanter extends ItemBase {

    @GameRegistry.ObjectHolder("thaumcraft:warpward")
    static Potion warpWard = null;

    private final SimpleNetworkWrapper networkWrapper;

    public CreatureEnchanter(SimpleNetworkWrapper networkWrapper) {
        super();
        this.networkWrapper = networkWrapper;
        setRegistryName(MODID, "creature_enchanter");
        setUnlocalizedName("creature_enchanter");
        this.setMaxStackSize(1);
    }

    boolean applyEntity(Entity entity, EntityPlayer playerIn, ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();

        if (tagCompound == null) return false;

        if (!tagCompound.hasKey("creature_enchants")) return false;

        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            if (!playerIn.getEntityWorld().isRemote) {
                ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);

                if (capability == null)
                    return false;
                NBTBase creature_enchants = tagCompound.getTag("creature_enchants");
                ((NBTTagList) creature_enchants).iterator().forEachRemaining((enchant) -> {
                    EnchantmentData enchantmentData = nbtToEnchantment((NBTTagCompound) enchant);
                    capability.putEnchant(enchantmentData.enchantment, enchantmentData.level);
                });

                stack.shrink(1);

                int entityId = entity.getEntityId();
                networkWrapper.sendTo(new CreatureEnchantSyncMessage(capability, entityId), (EntityPlayerMP) playerIn);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase entity, EnumHand hand) {

        return applyEntity(entity, playerIn, stack);

    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItemMainhand();
        ItemStack gauntlet = player.getHeldItemOffhand();
        IBlockState blockState = worldIn.getBlockState(pos);
        if (blockState.getBlock() == Blocks.ENCHANTING_TABLE &&
                gauntlet.getItem().equals(ItemsTC.casterBasic) &&
                player.getActivePotionEffect(warpWard) != null) {
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
            if (tagCompound.hasKey("creature_enchants")) {
                NBTTagList creature_enchants = (NBTTagList) tagCompound.getTag("creature_enchants");

                creature_enchants.iterator().forEachRemaining((enchant) -> {
                    String name = ((NBTTagCompound) enchant).getString("name");
                    int level = ((NBTTagCompound) enchant).getInteger("level");
                    tooltip.add(I18n.format("enchant.creature_enchant." + name.toLowerCase()) + " " + level);
                });
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        return (tagCompound != null) && (tagCompound.hasKey("creature_enchants"));
    }

    @Override
    public boolean getHasSubtypes() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == TABARCANA) {
            items.add(new ItemStack(ItemRegistry.creature_enchanter));
            items.add(createEnchantedItem(new EnchantmentData(FERTILE, 1)));
            items.add(createEnchantedItem(new EnchantmentData(RESPIRATION, 1)));

            for (int level = 1; level < 4; level++) {
                items.add(createEnchantedItem(new EnchantmentData(STRENGTH, level)));
            }

            for (int level = 1; level < 4; level++) {
                items.add(createEnchantedItem(new EnchantmentData(PROTECTION, level)));
            }
        }
    }

    public static ItemStack createEnchantedItem(Item baseItem, EnchantmentData... enchantmentDatas) {
        ItemStack enchanter = new ItemStack(baseItem, 1);

        NBTTagList tagList = new NBTTagList();

        for (EnchantmentData enchantmentData : enchantmentDatas) {
            NBTTagCompound enchantmentTag = new NBTTagCompound();
            enchantmentTag.setString("name", enchantmentData.enchantment.name());
            enchantmentTag.setInteger("level", enchantmentData.level);

            tagList.appendTag(enchantmentTag);
        }
        NBTTagCompound compound = new NBTTagCompound();
        compound.setTag("creature_enchants", tagList);
        enchanter.setTagCompound(compound);
        return enchanter;
    }
    public static ItemStack createEnchantedItem(EnchantmentData... enchantmentDatas) {
        return createEnchantedItem(ItemRegistry.creature_enchanter,enchantmentDatas);
    }

    static EnchantmentData nbtToEnchantment(NBTTagCompound nbt) {
        String name = nbt.getString("name");
        int level = nbt.getInteger("level");
        return new EnchantmentData(CEnchantment.valueOf(name), level);
    }

    public static class EnchantmentData {

        private final CEnchantment enchantment;
        private final int level;

        public EnchantmentData(CEnchantment enchantment, int level) {
            this.enchantment = enchantment;
            this.level = level;
        }
    }


}
