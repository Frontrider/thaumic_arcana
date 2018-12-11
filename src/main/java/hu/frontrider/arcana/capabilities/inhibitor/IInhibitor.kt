package hu.frontrider.arcana.capabilities.inhibitor

import hu.frontrider.arcana.entity.ai.InhibitedAI


interface IInhibitor {

    var inhibited:Boolean
    var aiList:List<InhibitedAI>

}
