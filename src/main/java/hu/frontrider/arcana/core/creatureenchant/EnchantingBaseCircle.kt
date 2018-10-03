package hu.frontrider.arcana.core.creatureenchant

import net.minecraft.entity.EntityLivingBase
import net.minecraftforge.registries.IForgeRegistryEntry
import thaumcraft.api.aspects.AspectList

/**
 * Stores information about the large circle. Adds a generic effect to all enchantments.
 *
 * @author frontrider
 */
abstract class EnchantingBaseCircle protected constructor(internal val unlocalizedName: String) : IForgeRegistryEntry.Impl<EnchantingBaseCircle>() {

    abstract val color: Color

    abstract val formula: AspectList

    abstract val research: String

    /**
     * Allows the circle to act on the entity Only called when an effect would do something
     *
     * @param multiplier   the multiplier of the enchant
     * @param entityLiving the entity this circle is applied to
     * @param enchant      the enchant that we modify.
     */
    abstract fun doEffect(multiplier: Int, entityLiving: EntityLivingBase, enchant: CreatureEnchant): Int

    fun getUnlocalizedName(): String {
        return "enchant.modifier.$unlocalizedName"
    }

    class Color(r: Int, g: Int, b: Int, a: Int) {

        var r: Float = 0.toFloat()
            internal set
        var g: Float = 0.toFloat()
            internal set
        var b: Float = 0.toFloat()
            internal set
        var a: Float = 0.toFloat()
            internal set

        init {

            this.r = (r / 255).toFloat()
            this.g = (g / 255).toFloat()
            this.b = (b / 255).toFloat()
            this.a = (a / 255).toFloat()
        }
    }
}
