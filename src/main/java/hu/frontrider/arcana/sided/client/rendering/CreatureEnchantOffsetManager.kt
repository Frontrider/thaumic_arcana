package hu.frontrider.arcana.client.rendering

import hu.frontrider.arcana.ThaumicArcana
import net.minecraft.client.Minecraft
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.text.TextComponentTranslation
import org.apache.commons.lang3.tuple.ImmutableTriple
import org.apache.commons.lang3.tuple.Triple

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.HashMap
import java.util.Properties

/**
 * Some creatures have the enchants in the wrong place. This system creates a customiseable offset.
 */
class CreatureEnchantOffsetManager {

    private var offsets: MutableMap<String, Triple<Float, Float, Float>>? = null

    fun loadConfigs() {
        val file = File(ThaumicArcana.ConfigDirectory.absolutePath + "/enchantingCircleOffsets.properties")

        val offsetProperties = Properties()
        if (!file.exists()) {

            initDefaults(offsetProperties)

            try {
                file.parentFile.mkdirs()
                file.createNewFile()
                offsetProperties.store(FileOutputStream(file),
                        "This file contains the offsets that are used to render the enchantment circles.\n" +
                                "Some entities may render their circles above their heads, or in the ground." +
                                "This file provides a fix for that.")

            } catch (e: IOException) {
                reportError(e,
                        "rendering.thaumic_arcana.reload.failed", {arrayOf("config file:" + file.absolutePath)})
            }

        } else {
            try {
                offsetProperties.load(FileInputStream(file))
            } catch (e: IOException) {
                reportError(e,
                        "rendering.thaumic_arcana.reload.failed",
                        { arrayOf("config file:" + file.absolutePath) })
            }

        }

        if (this.offsets == null) {
            this.offsets = HashMap()
        } else
            this.offsets!!.clear()

        offsetProperties.forEach { key, value ->
            try {
                val split = value.toString().split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                offsets!![key.toString()] = ImmutableTriple(java.lang.Float.valueOf(split[0]), java.lang.Float.valueOf(split[1]), java.lang.Float.valueOf(split[2]))
            } catch (e: Exception) {
                reportError(e,
                        "rendering.thaumic_arcana.reload.failed",
                        { arrayOf("key: $key", "value: $value") })
            }


        }

    }

    fun initDefaults(properties: Properties) {
        properties.setProperty("minecraft:VillagerGolem", "0,1,0")
        properties.setProperty("minecraft:sheep", "0,1.5,0")
        properties.setProperty("minecraft:cow", "0,1.5,0")
        properties.setProperty("minecraft:chicken", "0,1.5,0")
        properties.setProperty("minecraft:shulker", "0,1.5,0")
        properties.setProperty("minecraft:witherSkeleton", "0,1.5,0")

        properties.setProperty("minecraft:guardian", "0,1.4,0")
    }

    /**
     * Returns the offset values for the given entity.
     *
     * @param resourceLocation the location of the entity
     * @return Triple, containing the offsets or null, if no entity was found.
     */
    fun getForEntity(resourceLocation: String, entityLivingBase: EntityLivingBase?): Triple<Float, Float, Float> {
        var height = 0.0f
        if (entityLivingBase != null)
            height = (entityLivingBase.height - .5).toFloat()

        return if (offsets!!.containsKey(resourceLocation)) {
            offsets!![resourceLocation]!!
        } else {
            ImmutableTriple(0.0f, height, 0.0f)
        }
    }

    private fun reportError(e: Exception, languageKey: String, message: () -> Array<String>) {
        val player = Minecraft.getMinecraft().player
        player?.sendMessage(TextComponentTranslation(languageKey))

        for (log in message()) {
            ThaumicArcana.logger.error(log)
        }
        ThaumicArcana.logger.error(e.stackTrace)
    }

}
