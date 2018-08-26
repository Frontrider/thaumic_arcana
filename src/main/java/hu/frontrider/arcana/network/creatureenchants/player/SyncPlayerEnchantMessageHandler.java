package hu.frontrider.arcana.network.creatureenchants.player;

import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.HashMap;
import java.util.Map;

import static hu.frontrider.arcana.capabilities.CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY;

public class SyncPlayerEnchantMessageHandler implements IMessageHandler<SyncPlayerEnchantMessage, IMessage> {

    public static Map<Integer,Map<CreatureEnchant,Integer>> enchantmentCache;

    static{
        enchantmentCache = new HashMap<>();
    }

    @Override
    public IMessage onMessage(SyncPlayerEnchantMessage message, MessageContext ctx) {

        Map<CreatureEnchant, Integer> enchant = message.getEnchant();
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        ICreatureEnchant capability = player.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
        capability.setStore(enchant);

        return null;
    }
}
