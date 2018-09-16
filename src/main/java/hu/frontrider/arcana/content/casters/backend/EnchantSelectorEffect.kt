package hu.frontrider.arcana.content.casters.backend

import hu.frontrider.arcana.core.creatureenchant.CreatureEnchant
import thaumcraft.api.casters.FocusMod

open class EnchantSelectorEffect(val enchant: CreatureEnchant, private val research: String) : FocusMod() {

    override fun execute(): Boolean {
        return true
    }

    override fun getComplexity(): Int {
        return 1
    }

    override fun mustBeSupplied(): Array<EnumSupplyType?> {
        return arrayOfNulls(0)
    }

    override fun willSupply(): Array<EnumSupplyType?> {
        return arrayOfNulls(0)
    }

    override fun getKey(): String {
        return enchant.registryName.toString()
    }

    override fun getResearch(): String? {
        return research
    }
}
