package hu.frontrider.core.gui.widgets.traits

import hu.frontrider.core.gui.BaseGui
import hu.frontrider.core.gui.widgets.interfaces.Widget

class ButtonRender(override var x: Int,
                   override var y: Int,
                   var width:Int,
                   var height:Int) : Widget {

    override fun render(guiBase: BaseGui) {
        guiBase.drawVerticalLinePublic(this.x, this.y, this.y + this.height, 0)
        guiBase.drawVerticalLinePublic(this.x + this.width, this.y, this.y + this.height, 0)
        guiBase.drawHorizontalLinePublic(this.x, this.x + this.width, this.y, 4)
        guiBase.drawHorizontalLinePublic(this.x, this.x + this.width, this.y + this.height, 0)
    }
}