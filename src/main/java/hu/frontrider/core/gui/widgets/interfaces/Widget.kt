package hu.frontrider.core.gui.widgets.interfaces

import hu.frontrider.core.gui.BaseGui


interface Widget {
    var x:Int
    var y:Int
    fun render(guiBase: BaseGui){}
    fun postRender(guiBase: BaseGui){}
    fun update(){}
}