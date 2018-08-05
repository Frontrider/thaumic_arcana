package hu.frontrider.arcana.eventhandlers;

import hu.frontrider.arcana.items.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

@SuppressWarnings("ConstantConditions")
@Mod.EventBusSubscriber(modid = MODID)
public class FunctionEventManager {

    @GameRegistry.ObjectHolder("thaumcraft:salis_mundus")
    private static final Item sal_mundi = null;

    @GameRegistry.ObjectHolder("minecraft:book")
    private static final Item book = null;

    @GameRegistry.ObjectHolder("thaumic_arcana:creature_enchanter")
    private static final Item enchant_book = null;

    @GameRegistry.ObjectHolder("minecraft:enchanting_table")
    private static Block enchanting_table = null;

    @SubscribeEvent
    static void entityRightClick(PlayerInteractEvent.EntityInteract event) {

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
    static void createBook(PlayerInteractEvent.RightClickBlock event) {
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
}
