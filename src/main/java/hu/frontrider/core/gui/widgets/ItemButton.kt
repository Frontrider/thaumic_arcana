package hu.frontrider.core.gui.widgets

import hu.frontrider.core.gui.BaseGui
import hu.frontrider.core.gui.widgets.interfaces.Clickable
import hu.frontrider.core.gui.widgets.interfaces.Widget
import hu.frontrider.core.gui.widgets.traits.ItemWidget
import hu.frontrider.core.util.formatString
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack

open class ItemButton(final override var x: Int,
                      final override var y: Int,
                      val itemStack: ItemStack,
                      val display: String,
                      private val action: Clickable,
                      val xOffset: Int) : Widget, Clickable by action {
    final override var width: Int
    override var height: Int=16
    var itemWidget = ItemWidget(x +xOffset, y, itemStack, xOffset, display)

    init {
        val fontRenderer = Minecraft.getMinecraft().fontRenderer
        width = fontRenderer.getStringWidth(display)+xOffset
    }

    override fun postRender(guiBase: BaseGui) {
        itemWidget.postRender(guiBase)
        guiBase.setZlevel(400f)
        guiBase.drawText(x , y + 4, width, formatString(display), false)
        guiBase.setZlevel(0f)
    }

    override fun update() {
        itemWidget = ItemWidget(x +xOffset, y, itemStack, xOffset, display)
        action.x = x
        action.y = y
        action.width = width
        action.height = height
    }
}