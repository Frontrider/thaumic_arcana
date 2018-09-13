package hu.frontrider.arcana.sided.network.creatureenchants

import hu.frontrider.arcana.core.capabilities.CreatureEnchantCapability
import hu.frontrider.arcana.core.capabilities.ICreatureEnchant
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle
import io.netty.buffer.ByteBuf
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.network.ByteBufUtils.*
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.registries.IForgeRegistry

class CreatureEnchantSyncMessage() : IMessage {

    var enchant: ICreatureEnchant? = null
        private set
    var id: Int = 0
        private set

    private val enchantingBaseCircles: IForgeRegistry<EnchantingBaseCircle>
    private val registry: IForgeRegistry<CreatureEnchant>

    init {
        registry = GameRegistry.findRegistry(CreatureEnchant::class.java)
        enchantingBaseCircles = GameRegistry.findRegistry(EnchantingBaseCircle::class.java)
    }

    constructor(enchant: ICreatureEnchant, id: Int) : this() {
        this.enchant = enchant
        this.id = id
    }

    override fun fromBytes(buf: ByteBuf) {
        if (enchant == null)
            enchant = CreatureEnchantCapability()

        id = readVarInt(buf, Integer.BYTES)

        val baseCircle = enchantingBaseCircles.getValue(ResourceLocation(readUTF8String(buf)))
        enchant!!.circle = baseCircle!!


        val count = readVarInt(buf, byteLength)

        for (i in 0 until count) {
            val enchantIndex = readUTF8String(buf)
            val level = readVarInt(buf, byteLength)
            val usedTo = readVarInt(buf, byteLength)
            val creatureEnchant = registry.getValue(ResourceLocation(enchantIndex))
            enchant!!.putEnchant(CreatureEnchantCapability.CreatureEnchantContainer(creatureEnchant!!, level, usedTo))
        }
    }

    override fun toBytes(buf: ByteBuf) {
        val store = enchant!!.store.values
        writeVarInt(buf, id, Integer.BYTES)
        writeUTF8String(buf, enchant!!.circle.registryName!!.toString())

        writeVarInt(buf, store.size, byteLength)
        store.forEach { (creatureEnchant, level, usedTo) ->
            writeUTF8String(buf, creatureEnchant.registryName!!.toString())
            writeVarInt(buf, level, byteLength)
            writeVarInt(buf, usedTo, byteLength)
        }
    }

    companion object {

        private val byteLength = 1
    }
}
