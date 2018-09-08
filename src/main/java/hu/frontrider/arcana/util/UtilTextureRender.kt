package hu.frontrider.arcana.util

import hu.frontrider.arcana.ThaumicArcana
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.opengl.GL11

object UtilTextureRender {
    @SideOnly(Side.CLIENT)
    fun drawTextureSimple(res: ResourceLocation?, x: Int, y: Int, w: Int, h: Int) {
        if (res == null) {
            return
        }
        try {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
            Minecraft.getMinecraft().textureManager.bindTexture(res)
            Gui.drawModalRectWithCustomSizedTexture(x, y, 0f, 0f, w, h, w.toFloat(), h.toFloat())
        } catch (e: NullPointerException) {
            ThaumicArcana.logger.error("Null pointer drawTexture;Simple " + res.resourcePath)
            ThaumicArcana.logger.error(e.message)
            e.printStackTrace()
        } catch (e: net.minecraft.util.ReportedException) {
            ThaumicArcana.logger.error("net.minecraft.util.ReportedException ")
            ThaumicArcana.logger.error(res.resourceDomain + ":" + res.resourcePath)
            ThaumicArcana.logger.error(e.message)
            e.printStackTrace()
        }

    }

    @SideOnly(Side.CLIENT)
    fun drawTextureSquare(img: ResourceLocation?, x: Int, y: Int, dim: Int) {
        if (img == null) {
            return
        }
        drawTextureSimple(img, x, y, dim, dim)
    }
}