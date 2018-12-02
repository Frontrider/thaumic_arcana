package hu.frontrider.core.gui.widgets

import hu.frontrider.core.gui.BaseGui
import hu.frontrider.core.gui.widgets.interfaces.Clickable
import hu.frontrider.core.gui.widgets.interfaces.Widget
import hu.frontrider.core.gui.widgets.traits.ButtonRender
import hu.frontrider.core.gui.widgets.traits.ItemWidget
import hu.frontrider.core.util.formatString
import hu.frontrider.core.util.translateFormatting
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.item.ItemStack

class TextButton(override var x: Int,
                 override var y: Int,
                 val display: String,
                 private val action: Clickable,
                 override var width: Int=28,val color:Int=0
) : Widget, Clickable by action {
    override var height: Int=16

    init {
        val fontRenderer = Minecraft.getMinecraft().fontRenderer
        width = fontRenderer.getStringWidth(translateFormatting(display))
    }

    override fun render(guiBase: BaseGui) {
        guiBase.setZlevel(400f)
        guiBase.drawText(x , y + 4, width, translateFormatting(display), false,color)
        guiBase.setZlevel(0f)
    }

    override fun update() {
        action.x = x
        action.y = y
        action.width = width
        action.height = height
    }
}