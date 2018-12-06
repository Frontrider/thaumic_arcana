package hu.frontrider.core.gui.widgets

import hu.frontrider.core.gui.BaseGui
import hu.frontrider.core.util.formatTranslate
import hu.frontrider.core.gui.widgets.interfaces.Widget
import hu.frontrider.core.util.translateFormatting

class TextArea(override var x: Int, override var y: Int, val width: Int, val unlocalisedText:String) : Widget {
    override fun render(guiBase: BaseGui) {
        guiBase.drawText(x,y,width, translateFormatting(unlocalisedText),true)
    }
}