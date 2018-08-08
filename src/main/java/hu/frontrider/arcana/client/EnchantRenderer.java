package hu.frontrider.arcana.client;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantRenderer implements LayerRenderer {

    @SubscribeEvent
    public void renderLayer(RenderLivingEvent.Pre<EntityLivingBase> event){
        EntityLivingBase entity = event.getEntity();
        int ageInTicks = entity.ticksExisted;

    }
    //set vertices, set textures, tell glstatemanager to draw it
    @Override
    public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, entity.height-.5, 0);
        GlStateManager.rotate(180, 0, 1, 1);
        GlStateManager.scale(0.6, 0.6, 0.6);
        GlStateManager.rotate((ageInTicks) / 20.0F * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);

        //Minecraft.getMinecraft().renderEngine.bindTexture();
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
