package hu.frontrider.core.gui.widgets.traits

import hu.frontrider.core.gui.BaseGui
import hu.frontrider.core.gui.widgets.interfaces.Widget
import net.minecraft.item.ItemStack

class ItemWidget(override var x: Int,
                 override var y: Int,
                 val itemStack: ItemStack,
                 val xOffset: Int = 16,
                 val display: String
) : Widget {


    override fun postRender(guiBase: BaseGui) {
        guiBase.drawItemStack(itemStack, x+xOffset, y, 0, display)
    }
}