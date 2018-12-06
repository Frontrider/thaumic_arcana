package hu.frontrider.core.gui.widgets.traits

import hu.frontrider.core.gui.widgets.interfaces.Clickable

class ClickAction(val action:()->Unit): Clickable {
    override var width: Int=0
    override var height: Int=0
    override var x:Int=0
    override var y:Int=0
    override fun onClick(mouseX: Int, mouseY: Int, mouseButton: Int) {
        action()
    }
}