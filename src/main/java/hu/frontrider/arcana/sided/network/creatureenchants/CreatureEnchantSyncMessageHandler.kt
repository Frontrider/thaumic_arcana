package hu.frontrider.arcana.sided.network.creatureenchants

import hu.frontrider.arcana.capabilities.creatureenchant.CreatureEnchantProvider.Companion.CREATURE_ENCHANT_CAPABILITY
import hu.frontrider.arcana.capabilities.creatureenchant.ICreatureEnchant
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import java.util.*

class CreatureEnchantSyncMessageHandler : IMessageHandler<CreatureEnchantSyncMessage, IMessage> {

    override fun onMessage(message: CreatureEnchantSyncMessage, ctx: MessageContext): IMessage? {

        val entityByID = Minecraft.getMinecraft().world.getEntityByID(message.id)

        if (entityByID == null) {
            enchantmentCache[message.id] = message.enchant!!
            return null
        }

        if (entityByID.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            val capability = entityByID.getCapability(CREATURE_ENCHANT_CAPABILITY, null)
            capability!!.store = message.enchant!!.store
            capability.circle = message.enchant!!.circle
        }

        return null
    }

    companion object {

        var enchantmentCache: MutableMap<Int, ICreatureEnchant> = HashMap()

    }
}
