package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle
import hu.frontrider.arcana.creatureenchant.base.NegationCircle
import hu.frontrider.arcana.creatureenchant.base.NormalCircle
import hu.frontrider.arcana.creatureenchant.effect.*
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.registries.RegistryBuilder

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
    fun register(newRegistry: RegistryEvent.NewRegistry) {
        RegistryBuilder<CreatureEnchant>()
                .setType(CreatureEnchant::class.java)
                .setIDRange(0, 100)
                .setName(ResourceLocation(MODID, "creature_enchant"))
                .add { iForgeRegistryInternal, registryManager, i, enchant, v1 -> MinecraftForge.EVENT_BUS.register(enchant) }
                .create()

        RegistryBuilder<EnchantingBaseCircle>()
                .setType(EnchantingBaseCircle::class.java)
                .setIDRange(0, 100)
                .setName(ResourceLocation(MODID, "enchanting_base_circle"))
                .create()

        println("called registry creation events")
    }

    @SubscribeEvent
    fun registryEventCreatureEnchant(event: RegistryEvent.Register<CreatureEnchant>) {
        event.registry.registerAll(
                STRENGTH_ENCHANT, SPEED_ENCHANT, SPIDERFINGERS_ENCHANT,
                PROTECTION_ENCHANT, FERTILE_ENCHANT, RESPIRATION_ENCHANT, VITALITY_ENCHANT
        )
    }

    @SubscribeEvent
    fun registryEventEnchantingBaseCircle(event: RegistryEvent.Register<EnchantingBaseCircle>) {
        event.registry.registerAll(
                NormalCircle(),
                NegationCircle()
        )
    }

}
