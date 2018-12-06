package hu.frontrider.arcana.capabilities.scar

import net.minecraft.nbt.NBTTagCompound
import java.util.*

class Limbs {
    var legDamage: Byte = 0
    var bodyDamage: Byte = 0
    var armDamage: Byte = 0
    var headDamage: Byte = 0

    fun toNbt(): NBTTagCompound {
        val nbtTagCompound = NBTTagCompound()
        nbtTagCompound.setByte("leg", legDamage)
        nbtTagCompound.setByte("arm", armDamage)
        nbtTagCompound.setByte("body", bodyDamage)
        nbtTagCompound.setByte("arm", armDamage)
        return nbtTagCompound
    }

    fun fromNbt(compound: NBTTagCompound){
        legDamage = compound.getByte("leg")
        armDamage = compound.getByte("arm")
        bodyDamage = compound.getByte("body")
        armDamage = compound.getByte("arm")
    }

    override fun toString(): String {
        return "Limbs(legDamage=$legDamage, bodyDamage=$bodyDamage, armDamage=$armDamage, headDamage=$headDamage)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Limbs

        if (legDamage != other.legDamage) return false
        if (bodyDamage != other.bodyDamage) return false
        if (armDamage != other.armDamage) return false
        if (headDamage != other.headDamage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = legDamage.toInt()
        result = 31 * result + bodyDamage
        result = 31 * result + armDamage
        result = 31 * result + headDamage
        return result
    }

    fun hurt(amount: Float,random: Random) {

    }


}
