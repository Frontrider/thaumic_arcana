package hu.frontrider.core.gui

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.core.gui.widgets.interfaces.Clickable
import hu.frontrider.core.gui.widgets.interfaces.DebugDraw
import hu.frontrider.core.gui.widgets.interfaces.Widget
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation


open class BaseGui : GuiScreen() {
    companion object {
        val BACKGROUND = ResourceLocation(MODID, "textures/gui/guide/base.png")
        val TAB = ResourceLocation(MODID, "textures/gui/guide/tab.png")
        var xSize = 176
        var ySize = 166
        var debug = true
    }

    lateinit var widgets: Array<Widget>
    lateinit var staticWidgets: Array<Widget>

    fun getWidth(): Int {
        return width
    }

    fun getHeight(): Int {
        return height
    }


    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        this.mc.textureManager.bindTexture(BACKGROUND)
        val i = (this.width - xSize) / 2
        val j = (this.height - ySize) / 2
        this.drawTexturedModalRect(i, j, 0, 0, xSize, ySize)
        val arrayOfWidgets = staticWidgets + widgets

        arrayOfWidgets.forEach {
            it.render(this)
        }
        arrayOfWidgets.forEach {
            it.postRender(this)
        }
        if (debug) {

            arrayOfWidgets.forEach {
                debugDraw(it)
            }
        }
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        val clickable = (staticWidgets + widgets).filter {
            it is Clickable
        }.map {
            it as Clickable
        }.firstOrNull {
            return@firstOrNull it.x <= mouseX && it.x + it.width >= mouseX && it.y <= mouseY && it.y + it.height >= mouseY
        } ?: return
        clickable.onClick(mouseX, mouseY, mouseButton)
    }

    internal fun drawItemStack(stack: ItemStack, x: Int, y: Int, xoffset: Int, display: String) {
        GlStateManager.translate(0.0f, 0.0f, 32.0f)
        this.zLevel = 200.0f
        this.itemRender.zLevel = 200.0f
        this.itemRender.renderItemIntoGUI(stack, x, y)
        this.zLevel = 0.0f
        this.itemRender.zLevel = 0.0f
    }

    internal fun drawTab(x: Int, y: Int, resourceLocation: ResourceLocation) {
        this.mc.textureManager.bindTexture(TAB)
        drawModalRectWithCustomSizedTexture(x, y, 0f, 0f, 28, 16, 28f, 16f)
        this.mc.textureManager.bindTexture(resourceLocation)
        drawModalRectWithCustomSizedTexture(x + 6, y, 0f, 0f, 16, 16, 16f, 16f)
    }
    internal fun drawTab(x: Int, y: Int) {
        this.mc.textureManager.bindTexture(TAB)
        drawModalRectWithCustomSizedTexture(x, y, 0f, 0f, 28, 16, 28f, 16f)
    }

    internal fun drawText(x: Int, y: Int, width: Int, formattedText: String, multiLine: Boolean = false,color:Int=0) {
        if (multiLine) {
            fontRenderer.drawSplitString(formattedText, x, y, width, color)
        } else {
            fontRenderer.drawString(formattedText, x, y, color)
        }
    }

    internal fun reset() {
        widgets = arrayOf()
    }

    internal fun debugDraw(widget: Any) {
        this.zLevel = 1000.0f
        if (widget is Clickable) {
            widget.apply {
                drawVerticalLine(this.x, this.y, this.y + this.height, 16737535)
                drawVerticalLine(this.x + this.width, this.y, this.y + this.height, 16737535)
                drawHorizontalLine(this.x, this.x + this.width, this.y, 4)
                drawHorizontalLine(this.x, this.x + this.width, this.y + this.height, 16737535)
            }
        }
        if(widget is DebugDraw){
            widget.debugDraw(this)
        }
        this.zLevel = 0.0f
    }

    internal fun drawVerticalLinePublic(x:Int, startY:Int, y:Int, color:Int){
        drawVerticalLine(x, startY, y, color)
    }

    internal fun drawHorizontalLinePublic(x:Int, endX:Int, y:Int, color:Int){
        drawHorizontalLine(x, endX, y, color)
    }

    internal fun setZlevel(level:Float){
        zLevel=level
    }

}