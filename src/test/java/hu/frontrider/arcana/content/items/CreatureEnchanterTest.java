package hu.frontrider.arcana.content.items;

import hu.frontrider.arcana.content.creatureenchant.effect.FertileEnchant;
import hu.frontrider.arcana.core.capabilities.creatureenchant.ICreatureEnchant;
import hu.frontrider.arcana.sided.network.creatureenchants.CreatureEnchantSyncMessage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHealth;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.junit.jupiter.api.*;
import thaumcraft.api.items.ItemsTC;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@Disabled
class CreatureEnchanterTest {
    private CreatureEnchanter creature_enchanter;
    private EntityLivingBase mockentityLiving;
    private ICreatureEnchant capability;
    private World world;
    private EntityPlayerMP player;
    private BlockPos blockPos;
    private ItemStack enchantedItem;
    private EntityItemFrame entityItemFrame;
    private SimpleNetworkWrapper networkWrapper;

    @BeforeEach
    void setUp() {
        Bootstrap.register();

        networkWrapper = mock(SimpleNetworkWrapper.class);

        creature_enchanter = spy(new CreatureEnchanter(networkWrapper));

        mockentityLiving = mock(EntityLivingBase.class);

        entityItemFrame = mock(EntityItemFrame.class);
        enchantedItem = CreatureEnchanter.Companion.createEnchantedItem(new CreatureEnchanter.EnchantmentData(new FertileEnchant(), 1));
        blockPos = spy(new BlockPos(0, 0, 0));

        world = mock(World.class);
       // ReflectionHelper.refectSetValue(world, "isRemote", false);

        player = mock(EntityPlayerMP.class);
        when(player.getEntityWorld()).thenReturn(world);

        //we return something so that it's not null.

        when(mockentityLiving.hasCapability(any(), eq(null))).thenReturn(true);
        capability = mock(ICreatureEnchant.class);
        when(mockentityLiving.getCapability(any(), eq(null))).thenReturn(capability);
    }

    @Nested
    @DisplayName("entity application")
    class Interaction {
        boolean applyEntity;

        @BeforeEach
        void itemInteractionForEntity() {
            applyEntity = creature_enchanter.applyEntity(mockentityLiving, player, enchantedItem);
        }

        @Test
        @DisplayName("was applied")
        void wasApplied() {
            assertTrue(applyEntity);
        }

        @Test
        @DisplayName("capability got the new enchant")
        void capabilityGotEnchant() {

        }

        @Test
        @DisplayName("enchantments were synced to the client")
        void sendsSyncMessage() {
            verify(networkWrapper, times(1)).sendTo(any(CreatureEnchantSyncMessage.class), eq(player));
        }

    }

    @Test
    void EntityInteraction() {
        assertTrue(creature_enchanter.itemInteractionForEntity(enchantedItem, player, mockentityLiving, EnumHand.MAIN_HAND));
        verify(creature_enchanter, times(1)).applyEntity(mockentityLiving, player, enchantedItem);
    }

    @Nested
    @DisplayName("when used on a block")
    class onUse {
        EnumActionResult enumActionResult;
        IBlockState defaultState;

        @Test
        void playerMainhandWasAccessed() {
            verify(player, times(1)).getHeldItemMainhand();
        }

        @Test
        void playerOffhandWasAccessed() {
            verify(player, times(1)).getHeldItemOffhand();
        }

        @Test
        void potionEffectsWereRead() {
            verify(player, times(1)).getActivePotionEffect(any(Potion.class));
        }

        @Test
        void theBlockStateWasAccessed() {
            verify(world, times(1)).getBlockState(blockPos);
        }

        @Test
        void TheBlockWasAccessed() {
            verify(defaultState, times(1)).getBlock();
        }

        @Test
        void theEnchantmentWasApplied() {
            verify(creature_enchanter, times(1)).applyEntity(player, player, enchantedItem);
        }

        @BeforeEach
        @DisplayName("when used properly to apply")
        void onItemUse() {
            ItemsTC.casterBasic = new Item();
            when(player.getHeldItemOffhand()).thenReturn(new ItemStack(ItemsTC.casterBasic));
            when(player.getHeldItemMainhand()).thenReturn(enchantedItem);
            when(player.getActivePotionEffect(any(Potion.class))).thenReturn(new PotionEffect(new PotionHealth(true, 0)));

            defaultState = spy(Blocks.ENCHANTING_TABLE.getDefaultState());
            when(world.getBlockState(blockPos)).thenReturn(defaultState);

            enumActionResult = creature_enchanter.onItemUse(player, world, blockPos, EnumHand.MAIN_HAND, EnumFacing.NORTH, 0, 0, 0);
        }

    }


}