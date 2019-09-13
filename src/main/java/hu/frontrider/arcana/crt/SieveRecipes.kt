package hu.frontrider.arcana.crt

import crafttweaker.annotations.ZenRegister
import crafttweaker.api.item.IIngredient
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenMethod

@ZenRegister
@ZenClass("mods.thaumicarcana.Sieve")
class ArcaneSieveRecipes {

    @ZenMethod
    fun register(input1: IIngredient){
        val items = input1.items
        items[0].name
    }
}