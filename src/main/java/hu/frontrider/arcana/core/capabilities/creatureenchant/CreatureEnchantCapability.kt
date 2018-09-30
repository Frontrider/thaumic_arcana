package hu.frontrider.arcana.core.capabilities.creatureenchant

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import hu.frontrider.arcana.core.creatureenchant.EnchantingBaseCircle
import net.minecraftforge.fml.common.registry.GameRegistry
import java.util.*

class CreatureEnchantCapability : ICreatureEnchant {
    override fun removeEnchant(enchant: CreatureEnchant) {
        enchants.remove(enchant)
    }

    override fun setEnabledStatus(status: Boolean) {
        enchants.keys.forEach {
            enchants[it]?.enabled = status
        }
    }

    override fun setEnabledStatus(enchant: CreatureEnchant, status: Boolean) {
        enchants[enchant]?.enabled = status
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

    private var enchants: MutableMap<CreatureEnchant, CreatureEnchantContainer>

    private var enchantingBaseCircle: EnchantingBaseCircle

    init {
        enchants = HashMap()
        enchantingBaseCircle = baseCircle!!
    }

    override fun hasEnchant(): Boolean {
        if (enchants.isEmpty())
            return false
        return enchants.values
                .map {
                    it.enabled
                }.filter {
                    it
                }.count() > 0
    }

    override fun hasEnchant(enchant: CreatureEnchant): Boolean {
        return if (!enchants.containsKey(enchant))
            false
        else {
            enchants[enchant]!!.enabled
        }
    }

    override fun getLevel(enchant: CreatureEnchant): Int {
        return if (enchants.containsKey(enchant)) if (enchants[enchant]!!.enabled) enchants[enchant]!!.level else 0 else 0
    }

    override fun putEnchant(creatureEnchantContainer: CreatureEnchantContainer) {
        enchants[creatureEnchantContainer.creatureEnchant] = creatureEnchantContainer
    }

    data class CreatureEnchantContainer(val creatureEnchant: CreatureEnchant, val level: Int, val usedTo: Int, var enabled: Boolean = true)

    companion object {

        @GameRegistry.ObjectHolder("$MODID:normal")
        private var baseCircle: EnchantingBaseCircle? = null
    }
}
