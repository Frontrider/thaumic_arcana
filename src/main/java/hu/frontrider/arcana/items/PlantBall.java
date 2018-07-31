package hu.frontrider.arcana.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class PlantBall extends Item {

    PlantBall() {
        super();
        setRegistryName(new ResourceLocation(MODID, "plant_ball"));
        setUnlocalizedName("plant_ball");
    }

    public static ItemStack getBallFor(ItemStack... stacks) {
        ItemStack itemStack = new ItemStack(ItemRegistry.plant_ball);
        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        NBTTagList tagList = Arrays.stream(stacks).map(stack -> {

            String regname = stack.getItem().getRegistryName().toString();
            int metadata = stack.getMetadata();
            int count = stack.getCount();

            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setString("item", regname);
            tagCompound.setInteger("count", count);
            tagCompound.setInteger("meta", metadata);

            return tagCompound;
        }).collect(
                NBTTagList::new,
                NBTTagList::appendTag,
                (e1, e2) -> e2.iterator().forEachRemaining(e2::appendTag)
        );

        nbtTagCompound.setTag("items", tagList);
        itemStack.setTagCompound(nbtTagCompound);

        return itemStack;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        NBTTagCompound tagCompound = stack.getTagCompound();
        NBTTagList products = (NBTTagList) tagCompound.getTag("items");
        tooltip.add(I18n.format("item.plant_ball.contains"));
        products.iterator().forEachRemaining(
                item -> {
                    String regname = ((NBTTagCompound) item).getString("item");
                    int count = ((NBTTagCompound) item).getInteger("count");
                    int meta = ((NBTTagCompound) item).getInteger("meta");
                    ResourceLocation resourcelocation = new ResourceLocation(regname);
                    Item itemObj = Item.REGISTRY.getObject(resourcelocation);
                    String lore = " - " + TextFormatting.GREEN + new ItemStack(itemObj, 1, meta).getDisplayName() + " " + TextFormatting.RESET + count;
                    tooltip.add(lore);
                });
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        ItemStack heldItem = player.getHeldItem(hand);
        NBTTagCompound tagCompound = heldItem.getTagCompound();
        NBTTagList products = (NBTTagList) tagCompound.getTag("items");
        products.iterator().forEachRemaining(
                item -> {
                    String regname = ((NBTTagCompound) item).getString("item");
                    int count = ((NBTTagCompound) item).getInteger("count");
                    int meta = ((NBTTagCompound) item).getInteger("meta");
                    ResourceLocation resourcelocation = new ResourceLocation(regname);
                    Item itemObj = Item.REGISTRY.getObject(resourcelocation);
                    ItemStack itemStack = new ItemStack(itemObj, count, meta);

                    if (!player.addItemStackToInventory(itemStack)) {
                        EntityItem entityItem = new EntityItem(worldIn);
                        entityItem.setItem(itemStack);
                        entityItem.posX = player.posX;
                        entityItem.posY = player.posY;
                        entityItem.posZ = player.posZ;

                        worldIn.spawnEntity(entityItem);
                    }
                }
        );
        heldItem.shrink(1);
        return EnumActionResult.SUCCESS;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab != ItemRegistry.TABARCANA)
            return;
        {
            int meta = 0;

            Item sapling = Item.REGISTRY.getObject(new ResourceLocation("minecraft:sapling"));
            Item log = Item.REGISTRY.getObject(new ResourceLocation("minecraft:log"));
            while (meta < 5) {
                items.add(PlantBall.getBallFor(new ItemStack(sapling, 2, meta), new ItemStack(log, 5, meta)));
                meta++;
            }
        }
    }
}
