package hu.frontrider.arcana.core.capabilities.experiments

import net.minecraft.item.Item

interface IExperimentedWith {

    data class ExperimentEntry(val item:Item,val used:Int)
    fun experimentWith(item: Item,test: Boolean):Int

}