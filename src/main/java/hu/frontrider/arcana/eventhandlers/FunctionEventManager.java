package hu.frontrider.arcana.eventhandlers;

import hu.frontrider.arcana.items.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static hu.frontrider.arcana.blocks.BlockRegistry.experiment_table;
import static net.minecraft.block.BlockHorizontal.FACING;

@SuppressWarnings("ConstantConditions")
public class FunctionEventManager {

    @GameRegistry.ObjectHolder("thaumcraft:salis_mundus")
    private static final Item sal_mundi = null;

    @GameRegistry.ObjectHolder("minecraft:book")
    private static final Item book = null;

    @GameRegistry.ObjectHolder("thaumic_arcana:creature_enchanter")
    private static final Item enchant_book = null;

    @GameRegistry.ObjectHolder("minecraft:enchanting_table")
    private static Block enchanting_table = null;

    @GameRegistry.ObjectHolder("thaumcraft:plank_greatwood")
    static Block greatwood_planks = null;

    @GameRegistry.ObjectHolder("minecraft:glass_bottle")
    static Item glass_bottle = null;

    @GameRegistry.ObjectHolder("minecraft:stick")
    static Item stick = null;

    @SubscribeEvent
    public void entityRightClick(PlayerInteractEvent.EntityInteract event) {

        Entity target = event.getTarget();
        if (target instanceof EntityAgeable) {
            if (((EntityAgeable) target).isChild()) {
                EntityPlayer player = event.getEntityPlayer();
                ItemStack itemStack = player.getHeldItemMainhand();
                Item item = itemStack.getItem();
                if (item.equals(ItemRegistry.nutrient_mix)) {
                    itemStack.shrink(1);
                    ((EntityAgeable) target).addGrowth(30000);
                }
            }
        }
    }

    @SubscribeEvent
    public void createBook(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        if (world.isRemote)
            return;

        EntityPlayer player = event.getEntityPlayer();


        ItemStack itemMainhand = player.getHeldItemMainhand();
        ItemStack itemOffhand = player.getHeldItemOffhand();

        if (world.getBlockState(event.getPos()).getBlock() == enchanting_table &&
                itemMainhand.getItem() == sal_mundi &&
                itemOffhand.getItem() == book) {
            itemMainhand.shrink(1);
            itemOffhand.shrink(1);
            BlockPos pos = event.getPos().up();

            EntityItem entityItem = new EntityItem(world);
            entityItem.setPosition(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);
            entityItem.setItem(new ItemStack(enchant_book));

            world.spawnEntity(entityItem);

            SoundEvent sound = new SoundEvent(new ResourceLocation("thaumcraft:dust"));
            world.playSound(null, pos, sound, SoundCategory.AMBIENT, 1, 1.5f);

            for (int i = 0; i < 50; i++) {
                world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK,
                        pos.getX() + .5,
                        pos.getY() + .5,
                        pos.getZ() + .5,
                        0, 0, 0
                );
            }

            event.setUseBlock(Event.Result.DENY);
        }

    }

    @SubscribeEvent
    public void buildTable(PlayerInteractEvent.RightClickBlock event) {

        World world = event.getWorld();
        BlockPos pos = event.getPos();

        IBlockState blockState = world.getBlockState(pos);

        if (blockState.getBlock() == greatwood_planks) {
            EntityPlayer player = event.getEntityPlayer();

            if (player.getHeldItemMainhand().getItem() == glass_bottle &&
                    player.getHeldItemMainhand().getCount() >= 2&&
                    player.getHeldItemOffhand().getItem() == stick) {

                EnumFacing facing = player.getAdjustedHorizontalFacing();
                world.setBlockState(pos, experiment_table.getDefaultState().withProperty(FACING, facing));

                player.getHeldItemMainhand().shrink(2);
                player.getHeldItemOffhand().shrink(1);
            }
        }
    }
}
