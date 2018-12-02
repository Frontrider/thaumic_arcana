package hu.frontrider.core.gui.widgets.interfaces

interface Clickable {
    fun onClick(mouseX: Int, mouseY: Int, mouseButton: Int)
    var x:Int
    var y:Int
    var width:Int
    var height:Int
}