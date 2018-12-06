package hu.frontrider.core.gui.widgets

import hu.frontrider.core.gui.BaseGui
import hu.frontrider.core.gui.widgets.interfaces.Clickable
import hu.frontrider.core.gui.widgets.interfaces.Widget
import net.minecraft.util.ResourceLocation

class TabButton(override var x: Int,
                override var y: Int,
                val texture: ResourceLocation,
                val action: Clickable) : Widget, Clickable by action {
    override var height: Int =16

    override var width: Int =28

    override fun render(guiBase: BaseGui) {
        guiBase.drawTab(x, y, texture)
    }
}