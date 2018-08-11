package hu.frontrider.arcana.client;

import hu.frontrider.arcana.creatureenchant.backend.CEnchantment;
import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Map;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static java.lang.Math.PI;
import static net.minecraft.client.renderer.GlStateManager.glEnd;
import static net.minecraft.client.renderer.GlStateManager.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class EnchantRenderer implements LayerRenderer {

    ResourceLocation cicle = new ResourceLocation(MODID, "textures/enchant/enchant_effect.png");

    //set vertices, set textures, tell glstatemanager to draw it
    @Override
    public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        if (!CreatureEnchant.isEnchanted(entity)) {
            return;
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, entity.height - .5, 0);
        GlStateManager.rotate(180, 0, 1, 1);
        GlStateManager.scale(0.6, 0.6, 0.6);
        GlStateManager.rotate((ageInTicks) / 20.0F * (180F / (float) PI), 0.0F, 0.0F, 1.0F);
        TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;
        GlStateManager.disableCull();
        GlStateManager.disableLighting();

        drawMain(renderEngine);

        GlStateManager.translate(1.8, 0, -.1);
        drawIcons(renderEngine, entity);
        GlStateManager.translate(-1.8, 0, .1);

        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    private void drawMain(TextureManager textureManager) {

        GlStateManager.color(255, 204, 0);

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

    private void drawIcons(TextureManager textureManager, Entity entity) {
        Map<CEnchantment, Integer> creatureEnchants = CreatureEnchant.getCreatureEnchants(entity);
        creatureEnchants.forEach((enchantment, level) -> {

            CreatureEnchant creatureEnchant = CreatureEnchant.getForEnum(enchantment);

            textureManager.bindTexture(creatureEnchant.getIcon());
            switch (level) {
                case 1:
                    GlStateManager.color(182, 255, 0);
                    break;
                case 2:
                    GlStateManager.color(255, 127, 127);
                    break;
                default:
                    GlStateManager.color(214, 127, 155);
                    break;
            }

            GlStateManager.translate(1.8/2 * Math.cos(-PI/4), 1.8/2 * Math.sin(-PI/4),0);
            drawIcon();

        });
    }

    private void drawIcon() {

        GL11.glBegin(GL11.GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex3f(-.5f, -.5f, 0);
        glTexCoord2f(1, 0);
        glVertex3f(.5f, -.5f, 0);
        glTexCoord2f(1, 1);
        glVertex3f(.5f, .5f, 0);
        glTexCoord2f(0, 1);
        glVertex3f(-.5f, .5f, 0);
        glEnd();

    }
}
