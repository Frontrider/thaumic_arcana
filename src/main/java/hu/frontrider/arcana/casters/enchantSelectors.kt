package hu.frontrider.arcana.casters

import hu.frontrider.arcana.casters.backend.EnchantSelectorEffect
import hu.frontrider.arcana.registrationhandlers.CreatureEnchantRegistry.Companion.FERTILE_ENCHANT
import hu.frontrider.arcana.registrationhandlers.CreatureEnchantRegistry.Companion.PROTECTION_ENCHANT
import hu.frontrider.arcana.registrationhandlers.CreatureEnchantRegistry.Companion.RESPIRATION_ENCHANT
import hu.frontrider.arcana.registrationhandlers.CreatureEnchantRegistry.Companion.SPEED_ENCHANT
import hu.frontrider.arcana.registrationhandlers.CreatureEnchantRegistry.Companion.SPIDERFINGERS_ENCHANT
import hu.frontrider.arcana.registrationhandlers.CreatureEnchantRegistry.Companion.STRENGTH_ENCHANT
import hu.frontrider.arcana.registrationhandlers.CreatureEnchantRegistry.Companion.VITALITY_ENCHANT

class SelectProtection : EnchantSelectorEffect(PROTECTION_ENCHANT,"BIOMANCY_BASICS")
class SelectVitality : EnchantSelectorEffect(VITALITY_ENCHANT,"BIOMANCY_BASICS")
class SelectSpeed : EnchantSelectorEffect(SPEED_ENCHANT,"BIOMANCY_BASICS")
class SelectSpider : EnchantSelectorEffect(SPIDERFINGERS_ENCHANT,"BIOMANCY_BASICS")
class SelectStrength : EnchantSelectorEffect(STRENGTH_ENCHANT,"BIOMANCY_BASICS")
class SelectRespiration : EnchantSelectorEffect(RESPIRATION_ENCHANT,"BIOMANCY_BASICS")
class SelectFertile: EnchantSelectorEffect(FERTILE_ENCHANT,"BIOMANCY_BASICS")
