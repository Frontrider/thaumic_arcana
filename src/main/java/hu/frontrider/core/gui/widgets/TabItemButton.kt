package hu.frontrider.core.gui.widgets

import hu.frontrider.core.gui.BaseGui
import hu.frontrider.core.gui.widgets.interfaces.Clickable
import hu.frontrider.core.gui.widgets.interfaces.Widget
import hu.frontrider.core.gui.widgets.traits.ItemWidget
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class TabItemButton(override var x: Int,
                    override var y: Int,
                    val itemStack: ItemStack,
                    val action: Clickable) : Widget, Clickable by action {
    override var height: Int =16
    override var width: Int =28
    var itemWidget = ItemWidget(x+8 , y, itemStack, 0, "")


    override fun render(guiBase: BaseGui) {
        guiBase.drawTab(x, y)
    }
    override fun postRender(guiBase: BaseGui) {
        itemWidget.postRender(guiBase)
    }
    override fun update() {
        itemWidget = ItemWidget(x +8, y, itemStack, 0, "")
    }

}