package hu.frontrider.arcana.network.creatureenchants;

import hu.frontrider.arcana.capabilities.CreatureEnchantCapability;
import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Map;

import static net.minecraftforge.fml.common.network.ByteBufUtils.*;

public class CreatureEnchantSyncMessage implements IMessage {

    private ICreatureEnchant enchant;
    private int id;

    private static final int byteLength = 1;

    IForgeRegistry<EnchantingBaseCircle> enchantingBaseCircles;
    IForgeRegistry<CreatureEnchant> registry;

    public CreatureEnchantSyncMessage() {
        registry = GameRegistry.findRegistry(CreatureEnchant.class);

        enchantingBaseCircles = GameRegistry.findRegistry(EnchantingBaseCircle.class);

    }

    public CreatureEnchantSyncMessage(ICreatureEnchant enchant, int id) {
        this();
        this.enchant = enchant;
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        if (enchant == null)
            enchant = new CreatureEnchantCapability();

        id = readVarInt(buf, Integer.BYTES);

        EnchantingBaseCircle baseCircle = enchantingBaseCircles.getValue(new ResourceLocation(readUTF8String(buf)));
        enchant.setCircle(baseCircle);


        int count = readVarInt(buf, byteLength);

        for (int i = 0; i < count; i++) {
            String enchantIndex = readUTF8String(buf);
            int level = readVarInt(buf, byteLength);
            enchant.getStore().put(registry.getValue(new ResourceLocation(enchantIndex)), level);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        Map<CreatureEnchant, Integer> store = enchant.getStore();
        writeVarInt(buf, id, Integer.BYTES);
        writeUTF8String(buf,enchant.getCircle().getRegistryName().toString());

        writeVarInt(buf, store.size(), byteLength);
        store.forEach((key, value) -> {
            writeUTF8String(buf, key.getRegistryName().toString());
            writeVarInt(buf, value, byteLength);
        });
    }

    public ICreatureEnchant getEnchant() {
        return enchant;
    }

    public int getId() {
        return id;
    }
}
