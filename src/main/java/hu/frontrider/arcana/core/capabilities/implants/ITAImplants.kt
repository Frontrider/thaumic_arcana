package hu.frontrider.arcana.core.capabilities.implants

import java.util.*

interface ITAImplants {
    fun getImplants():Map<Slot, IImplant>
    fun hasImplant(slot:Slot):Boolean
    fun getImplant(slot:Slot): Optional<IImplant>
    fun setImplant(slot:Slot,implant: IImplant)
}