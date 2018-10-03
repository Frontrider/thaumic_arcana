package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.content.golems.MaterialFlesh
import hu.frontrider.arcana.content.golems.MaterialHay
import thaumcraft.api.golems.parts.GolemMaterial

class GolemRegistry {

    fun init(){
        GolemMaterial.register(MaterialFlesh())
        GolemMaterial.register(MaterialHay())
    }
}