package hu.frontrider.arcana.client;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.lwjgl.opengl.GL11;

import java.util.Map;
import java.util.Set;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static java.lang.Math.PI;
import static net.minecraft.client.renderer.GlStateManager.*;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class EnchantRenderer implements LayerRenderer {

    ResourceLocation cicle = new ResourceLocation(MODID, "textures/cenchant/enchant_effect.png");
    IForgeRegistry<CreatureEnchant> creatureEnchants;

    public EnchantRenderer(){
        creatureEnchants = GameRegistry.findRegistry(CreatureEnchant.class);
    }


    @Override
    public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
         doRender(entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @SubscribeEvent
    public void renderFirstPerson(RenderWorldLastEvent event){
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        if(player != null) {
            int thirdPersonView = Minecraft.getMinecraft().gameSettings.thirdPersonView;
            if (thirdPersonView == 0) {
                GlStateManager.translate(0, -1, 0);
                doRender(player, player.limbSwing, player.limbSwingAmount, event.getPartialTicks(), player.ticksExisted, player.cameraYaw, player.cameraPitch, 1);
                GlStateManager.translate(0, 1, 0);
            }
        }
    }

    void doRender(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale){
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

        drawMain(renderEngine,entity);
        GlStateManager.translate(0, 0, -.1);
        drawIcons(renderEngine, entity);
        GlStateManager.translate(0, 0, .1);

        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    private void drawMain(TextureManager textureManager,Entity entity) {

        EnchantingBaseCircle baseCircle = CreatureEnchant.getBaseCircle(entity);
        EnchantingBaseCircle.Color color = baseCircle.getColor();
        textureManager.bindTexture(cicle);
        GlStateManager.color(color.getR(), color.getG(), color.getB());

        glBegin(GL11.GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex3f(-2, -2, 0);
        glTexCoord2f(1, 0);
        glVertex3f(2, -2, 0);
        glTexCoord2f(1, 1);
        glVertex3f(2, 2, 0);
        glTexCoord2f(0, 1);
        glVertex3f(-2, 2, 0);
        glEnd();
        GlStateManager.resetColor();


    }

    private void drawIcons(TextureManager textureManager, Entity entity) {
        Map<CreatureEnchant, Integer> creatureEnchants = CreatureEnchant.getCreatureEnchants(entity);
        Set<Map.Entry<CreatureEnchant, Integer>> entries = creatureEnchants.entrySet();
        int index = 1;
        for (Map.Entry<CreatureEnchant, Integer> entry : entries) {


            Integer level = entry.getValue();
            CreatureEnchant creatureEnchant = entry.getKey();

            textureManager.bindTexture(creatureEnchant.getIcon());

            switch (level) {
                case 1:
                    GlStateManager.color(.71f, 1,0);
                    break;
                case 2:
                    GlStateManager.color(1, 0.49f, 0.49f);
                    break;
                default:
                    GlStateManager.color(0.83f, 0.49f, 1);
                    break;
            }

            float x = (float) (Math.cos(45 * index) * 1.8);
            float y = (float) (Math.sin(45 * index) * 1.8);

            GlStateManager.translate(x, y, 0);

            drawIcon();

            GlStateManager.translate(-x, -y, 0);
            index++;
        }
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
