package hu.frontrider.arcana.eventhandlers;

import hu.frontrider.arcana.capabilities.CreatureEnchant;
import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import hu.frontrider.arcana.capabilities.IRelief;
import hu.frontrider.arcana.effect.SweetRelief;
import hu.frontrider.arcana.items.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.stream.Collectors;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.capabilities.CreatureEnchant.ENCHANTS.PROTECTION;
import static hu.frontrider.arcana.capabilities.CreatureEnchant.ENCHANTS.STRENGTH;
import static hu.frontrider.arcana.capabilities.CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY;
import static hu.frontrider.arcana.capabilities.ReliefProvider.RELIEF_CAPABILITY;

@SuppressWarnings("ConstantConditions")
@Mod.EventBusSubscriber(modid = MODID)
public class FunctionEventManager {

    @SubscribeEvent
    static void entityHurt(LivingHurtEvent event) {

        EntityLivingBase entity = event.getEntityLiving();
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
            if (capability.getName().equals(PROTECTION.toString())) {
                float amount = event.getAmount() / capability.getLevel();
                System.out.println("event amount = " + event.getAmount());
                System.out.println("amount = " + amount);
                if (amount < 1 && entity.world.rand.nextBoolean())
                    amount = 1;
                event.setAmount(amount);
            }
        }

        DamageSource source = event.getSource();
        if (source.getTrueSource() != null) {
            Entity trueSource = source.getTrueSource();
            if (trueSource.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
                ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
                if (capability.getName().equals(STRENGTH.toString())) {
                    event.setAmount(event.getAmount() + capability.getLevel() * 3);
                }
            }
        }
    }

    @SubscribeEvent
    static void playerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;

        if (player.getActivePotionEffects()
                .stream()
                .filter(potionEffect -> !(potionEffect.getPotion() instanceof SweetRelief))
                .collect(Collectors.toList())
                .size() > 0) {

            if (player.hasCapability(RELIEF_CAPABILITY, null)) {

                IRelief capability = player.getCapability(RELIEF_CAPABILITY, null);
                player.setHealth(player.getHealth() - capability.getHealthStore());
                player.performHurtAnimation();
            }
        }

    }

    @SubscribeEvent
    static void entityTick(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            if (entity.isInWater()) {
                ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
                if (capability.getName().equals(CreatureEnchant.ENCHANTS.RESPIRATION.toString())) {
                    entity.setAir(300);
                }
            }
        }
    }

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
    static void babyEntitySpawn(BabyEntitySpawnEvent entitySpawnEvent) {
        EntityAnimal parentA = (EntityAnimal) entitySpawnEvent.getParentA();
        EntityAnimal parentB = (EntityAnimal) entitySpawnEvent.getParentB();

        if (parentIsFertile(parentA)) {
            createChild(parentA, parentB);
        }
        if (parentIsFertile(parentB)) {
            createChild(parentB, parentA);
        }
    }

    static boolean parentIsFertile(EntityLiving parent) {
        if (parent.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            ICreatureEnchant capability = parent.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
            if (capability.getName().equals(CreatureEnchant.ENCHANTS.FERTILE.toString())) {
                return capability.getLevel() > 0;
            }
        }
        return false;
    }

    static void createChild(EntityAnimal parentA, EntityAnimal parentB) {
        EntityAgeable child = parentA.createChild(parentB);
        child.setPosition(parentA.posX, parentA.posY, parentA.posZ);
        child.setGrowingAge(-24000);
        parentA.world.spawnEntity(child);
    }
}
