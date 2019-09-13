package hu.frontrider.arcana.capabilities.inhibitor

import hu.frontrider.arcana.api.InhibitorAiWrapper


interface IInhibitor {

    var inhibited:Boolean
    var aiList:List<InhibitorAiWrapper>

}
