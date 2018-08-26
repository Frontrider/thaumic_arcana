package hu.frontrider.arcana.items;

import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import hu.frontrider.arcana.network.creatureenchants.CreatureEnchantSyncMessage;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nullable;
import java.util.List;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.ThaumicArcana.TABARCANA;
import static hu.frontrider.arcana.capabilities.CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY;
import static net.minecraft.util.EnumActionResult.FAIL;
import static net.minecraft.util.EnumActionResult.SUCCESS;

public class EnchantModifierDust extends ItemBase {
    private static final IForgeRegistry<EnchantingBaseCircle> registry = GameRegistry.findRegistry(EnchantingBaseCircle.class);
    private final SimpleNetworkWrapper networkWrapper;

    @GameRegistry.ObjectHolder("thaumcraft:warpward")
    public static final Potion warpWard = null;


    public EnchantModifierDust(SimpleNetworkWrapper networkWrapper) {
        super();
        this.networkWrapper = networkWrapper;
        setRegistryName(MODID, "enchant_modifier");
        setUnlocalizedName("enchant_modifier");
        this.setMaxStackSize(1);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    boolean applyEntity(Entity entity, EntityPlayer playerIn, ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();

        if (tagCompound == null) return false;

        if (!tagCompound.hasKey("modifier")) return false;

        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            if (!playerIn.getEntityWorld().isRemote) {
                if (AuraHelper.drainVis(entity.world, entity.getPosition(), 30, true) < 30)
                    return false;

                String modifier = tagCompound.getString("modifier");
                EnchantingBaseCircle baseCircle = registry.getValue(new ResourceLocation(modifier));
                ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
                capability.setCircle(baseCircle);
                stack.shrink(1);

                AuraHelper.polluteAura(entity.getEntityWorld(), entity.getPosition(), 10, true);

                int entityId = entity.getEntityId();
                networkWrapper.sendTo(new CreatureEnchantSyncMessage(capability, entityId), (EntityPlayerMP) playerIn);
            }
        }
        return false;
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
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase entity, EnumHand hand) {
        return applyEntity(entity, playerIn, stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        if (tagCompound != null) {
            if (tagCompound.hasKey("modifier")) {

                String name = tagCompound.getString("modifier");
                tooltip.add(I18n.format("enchant.modifier." + name.split(":")[1].toLowerCase()));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        return (tagCompound != null) && (tagCompound.hasKey("modifier"));
    }

    @Override
    public boolean getHasSubtypes() {
        return true;
    }

    public static ItemStack createItem(Item baseItem, EnchantingBaseCircle modifierName) {
        ItemStack enchanter = new ItemStack(baseItem, 1);

        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("modifier", modifierName.getRegistryName().toString());
        enchanter.setTagCompound(compound);
        return enchanter;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == TABARCANA) {
            items.add(new ItemStack(this));
            registry.getValuesCollection().forEach(enchant -> items.add(createItem(this, enchant)));
        }
    }

}
