package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.entity.golems.MaterialFlesh
import hu.frontrider.arcana.entity.golems.MaterialHay
import hu.frontrider.arcana.entity.golems.MaterialLeather
import hu.frontrider.arcana.entity.golems.MaterialSlime
import thaumcraft.api.golems.parts.GolemMaterial

class GolemRegistry {

    fun init(){
        GolemMaterial.register(MaterialFlesh())
        GolemMaterial.register(MaterialHay())
        GolemMaterial.register(MaterialSlime())
        GolemMaterial.register(MaterialLeather())
    }
}