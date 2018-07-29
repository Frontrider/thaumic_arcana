package hu.frontrider.arcana.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    public void getSubItems(Item item, CreativeTabs tab, List itemList) {

    }
}
