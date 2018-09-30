package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.content.casters.*
import hu.frontrider.arcana.content.casters.*
import net.minecraft.util.ResourceLocation
import thaumcraft.api.casters.FocusEngine

class FocusRegistry {

    fun init(){
        FocusEngine.registerElement(DisableEnchants::class.java, ResourceLocation(MODID,"textures/cenchant/modifier/disable.png"),0x800080)
        FocusEngine.registerElement(EnableEnchants::class.java, ResourceLocation(MODID,"textures/cenchant/modifier/enable.png"),0xffc0ff)
    }
}