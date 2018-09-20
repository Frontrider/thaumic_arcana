package hu.frontrider.arcana.casters

import hu.frontrider.arcana.content.casters.backend.SetEnchantStatus
import thaumcraft.api.aspects.Aspect

class DisableEnchants: SetEnchantStatus(name = "disable_creature_enchants",requiredAspect = Aspect.FLUX,researchName = "BIOMANCY_BASICS",status = false)
class EnableEnchants: SetEnchantStatus(name = "enable_creature_enchants",requiredAspect = Aspect.AURA,researchName = "BIOMANCY_BASICS",status = true)

