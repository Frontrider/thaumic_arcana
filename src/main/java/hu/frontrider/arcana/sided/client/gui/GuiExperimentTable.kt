package hu.frontrider.arcana.sided.client.gui

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import hu.frontrider.arcana.blocks.experiments.tiles.TileEntityExperimentTable

@SideOnly(Side.CLIENT)
class GuiExperimentTable(container: Container, private val playerInv: InventoryPlayer, private val experimentTableTileEntity: TileEntityExperimentTable) : GuiContainer(container) {

    init {

    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {

        val x = (width - xSize) / 2
        val y = (height - ySize) / 2

        this.drawDefaultBackground()

        GlStateManager.color(1f, 1f, 1f, 1f)
        mc.textureManager.bindTexture(WOWRKBENCH)
        for (i in 0..1)
            for(j in 0..2)
                drawTexturedModalRect(x + 92.toFloat() + i * 36, y.toFloat() - 15+j*36, 148, 52, 40, 40)

        mc.textureManager.bindTexture(PAPER)
        drawModalRectWithCustomSizedTexture(x - 20, y - 20, 0f, 0f, xPaperSize / 2, yPaperSize / 2, 128f, 128f)

        mc.textureManager.bindTexture(GUI_BASE)
        //button
        drawTexturedModalRect(x + 75.toFloat(), y.toFloat() + 83, 65, 96, 14, 16)
        //inventory
        drawTexturedModalRect(x.toFloat(), y.toFloat() + 107, 0, 167, 176, 89)
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        super.drawScreen(mouseX, mouseY, partialTicks)
        this.renderHoveredToolTip(mouseX, mouseY)
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        val x = (width - xSize) / 2
        val y = (height - ySize) / 2
            if(mouseX >x+75 && x<x+75+14 && mouseY>y+83 && mouseY<y+83+16){
                println()
            }
    }

    companion object {
        private val PAPER = ResourceLocation("thaumcraft:textures/gui/paper.png")
        //I'm using the slot from here.
        private val WOWRKBENCH = ResourceLocation("thaumcraft:textures/gui/arcaneworkbench.png")

        private val GUI_BASE = ResourceLocation("thaumcraft:textures/gui/gui_base.png")

        /** The X size of the inventory window in pixels.  */
        protected var xPaperSize = 256
        /** The Y size of the inventory window in pixels.  */
        protected var yPaperSize = 256
    }
}
