package hu.frontrider.arcana.client.rendering;

import hu.frontrider.arcana.capabilities.CreatureEnchantCapability;
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
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.tuple.Triple;
import org.lwjgl.opengl.GL11;

import java.util.Collection;
import java.util.Map;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static java.lang.Math.PI;
import static net.minecraft.client.renderer.GlStateManager.*;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class EnchantRenderer implements LayerRenderer {

    ResourceLocation cicle = new ResourceLocation(MODID, "textures/cenchant/enchant_effect.png");
    IForgeRegistry<CreatureEnchant> creatureEnchants;
    CreatureEnchantOffsetManager creatureEnchantOffsetManager;

    public EnchantRenderer() {
        creatureEnchants = GameRegistry.findRegistry(CreatureEnchant.class);
        creatureEnchantOffsetManager = new CreatureEnchantOffsetManager();

        creatureEnchantOffsetManager.loadConfigs();
    }

    @Override
    public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        doRender(entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @SubscribeEvent
    public void renderFirstPerson(RenderWorldLastEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        if (player != null) {
            int thirdPersonView = Minecraft.getMinecraft().gameSettings.thirdPersonView;

            if (thirdPersonView == 0) {
                GlStateManager.translate(0, -1, 0);
                doRender(player, player.limbSwing, player.limbSwingAmount, event.getPartialTicks(), player.ticksExisted, player.cameraYaw, player.cameraPitch, 1);
                GlStateManager.translate(0, 1, 0);
            }
        }
    }

    void doRender(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!CreatureEnchant.isEnchanted(entity)) {
            return;
        }
        EntityEntry entry = EntityRegistry.getEntry(entity.getClass());
        GlStateManager.pushMatrix();
        String name = "null";
        if (entry != null) {
            name = entry.getRegistryName().toString();
        }

        Triple<Float, Float, Float> offset = creatureEnchantOffsetManager.getForEntity(name, entity);
        GlStateManager.translate(offset.getLeft(), offset.getMiddle(), offset.getRight());
        GlStateManager.rotate(180, 0, 1, 1);
        GlStateManager.scale(0.6, 0.6, 0.6);
        GlStateManager.rotate((ageInTicks) / 20.0F * (180F / (float) PI), 0.0F, 0.0F, 1.0F);
        TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;
        GlStateManager.disableCull();
        GlStateManager.disableLighting();

        drawMain(renderEngine, entity);
        GlStateManager.translate(0, 0, -.1);
        drawIcons(renderEngine, entity);
        GlStateManager.translate(0, 0, .1);
        GlStateManager.translate(-offset.getLeft(), -offset.getMiddle(), -offset.getRight());
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    private void drawMain(TextureManager textureManager, Entity entity) {

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
        Map<CreatureEnchant, CreatureEnchantCapability.CreatureEnchantContainer> creatureEnchants = CreatureEnchant.getCreatureEnchants(entity);
        Collection<CreatureEnchantCapability.CreatureEnchantContainer> values = creatureEnchants.values();
        int index = 1;
        for (CreatureEnchantCapability.CreatureEnchantContainer entry : values) {


            Integer level = entry.getLevel();
            CreatureEnchant creatureEnchant = entry.getCreatureEnchant();

            textureManager.bindTexture(creatureEnchant.getIcon());

            switch (level) {
                case 1:
                    GlStateManager.color(.71f, 1, 0);
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

    public void reload() {
        creatureEnchantOffsetManager.loadConfigs();
    }
}
