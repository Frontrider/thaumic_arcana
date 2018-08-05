package hu.frontrider.arcana.eventhandlers;

import hu.frontrider.arcana.capabilities.CreatureEnchantProvider;
import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class LifecycleEventManager {

    private static final ResourceLocation CREATURE_ENCHANT = new ResourceLocation(MODID, "creature_enchant");
    private static final ResourceLocation RELIEF = new ResourceLocation(MODID, "relief");

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        EntityPlayer original = event.getOriginal();

        if (original.hasCapability(CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY, null)) {
            EntityPlayer player = event.getEntityPlayer();
            ICreatureEnchant creatureEnchant = player.getCapability(CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY, null);
            ICreatureEnchant oldCreatureEnchant = original.getCapability(CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY, null);
            creatureEnchant.setStore(oldCreatureEnchant.getStore());
        }
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        Entity object = event.getObject();
        if (object instanceof EntityLiving) {
            event.addCapability(CREATURE_ENCHANT, new CreatureEnchantProvider());
        }
        if (object instanceof EntityPlayer) {
            event.addCapability(CREATURE_ENCHANT, new CreatureEnchantProvider());
        }
    }
}
