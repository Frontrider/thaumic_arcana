package hu.frontrider.arcana.eventhandlers;

import hu.frontrider.arcana.items.EnchantmentUpgradePowder;
import hu.frontrider.arcana.registrationhandlers.ItemRegistry;
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
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import thaumcraft.api.aura.AuraHelper;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.registrationhandlers.ItemRegistry.creature_enchanter;
import static net.minecraft.block.BlockHorizontal.FACING;

@SuppressWarnings("ConstantConditions")
public class FunctionEventManager {

    @ObjectHolder("thaumcraft:salis_mundus")
    private static final Item sal_mundi = null;

    @ObjectHolder("minecraft:book")
    private static final Item book = null;

    @ObjectHolder("thaumic_arcana:creature_enchanter")
    private static final Item enchant_book = null;

    @ObjectHolder("minecraft:enchanting_table")
    private static Block enchanting_table = null;

    @ObjectHolder("thaumcraft:plank_greatwood")
    private static Block greatwood_planks = null;

    @ObjectHolder("minecraft:glass_bottle")
    private static Item glass_bottle = null;

    @ObjectHolder("minecraft:stick")
    private static Item stick = null;

    @ObjectHolder(MODID+":experiment_table")
    private static Block experiment_table = null;

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
                itemOffhand.getItem() == book && AuraHelper.drainVis(world,event.getPos(),20,true)==100) {
            itemMainhand.shrink(1);
            itemOffhand.shrink(1);
            BlockPos pos = event.getPos().up();

            EntityItem entityItem = new EntityItem(world);
            entityItem.setPosition(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);
            entityItem.setItem(new ItemStack(enchant_book));

            world.spawnEntity(entityItem);

            SoundEvent sound = new SoundEvent(new ResourceLocation("thaumcraft:dust"));
            world.playSound(null, pos, sound, SoundCategory.AMBIENT, 1, 1.5f);

            AuraHelper.polluteAura(world,event.getPos(),5,true);
            event.setUseBlock(Event.Result.DENY);
        }
    }
    @SubscribeEvent
    public void createEnchantedBook(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        if (world.isRemote)
            return;

        EntityPlayer player = event.getEntityPlayer();


        ItemStack itemMainhand = player.getHeldItemMainhand();
        ItemStack itemOffhand = player.getHeldItemOffhand();

        if (world.getBlockState(event.getPos()).getBlock() == enchanting_table &&
                itemMainhand.getItem() instanceof EnchantmentUpgradePowder &&
                itemOffhand.getItem() == creature_enchanter) {

            BlockPos pos = event.getPos().up();

            EntityItem entityItem = new EntityItem(world);
            entityItem.setPosition(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);
            ItemStack itemStack = new ItemStack(enchant_book);

            world.spawnEntity(entityItem);
            ((EnchantmentUpgradePowder)itemMainhand.getItem()).transferEnchants(itemMainhand,itemStack);

            entityItem.setItem(itemStack);

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
            itemMainhand.shrink(1);
            itemOffhand.shrink(1);

            event.setUseBlock(Event.Result.DENY);
        }
    }


    //must deny this event, in order to be able to use the book on it
    @SubscribeEvent
    public void selfEnchantFix(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        if (world.isRemote)
            return;

        EntityPlayer player = event.getEntityPlayer();
        ItemStack itemMainhand = player.getHeldItemMainhand();

        if(itemMainhand.getItem() == creature_enchanter) {
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
