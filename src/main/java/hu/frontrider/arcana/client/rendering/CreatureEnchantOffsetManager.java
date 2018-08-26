package hu.frontrider.arcana.client.rendering;

import hu.frontrider.arcana.ThaumicArcana;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

/**
 * Some creatures have the enchants in the wrong place. This system creates a customiseable offset.
 */
public class CreatureEnchantOffsetManager {

    private Map<String, Triple<Float, Float, Float>> offsets;

    public void loadConfigs() {
        File file = new File(ThaumicArcana.ConfigDirectory.getAbsolutePath() + "/enchantingCircleOffsets.properties");

        Properties offsetProperties = new Properties();
        if (!file.exists()) {

            initDefaults(offsetProperties);

            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                offsetProperties.store(new FileOutputStream(file),
                        "This file contains the offsets that are used to render the enchantment circles.\n" +
                                "Some entities may render their circles above their heads, or in the ground." +
                                "This file provides a fix for that.");

            } catch (IOException e) {
                reportError(e,
                        "rendering.reload.failed",
                        () -> new String[]{
                                "config file:" + file.getAbsolutePath()
                        });
            }
        } else {
            try {
                offsetProperties.load(new FileInputStream(file));
            } catch (IOException e) {
                reportError(e,
                        "rendering.reload.failed",
                        () -> new String[]{
                                "config file:" + file.getAbsolutePath()
                        });
            }
        }

        if (this.offsets == null) {
            this.offsets = new HashMap<>();
        } else
            this.offsets.clear();

        offsetProperties.forEach((key, value) -> {
            try {
                String[] split = value.toString().split(",");
                offsets.put(key.toString(), new ImmutableTriple<>(Float.valueOf(split[0]), Float.valueOf(split[1]), Float.valueOf(split[2])));
            } catch (Exception e) {
                reportError(e,
                        "rendering.reload.failed",
                        () -> new String[]{
                                "key: " + key,
                                "value: " + value
                        });
            }

        });

    }

    public void initDefaults(Properties properties) {
        properties.setProperty("minecraft:VillagerGolem", "0,1,0");
        properties.setProperty("minecraft:sheep", "0,1.5,0");
        properties.setProperty("minecraft:cow", "0,1.5,0");
        properties.setProperty("minecraft:chicken", "0,1.5,0");
        properties.setProperty("minecraft:shulker", "0,1.5,0");
        properties.setProperty("minecraft:wither_skeleton", "0,1.5,0");

        properties.setProperty("minecraft:guardian", "0,1.4,0");
    }

    /**
     * Returns the offset values for the given entity.
     *
     * @param resourceLocation the location of the entity
     * @return Triple, containing the offsets or null, if no entity was found.
     */
    public Triple<Float, Float, Float> getForEntity(String resourceLocation, EntityLivingBase entityLivingBase) {
        float height = 0.0f;
        if(entityLivingBase != null)
            height = (float) (entityLivingBase.height-.5);

        if (offsets.containsKey(resourceLocation)) {
            return offsets.get(resourceLocation);
        } else {
            return new ImmutableTriple<>(0.0f, height, 0.0f);
        }
    }

    private void reportError(Exception e, String languageKey, Supplier<String[]> message) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player != null)
            player.sendMessage(new TextComponentTranslation(languageKey));

        for (String log : message.get()) {
            ThaumicArcana.logger.error(log);
        }
        ThaumicArcana.logger.error(e.getStackTrace());
    }

}
