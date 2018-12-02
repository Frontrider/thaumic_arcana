package hu.frontrider.core.gui.widgets

import hu.frontrider.core.gui.BaseGui
import hu.frontrider.core.gui.widgets.interfaces.Clickable
import hu.frontrider.core.gui.widgets.interfaces.DebugDraw
import hu.frontrider.core.gui.widgets.interfaces.Widget

class ListWidget(widgets:Array<Widget>, override var x: Int, override var y: Int, val ySpacing:Int, override var width: Int=0, override var height: Int=0): Widget, Clickable,DebugDraw {

    private val widgets:Array<Widget>
    init{
        var yspace = y+2
        this.widgets =widgets.map {
            it.x = x+it.x
            it.y = yspace
            yspace += ySpacing
            it.update()
            it
        }.toTypedArray()
    }
    override fun render(guiBase: BaseGui) {
        widgets.forEach {
            it.render(guiBase)
        }
    }

    override fun postRender(guiBase: BaseGui) {
        widgets.forEach {
            it.postRender(guiBase)
        }
    }

    override fun debugDraw(guiBase: BaseGui) {
        widgets.forEach {
            guiBase.debugDraw(it)
        }
    }

    override fun onClick(mouseX: Int, mouseY: Int, mouseButton: Int) {
        val clickable = (widgets).filter {
            it is Clickable
        }.map {
            it as Clickable
        }.firstOrNull {
            return@firstOrNull it.x <= mouseX && it.x + it.width >= mouseX && it.y <= mouseY && it.y + it.height >= mouseY
        }?:return
        clickable.onClick(mouseX, mouseY, mouseButton)
    }

}