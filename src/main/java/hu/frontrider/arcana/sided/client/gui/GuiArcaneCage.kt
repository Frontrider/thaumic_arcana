package hu.frontrider.arcana.sided.client.gui

import hu.frontrider.arcana.content.blocks.experiments.tiles.TileEntityArcaneCage
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import hu.frontrider.arcana.ThaumicArcana.MODID

@SideOnly(Side.CLIENT)
class GuiArcaneCage(container: Container, private val playerInv: InventoryPlayer, private val experimentTableTileEntity: TileEntityArcaneCage) : GuiContainer(container) {

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        this.drawDefaultBackground()

        GlStateManager.color(1f, 1f, 1f, 1f)
        mc.textureManager.bindTexture(BG_TEXTURE)
        val x = (width - xSize) / 2
        val y = (height - ySize) / 2
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize)
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        super.drawScreen(mouseX, mouseY, partialTicks)
        this.renderHoveredToolTip(mouseX, mouseY)
    }

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY)
    }

    companion object {

        private val BG_TEXTURE = ResourceLocation("$MODID:textures/gui/experiment_table_cage.png")
    }
}
