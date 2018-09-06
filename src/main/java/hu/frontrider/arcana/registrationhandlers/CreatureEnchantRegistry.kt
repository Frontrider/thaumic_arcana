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
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.registries.RegistryBuilder


@Mod.EventBusSubscriber
object CreatureEnchantRegistry {
    val STRENGTH_ENCHANT =StrengthEnchant()
    val FERTILE_ENCHANT =FertileEnchant()
    val PROTECTION_ENCHANT =ProtectionEnchant()
    val RESPIRATION_ENCHANT =RespirationEnchant()
    val SPIDERFINGERS_ENCHANT =SpiderFingers()
    val SPEED_ENCHANT =Speed()
    val VITALITY_ENCHANT =Vitality()

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
