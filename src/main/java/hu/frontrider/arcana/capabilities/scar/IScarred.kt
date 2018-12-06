package hu.frontrider.arcana.capabilities.scar

interface IScarred {
    val scarred:Boolean
    var requiredDamage:Int
    var currentDamage:Float
    var severity:Byte
    var limbs:Limbs
}