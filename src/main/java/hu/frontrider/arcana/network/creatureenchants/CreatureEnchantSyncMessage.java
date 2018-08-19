package hu.frontrider.arcana.network.creatureenchants;

import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;
import java.util.Map;

import static net.minecraftforge.fml.common.network.ByteBufUtils.*;

public class CreatureEnchantSyncMessage implements IMessage {

    private Map<CreatureEnchant, Integer> enchant;
    private int id;

    private static final int byteLength = 1;

    IForgeRegistry<CreatureEnchant> registry;

    public CreatureEnchantSyncMessage() {
        registry = GameRegistry.findRegistry(CreatureEnchant.class);

    }

    public CreatureEnchantSyncMessage(ICreatureEnchant enchant, int id) {
        this();
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
            String enchantIndex = readUTF8String(buf);
            int level = readVarInt(buf, byteLength);
            enchant.put(registry.getValue(new ResourceLocation(enchantIndex)), level);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        writeVarInt(buf, id, Integer.BYTES);
        writeVarInt(buf, enchant.size(), byteLength);
        enchant.forEach((key, value) -> {
            writeUTF8String(buf, key.getRegistryName().toString());
            writeVarInt(buf, value, byteLength);
        });
    }

    public Map<CreatureEnchant, Integer> getEnchant() {
        return enchant;
    }

    public int getId() {
        return id;
    }
}
