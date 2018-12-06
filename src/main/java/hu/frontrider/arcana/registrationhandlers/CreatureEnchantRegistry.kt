package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.creatureenchant.base.EffectAmplifierCircle
import hu.frontrider.arcana.creatureenchant.base.NegationCircle
import hu.frontrider.arcana.creatureenchant.base.NormalCircle
import hu.frontrider.arcana.creatureenchant.effect.*
import hu.frontrider.arcana.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.creatureenchant.EnchantingBaseCircle
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class CreatureEnchantRegistry {

    companion object {

        var STRENGTH_ENCHANT: CreatureEnchant = StrengthEnchant()
        var FERTILE_ENCHANT: CreatureEnchant = FertileEnchant()
        var PROTECTION_ENCHANT: CreatureEnchant = ProtectionEnchant()
        var RESPIRATION_ENCHANT: CreatureEnchant = RespirationEnchant()
        var SPIDERFINGERS_ENCHANT: CreatureEnchant = SpiderFingers()
        var SPEED_ENCHANT: CreatureEnchant = Speed()
        var VITALITY_ENCHANT: CreatureEnchant = Vitality()

    }

    @SubscribeEvent
    fun registryEventCreatureEnchant(event: RegistryEvent.Register<CreatureEnchant>) {
        event.registry.registerAll(
                STRENGTH_ENCHANT, SPEED_ENCHANT, SPIDERFINGERS_ENCHANT,
                PROTECTION_ENCHANT, FERTILE_ENCHANT, RESPIRATION_ENCHANT, VITALITY_ENCHANT,
                HealtBoostEnchant(),
                AgilityEnchant(),
                HasteEnchant(),
                ProductivityEnchant()
        )
    }

    @SubscribeEvent
    fun registryEventEnchantingBaseCircle(event: RegistryEvent.Register<EnchantingBaseCircle>) {
        event.registry.registerAll(
                NormalCircle(),
                NegationCircle(),
                EffectAmplifierCircle()
        )
    }

}

