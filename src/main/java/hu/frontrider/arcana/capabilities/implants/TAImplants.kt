package hu.frontrider.arcana.capabilities.implants

import java.util.*

class TAImplants : ITAImplants {

    private val storage = HashMap<Slot, IImplant>()

    override fun getImplants(): Map<Slot, IImplant> {
        return storage
    }

    override fun hasImplant(slot: Slot): Boolean {
        return storage.containsKey(slot)
    }

    override fun getImplant(slot: Slot): Optional<IImplant> {
        if (storage.containsKey(slot))
            return Optional.of(storage[slot]!!)
        return Optional.empty()
    }

    override fun setImplant(slot: Slot, implant: IImplant) {
        storage[slot] = implant
    }
}