package hu.frontrider.arcana.core.capabilities

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle
import net.minecraftforge.fml.common.registry.GameRegistry
import java.util.*

class CreatureEnchantCapability : ICreatureEnchant {
    override fun setEnabledStatus(enchant: CreatureEnchant, status: Boolean) {
        if (enabled.containsKey(enchant))
            enabled[enchant] = status
    }

    override var store: MutableMap<CreatureEnchant, CreatureEnchantContainer>
        get() = enchants
        set(value) {
            enchants = value
        }
    override var circle: EnchantingBaseCircle
        get() = enchantingBaseCircle
        set(value) {
            enchantingBaseCircle = value
        }

    override fun setEnabledStatus(store: MutableMap<CreatureEnchant, Boolean>) {
        enabled = store
    }

    override fun getEnabledStatus(): MutableMap<CreatureEnchant, Boolean> {
        return enabled
    }

    private var enchants: MutableMap<CreatureEnchant, CreatureEnchantContainer>
    private var enabled: MutableMap<CreatureEnchant, Boolean>

    private var enchantingBaseCircle: EnchantingBaseCircle

    init {
        enchants = HashMap()
        enabled = HashMap()
        enchantingBaseCircle = baseCircle!!
    }

    override fun hasEnchant(): Boolean {
        if(enchants.isEmpty())
            return false
        return if (enabled.values.isEmpty()) true else enabled.values.contains(true)
    }

    override fun hasEnchant(enchant: CreatureEnchant): Boolean {
        return when {
            !enabled.contains(enchant) -> false
            else -> enabled[enchant]!!
        }
    }

    override fun getLevel(enchant: CreatureEnchant): Int {
        return if (enchants[enchant] != null) enchants[enchant]!!.level else 0
    }

    override fun putEnchant(creatureEnchantContainer: CreatureEnchantContainer) {
        enchants[creatureEnchantContainer.creatureEnchant] = creatureEnchantContainer
        enabled[creatureEnchantContainer.creatureEnchant] = creatureEnchantContainer.enabled
    }


    data class CreatureEnchantContainer(val creatureEnchant: CreatureEnchant, val level: Int, val usedTo: Int, val enabled: Boolean = true)

    companion object {

        @GameRegistry.ObjectHolder("$MODID:normal")
        private var baseCircle: EnchantingBaseCircle? = null
    }
}
