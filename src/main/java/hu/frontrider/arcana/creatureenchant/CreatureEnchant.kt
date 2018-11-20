package hu.frontrider.arcana.creatureenchant

import hu.frontrider.arcana.capabilities.creatureenchant.ICreatureEnchant
import hu.frontrider.arcana.util.AspectUtil
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.registries.IForgeRegistryEntry
import thaumcraft.api.aspects.AspectList

import hu.frontrider.arcana.ThaumicArcana.MODID
import hu.frontrider.arcana.capabilities.creatureenchant.CreatureEnchantCapability
import hu.frontrider.arcana.capabilities.creatureenchant.CreatureEnchantProvider.Companion.CREATURE_ENCHANT_CAPABILITY

abstract class CreatureEnchant(resourceLocation: ResourceLocation, private val unlocalizedName: String) : IForgeRegistryEntry.Impl<CreatureEnchant>() {

    val icon: ResourceLocation

    abstract val research: String

    init {
        registryName = resourceLocation
        icon = ResourceLocation(resourceLocation.resourceDomain, "textures/cenchant/" + resourceLocation.resourcePath + ".png")
    }

    fun getUnlocalizedName(): String {
        return "enchant.creature_enchant.$unlocalizedName"
    }

    open fun getEnchantLevel(entity: EntityLivingBase, enchantment: CreatureEnchant): Int {
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            val capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null)!!
            val level = capability.getLevel(enchantment)
            if (capability.hasEnchant(enchantment))
                return capability.circle.doEffect(level, entity, enchantment)
        }
        return 0
    }

    abstract fun formula(): AspectList


    override fun toString(): String {
        return "CreatureEnchant{" +
                ", registryName=" + registryName +
                ", research='" + research + '\''.toString() +
                '}'.toString()
    }

    companion object {

        @GameRegistry.ObjectHolder("$MODID:normal")
        private val baseCircle: EnchantingBaseCircle? = null

        fun setEnchantment(entity: Entity, enchants: ICreatureEnchant) {
            if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
                val capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null)

                capability!!.store = enchants.store
                capability.circle = enchants.circle
            }
        }

        fun isEnchanted(entity: Entity): Boolean {
            if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
                val capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null)
                return capability!!.hasEnchant()
            }
            return false
        }

        fun getCreatureEnchants(entity: Entity): Map<CreatureEnchant, CreatureEnchantCapability.CreatureEnchantContainer> {
            if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
                val capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null)
                return capability!!.store
            }

            return emptyMap()
        }

        fun getBaseCircle(entity: Entity): EnchantingBaseCircle? {
            if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
                val capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null)
                return capability!!.circle
            }

            return baseCircle
        }


        fun getForFormula(formula: AspectList): CreatureEnchant? {
            for (enchant in GameRegistry.findRegistry(CreatureEnchant::class.java).valuesCollection) {
                if (AspectUtil.aspectListEquals(enchant.formula(), formula))
                    return enchant
            }
            return null
        }
    }
}

