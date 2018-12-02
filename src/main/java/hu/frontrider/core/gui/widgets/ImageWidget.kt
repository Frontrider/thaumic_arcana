package hu.frontrider.core.gui.widgets

import hu.frontrider.core.gui.BaseGui
import hu.frontrider.core.gui.widgets.interfaces.Widget
import net.minecraft.client.gui.Gui
import net.minecraft.util.ResourceLocation

class ImageWidget(override var x: Int,
                  override var y: Int,
                  val width:Int,
                  val height:Int,
                  val texture:ResourceLocation) : Widget {
    override fun render(guiBase: BaseGui) {
        guiBase.mc.textureManager.bindTexture(texture)
        Gui.drawModalRectWithCustomSizedTexture(x,y,0f,0f,width,height, width.toFloat(), height.toFloat())
    }
}