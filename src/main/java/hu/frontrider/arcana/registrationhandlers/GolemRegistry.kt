package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.content.golems.MaterialFlesh
import hu.frontrider.arcana.content.golems.MaterialHay
import thaumcraft.api.golems.parts.GolemMaterial

object GolemRegistry {

    fun init(){
        GolemMaterial.register(MaterialFlesh())
        GolemMaterial.register(MaterialHay())
    }
}