package hu.frontrider.core.gui.widgets

import hu.frontrider.core.gui.BaseGui
import hu.frontrider.core.gui.widgets.interfaces.Clickable
import hu.frontrider.core.gui.widgets.traits.ButtonRender
import net.minecraft.client.gui.GuiScreen
import net.minecraft.item.ItemStack

class BorderedItemButton(x:Int,
                         y:Int,
                         itemStack: ItemStack,
                         display:String,
                         action: Clickable,
                         xOffset:Int=16) :
        ItemButton(x, y, itemStack, display, action, xOffset){

    val buttonRender: ButtonRender = ButtonRender(x, y, width, height)

    override fun render(guiBase: BaseGui) {
        super.render(guiBase)
        buttonRender.render(guiBase)

    }

    override fun update() {
        super.update()
        buttonRender.x = x
        buttonRender.y = y
        buttonRender.width = width
        buttonRender.height = height

    }
}