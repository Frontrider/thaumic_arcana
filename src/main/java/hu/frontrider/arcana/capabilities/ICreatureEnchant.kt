package hu.frontrider.arcana.capabilities

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle


interface ICreatureEnchant {

    var store: MutableMap<CreatureEnchant, CreatureEnchantCapability.CreatureEnchantContainer>

    var circle: EnchantingBaseCircle

    fun hasEnchant(): Boolean

    fun hasEnchant(enchant: CreatureEnchant): Boolean

    fun getLevel(enchant: CreatureEnchant): Int

    fun setEnabledStatus(store: MutableMap<CreatureEnchant, Boolean>)

    fun setEnabledStatus(enchant: CreatureEnchant, status: Boolean)

    fun getEnabledStatus(): MutableMap<CreatureEnchant, Boolean>

    fun putEnchant(creatureEnchantContainer: CreatureEnchantCapability.CreatureEnchantContainer)
}
