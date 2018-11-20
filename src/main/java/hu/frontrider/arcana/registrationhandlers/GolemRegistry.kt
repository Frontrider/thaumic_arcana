package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.golems.MaterialFlesh
import hu.frontrider.arcana.golems.MaterialHay
import hu.frontrider.arcana.golems.MaterialLeather
import hu.frontrider.arcana.golems.MaterialSlime
import thaumcraft.api.golems.parts.GolemMaterial

class GolemRegistry {

    fun init(){
        GolemMaterial.register(MaterialFlesh())
        GolemMaterial.register(MaterialHay())
        GolemMaterial.register(MaterialSlime())
        GolemMaterial.register(MaterialLeather())
    }
}