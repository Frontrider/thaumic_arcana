package hu.frontrider.arcana.sided.client.rendering

import hu.frontrider.arcana.client.rendering.CreatureEnchantOffsetManager
import hu.frontrider.arcana.core.capabilities.CreatureEnchantCapability
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle
import net.minecraft.client.Minecraft
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.entity.layers.LayerRenderer
import net.minecraft.client.renderer.texture.TextureManager
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.EntityEntry
import net.minecraftforge.fml.common.registry.EntityRegistry
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.registries.IForgeRegistry
import org.apache.commons.lang3.tuple.Triple
import org.lwjgl.opengl.GL11

import hu.frontrider.arcana.ThaumicArcana.MODID
import java.lang.Math.PI
import net.minecraft.client.renderer.GlStateManager.*
import org.lwjgl.opengl.GL11.glVertex3f

class EnchantRenderer : LayerRenderer<EntityLivingBase> {


    internal var cicle = ResourceLocation(MODID, "textures/cenchant/enchant_effect.png")
    internal var creatureEnchants: IForgeRegistry<CreatureEnchant>
    internal var creatureEnchantOffsetManager: CreatureEnchantOffsetManager

    init {
        creatureEnchants = GameRegistry.findRegistry(CreatureEnchant::class.java)
        creatureEnchantOffsetManager = CreatureEnchantOffsetManager()

        creatureEnchantOffsetManager.loadConfigs()
    }

    override fun doRenderLayer(entity: EntityLivingBase, limbSwing: Float, limbSwingAmount: Float, partialTicks: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float, scale: Float) {
        doRender(entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale)
    }

    @SubscribeEvent
    fun renderFirstPerson(event: RenderWorldLastEvent) {
        val player = Minecraft.getMinecraft().player

        if (player != null) {
            val thirdPersonView = Minecraft.getMinecraft().gameSettings.thirdPersonView

            if (thirdPersonView == 0) {
                GlStateManager.translate(0f, -1f, 0f)
                doRender(player, player.limbSwing, player.limbSwingAmount, event.partialTicks, player.ticksExisted.toFloat(), player.cameraYaw, player.cameraPitch, 1f)
                GlStateManager.translate(0f, 1f, 0f)
            }
        }
    }

    internal fun doRender(entity: EntityLivingBase, limbSwing: Float, limbSwingAmount: Float, partialTicks: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float, scale: Float) {
        if (!CreatureEnchant.isEnchanted(entity)) {
            return
        }
        val entry = EntityRegistry.getEntry(entity.javaClass)
        GlStateManager.pushMatrix()
        var name = "null"
        if (entry != null) {
            name = entry.registryName!!.toString()
        }

        val offset = creatureEnchantOffsetManager.getForEntity(name, entity)
        GlStateManager.translate(offset.left, offset.middle, offset.right)
        GlStateManager.rotate(180f, 0f, 1f, 1f)
        GlStateManager.scale(0.6, 0.6, 0.6)
        GlStateManager.rotate(ageInTicks / 20.0f * (180f / PI.toFloat()), 0.0f, 0.0f, 1.0f)
        val renderEngine = Minecraft.getMinecraft().renderEngine
        GlStateManager.disableCull()
        GlStateManager.disableLighting()

        drawMain(renderEngine, entity)
        GlStateManager.translate(0.0, 0.0, -.1)
        drawIcons(renderEngine, entity)
        GlStateManager.translate(0.0, 0.0, .1)
        GlStateManager.translate(-offset.left, -offset.middle, -offset.right)
        GlStateManager.enableLighting()
        GlStateManager.enableCull()
        GlStateManager.popMatrix()
    }

    override fun shouldCombineTextures(): Boolean {
        return false
    }

    private fun drawMain(textureManager: TextureManager, entity: Entity) {

        val baseCircle = CreatureEnchant.getBaseCircle(entity)
        val color = baseCircle!!.color
        textureManager.bindTexture(cicle)
        GlStateManager.color(color.r, color.g, color.b)

        glBegin(GL11.GL_QUADS)
        glTexCoord2f(0f, 0f)
        glVertex3f(-2f, -2f, 0f)
        glTexCoord2f(1f, 0f)
        glVertex3f(2f, -2f, 0f)
        glTexCoord2f(1f, 1f)
        glVertex3f(2f, 2f, 0f)
        glTexCoord2f(0f, 1f)
        glVertex3f(-2f, 2f, 0f)
        glEnd()
        GlStateManager.resetColor()
    }

    private fun drawIcons(textureManager: TextureManager, entity: Entity) {
        val creatureEnchants = CreatureEnchant.getCreatureEnchants(entity)
        val values = creatureEnchants.values
        var index = 1
        for ((creatureEnchant, level) in values) {


            textureManager.bindTexture(creatureEnchant.icon)

            when (level) {
                1 -> GlStateManager.color(.71f, 1f, 0f)
                2 -> GlStateManager.color(1f, 0.49f, 0.49f)
                else -> GlStateManager.color(0.83f, 0.49f, 1f)
            }

            val x = (Math.cos((45 * index).toDouble()) * 1.8).toFloat()
            val y = (Math.sin((45 * index).toDouble()) * 1.8).toFloat()

            GlStateManager.translate(x, y, 0f)

            drawIcon()

            GlStateManager.translate(-x, -y, 0f)
            index++
        }
    }

    private fun drawIcon() {

        GL11.glBegin(GL11.GL_QUADS)
        glTexCoord2f(0f, 0f)
        glVertex3f(-.5f, -.5f, 0f)
        glTexCoord2f(1f, 0f)
        glVertex3f(.5f, -.5f, 0f)
        glTexCoord2f(1f, 1f)
        glVertex3f(.5f, .5f, 0f)
        glTexCoord2f(0f, 1f)
        glVertex3f(-.5f, .5f, 0f)
        glEnd()

    }

    fun reload() {
        creatureEnchantOffsetManager.loadConfigs()
    }
}
