package hu.frontrider.arcana.sided.client.rendering.implantmodel

import hu.frontrider.thaumcraft.researchloader.TCResearchLoader.MODID
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.entity.layers.LayerRenderer
import net.minecraft.client.renderer.vertex.VertexFormat
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

class ImplantRenderer : LayerRenderer<EntityPlayer> {

    init{


    }

    override fun shouldCombineTextures(): Boolean {
        return true
    }


    override fun doRenderLayer(entitylivingbaseIn: EntityPlayer, limbSwing: Float, limbSwingAmount: Float, partialTicks: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float, scale: Float) {


    }
}