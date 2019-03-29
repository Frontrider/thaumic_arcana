package hu.frontrider.arcana.capabilities

import net.minecraft.util.EnumFacing
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.IEssentiaTransport
import kotlin.math.absoluteValue

/**
 * Trait to make it easier to handle Essentia using blocks.
 * because thaumcraft does not handles it as a capability
 *
 * */
class EssentiaRecieverTrait(val aspect: Aspect, val facings: Array<EnumFacing>) : IEssentiaTransport {

    var amount = 0
    var cost = 0

    override fun getMinimumSuction(): Int {
        return 0
    }

    override fun getEssentiaType(facing: EnumFacing?): Aspect? {
        return null
    }

    override fun getEssentiaAmount(p0: EnumFacing?): Int {
        return 0;
    }

    override fun canInputFrom(facing: EnumFacing?): Boolean {
        return facings.contains(facing)
    }

    override fun getSuctionType(p0: EnumFacing?): Aspect? {
        return if (facings.contains(p0)) {
            this.aspect
        } else
            null
    }

    override fun getSuctionAmount(p0: EnumFacing?): Int {
        return if (this.cost - amount > 0) 128 else 0
    }

    override fun isConnectable(facing: EnumFacing): Boolean {
        return facings.contains(facing)
    }

    override fun addEssentia(aspect: Aspect, added: Int, p2: EnumFacing?): Int {
        return if (this.amount < this.cost && aspect === this.aspect) {
            val missing = cost - amount
            val currentlyAdded = missing - added
            return if (currentlyAdded < 0) {
                amount = cost
                currentlyAdded.absoluteValue
            } else {
                amount += currentlyAdded
                0
            }

        } else {
            0
        }
    }

    override fun takeEssentia(p0: Aspect?, p1: Int, p2: EnumFacing?): Int {
        return 0
    }

    override fun setSuction(p0: Aspect?, p1: Int) {

    }

    override fun canOutputTo(p0: EnumFacing?): Boolean {
       return false
    }
}