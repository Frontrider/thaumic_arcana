package hu.frontrider.arcana.capabilities.scar

class ScarredCapability : IScarred {

    override var requiresResearch: Boolean =false
    override val scarred: Boolean
        get() = requiredDamage < currentDamage
    override var requiredDamage: Int = 300
    override var currentDamage: Float = 0f
    override var severity: Byte = 1

    override fun toString(): String {
        return "ScarredCapability(requiresResearch=$requiresResearch, requiredDamage=$requiredDamage, currentDamage=$currentDamage, severity=$severity)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ScarredCapability

        if (requiresResearch != other.requiresResearch) return false
        if (requiredDamage != other.requiredDamage) return false
        if (currentDamage != other.currentDamage) return false
        if (severity != other.severity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = requiresResearch.hashCode()
        result = 31 * result + requiredDamage
        result = 31 * result + currentDamage.hashCode()
        result = 31 * result + severity
        return result
    }


}