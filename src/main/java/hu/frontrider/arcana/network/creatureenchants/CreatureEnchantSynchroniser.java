package hu.frontrider.arcana.network.creatureenchants;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.StartTracking;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static hu.frontrider.arcana.ThaumicArcana.NETWORK_WRAPPER;
import static hu.frontrider.arcana.capabilities.CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY;
import static hu.frontrider.arcana.network.creatureenchants.CreatureEnchantSyncMessageHandler.enchantmentCache;

public class CreatureEnchantSynchroniser {

    @SubscribeEvent
    @SideOnly(Side.SERVER)
    public void EntityTrack(StartTracking event) {
        Entity target = event.getTarget();

        if (target.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            EntityPlayer entityPlayer = event.getEntityPlayer();
            int entityId = target.getEntityId();
            NETWORK_WRAPPER.sendTo(new CreatureEnchantSyncMessage(
                            target.getCapability(CREATURE_ENCHANT_CAPABILITY, null),
                            entityId
                    ), (EntityPlayerMP) entityPlayer
            );
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void entityJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (enchantmentCache.containsKey(entity.getEntityId())) {
            CreatureEnchant.setEnchantment(entity, enchantmentCache.get(entity.getEntityId()));
            enchantmentCache.remove(entity.getEntityId());
        }
    }

    @SubscribeEvent
    @SideOnly(Side.SERVER)
    public void syncPlayer(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if(entity instanceof EntityPlayer){
            int entityId = entity.getEntityId();
            NETWORK_WRAPPER.sendTo(new CreatureEnchantSyncMessage(
                            entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null),
                            entityId
                    ), (EntityPlayerMP) entity
            );
        }
    }

}
