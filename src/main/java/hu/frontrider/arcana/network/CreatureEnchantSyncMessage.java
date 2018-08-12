package hu.frontrider.arcana.network;

import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.CEnchantment;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.util.HashMap;
import java.util.Map;

import static net.minecraftforge.fml.common.network.ByteBufUtils.readVarInt;
import static net.minecraftforge.fml.common.network.ByteBufUtils.writeVarInt;

public class CreatureEnchantSyncMessage implements IMessage {

    private Map<CEnchantment, Integer> enchant;
    private int id;

    private static final int byteLength = 1;

    public CreatureEnchantSyncMessage() {
    }

    public CreatureEnchantSyncMessage(ICreatureEnchant enchant, int id) {

        this.enchant = enchant.getStore();
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        if (enchant == null)
            enchant = new HashMap<>();

        id = readVarInt(buf, Integer.BYTES);
        int count = readVarInt(buf, byteLength);
        for (int i = 0; i < count; i++) {
            int enchantIndex = readVarInt(buf, byteLength);
            int level = readVarInt(buf, byteLength);
            enchant.put(CEnchantment.values()[enchantIndex], level);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        writeVarInt(buf, id, Integer.BYTES);
        writeVarInt(buf, enchant.size(), byteLength);
        enchant.forEach((key, value) -> {
            writeVarInt(buf, key.ordinal(), byteLength);
            writeVarInt(buf, value, byteLength);
        });
    }

    public Map<CEnchantment, Integer> getEnchant() {
        return enchant;
    }

    public int getId() {
        return id;
    }
}
