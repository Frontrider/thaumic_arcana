package hu.frontrider.arcana.network.falldamage;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FalldamageSyncMessageHandler implements IMessageHandler<FalldamageSyncMessage, IMessage> {
    @Override
    public IMessage onMessage(FalldamageSyncMessage message, MessageContext ctx) {
        ctx.getServerHandler().player.fallDistance = 0.0f;
        return null;
    }
}
