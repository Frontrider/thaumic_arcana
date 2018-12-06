package hu.frontrider.arcana.sided.client.gui

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.blocks.production.tile.TileArcaneSieve
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.ResourceLocation

class GuiArcaneSieve(container: Container, private val playerInv: InventoryPlayer, private val arcaneSieve: TileArcaneSieve) : GuiContainer(container) {

    private val GUI_TEXTURES = ResourceLocation(MODID,"textures/gui/gui_arcane_sieve.png")

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        super.drawScreen(mouseX, mouseY, partialTicks)
        this.renderHoveredToolTip(mouseX, mouseY)
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        this.mc.textureManager.bindTexture(GUI_TEXTURES)
        val i = (this.width - this.xSize) / 2
        val j = (this.height - this.ySize) / 2
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize)
    }
}