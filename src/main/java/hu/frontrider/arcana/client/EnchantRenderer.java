package hu.frontrider.arcana.client;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static net.minecraft.client.renderer.GlStateManager.glEnd;
import static net.minecraft.client.renderer.GlStateManager.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class EnchantRenderer implements LayerRenderer {

    ResourceLocation cicle = new ResourceLocation(MODID, "textures/enchant/enchant_effect.png");
    //set vertices, set textures, tell glstatemanager to draw it
    @Override
    public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {


        if(!CreatureEnchant.isEnchanted(entity)) {
            return;
        }


        GlStateManager.pushMatrix();
        GlStateManager.translate(0, entity.height - .5, 0);
        GlStateManager.rotate(180, 0, 1, 1);
        GlStateManager.scale(0.6, 0.6, 0.6);
        GlStateManager.rotate((ageInTicks) / 20.0F * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
        TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;
        GlStateManager.disableCull();
        GlStateManager.disableLighting();

        drawMain(renderEngine);

        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    private void drawMain(TextureManager textureManager){

        GlStateManager.color(255,204,0);

        textureManager.bindTexture(cicle);

        GL11.glBegin(GL11.GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex3f(-2, -2, 0);
        glTexCoord2f(1, 0);
        glVertex3f(2, -2, 0);
        glTexCoord2f(1, 1);
        glVertex3f(2, 2, 0);
        glTexCoord2f(0, 1);
        glVertex3f(-2, 2, 0);
        glEnd();
    }
}
