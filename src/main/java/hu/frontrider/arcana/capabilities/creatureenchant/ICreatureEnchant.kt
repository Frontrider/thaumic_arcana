package hu.frontrider.arcana.capabilities.creatureenchant

import hu.frontrider.arcana.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.creatureenchant.EnchantingBaseCircle


interface ICreatureEnchant {

    var store: MutableMap<CreatureEnchant, CreatureEnchantCapability.CreatureEnchantContainer>

    var circle: EnchantingBaseCircle

    fun hasEnchant(): Boolean

    fun hasEnchant(enchant: CreatureEnchant): Boolean

    fun getLevel(enchant: CreatureEnchant): Int

    fun setEnabledStatus(enchant: CreatureEnchant, status: Boolean)

    fun setEnabledStatus(status: Boolean)

    fun putEnchant(creatureEnchantContainer: CreatureEnchantCapability.CreatureEnchantContainer)

    fun removeEnchant(enchant: CreatureEnchant)
}
