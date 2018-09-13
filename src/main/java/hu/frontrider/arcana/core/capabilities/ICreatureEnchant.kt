package hu.frontrider.arcana.core.capabilities

import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle


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
