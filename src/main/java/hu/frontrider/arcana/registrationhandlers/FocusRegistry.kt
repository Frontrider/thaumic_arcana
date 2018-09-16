package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.casters.*
import hu.frontrider.arcana.content.casters.*
import net.minecraft.util.ResourceLocation
import thaumcraft.api.casters.FocusEngine

object FocusRegistry {

    fun init(){
        FocusEngine.registerElement(SelectProtection::class.java, ResourceLocation(MODID,"textures/cenchant/protection.png"),49344)
        FocusEngine.registerElement(SelectStrength::class.java, ResourceLocation(MODID,"textures/cenchant/strength.png"),12603472)
        FocusEngine.registerElement(SelectSpeed::class.java, ResourceLocation(MODID,"textures/cenchant/speed.png"),13487348)
        FocusEngine.registerElement(SelectVitality::class.java, ResourceLocation(MODID,"textures/cenchant/vitality.png"),14548997)

        FocusEngine.registerElement(SelectSpider::class.java, ResourceLocation(MODID,"textures/cenchant/spider.png"),10445833)
        FocusEngine.registerElement(SelectRespiration::class.java, ResourceLocation(MODID,"textures/cenchant/respiration.png"),3986684)
        FocusEngine.registerElement(SelectFertile::class.java, ResourceLocation(MODID,"textures/cenchant/fertile.png"),15121988)

        FocusEngine.registerElement(DisableEnchants::class.java, ResourceLocation(MODID,"textures/cenchant/modifier/disable.png"),0x800080)
        FocusEngine.registerElement(EnableEnchants::class.java, ResourceLocation(MODID,"textures/cenchant/modifier/enable.png"),0xffc0ff)
    }
}