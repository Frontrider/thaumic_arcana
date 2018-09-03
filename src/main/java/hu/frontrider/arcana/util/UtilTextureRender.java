package hu.frontrider.arcana.util;

import hu.frontrider.arcana.ThaumicArcana;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class UtilTextureRender {
    @SideOnly(Side.CLIENT)
    public static void drawTextureSimple(ResourceLocation res, int x, int y, int w, int h) {
        if (res == null) {
            return;
        }
        try {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(res);
            Gui.drawModalRectWithCustomSizedTexture(x, y, 0F, 0F, w, h, w, h);
        } catch (NullPointerException e) {
            ThaumicArcana.INSTANCE.getLogger().error("Null pointer drawTexture;Simple " + res.getResourcePath());
            ThaumicArcana.INSTANCE.getLogger().error(e.getMessage());
            e.printStackTrace();
        } catch (net.minecraft.util.ReportedException e) {
            ThaumicArcana.INSTANCE.getLogger().error("net.minecraft.util.ReportedException ");
            ThaumicArcana.INSTANCE.getLogger().error(res.getResourceDomain() + ":" + res.getResourcePath());
            ThaumicArcana.INSTANCE.getLogger().error(e.getMessage());
            e.printStackTrace();
        }
    }

    @SideOnly(Side.CLIENT)
    public static void drawTextureSquare(ResourceLocation img, int x, int y, int dim) {
        if (img == null) {
            return;
        }
        drawTextureSimple(img, x, y, dim, dim);
    }
}