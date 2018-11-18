package hu.frontrider.arcana.core.capabilities.scar

interface IScarred {
    val scarred:Boolean
    var requiredDamage:Int
    var currentDamage:Float
    var severity:Byte
    var requiresResearch:Boolean
}