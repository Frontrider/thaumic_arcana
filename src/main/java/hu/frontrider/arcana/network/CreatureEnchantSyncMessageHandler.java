package hu.frontrider.arcana.network;

import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.HashMap;
import java.util.Map;

import static hu.frontrider.arcana.capabilities.CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY;

public class CreatureEnchantSyncMessageHandler implements IMessageHandler<CreatureEnchantSyncMessage, IMessage> {

    public static Map<Integer,Map<CreatureEnchant,Integer>> enchantmentCache;

    static{
        enchantmentCache = new HashMap<>();
    }

    @Override
    public IMessage onMessage(CreatureEnchantSyncMessage message, MessageContext ctx) {
        Entity entityByID = Minecraft.getMinecraft().world.getEntityByID(message.getId());
        if(entityByID == null) {
            enchantmentCache.put(message.getId(),message.getEnchant());
            return null;
        }

        if (entityByID.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            ICreatureEnchant capability = entityByID.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
            capability.setStore(message.getEnchant());
        }

        return null;
    }
}
