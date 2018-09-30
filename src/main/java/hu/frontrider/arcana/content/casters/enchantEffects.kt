package hu.frontrider.arcana.content.casters

import hu.frontrider.arcana.content.casters.backend.SetEnchantStatus
import thaumcraft.api.aspects.Aspect

class DisableEnchants: SetEnchantStatus(name = "disable_creature_enchants",requiredAspect = Aspect.FLUX,researchName = "ENCHANT_FOCUS@2",status = false)
class EnableEnchants: SetEnchantStatus(name = "enable_creature_enchants",requiredAspect = Aspect.AURA,researchName = "ENCHANT_FOCUS@2",status = true)

