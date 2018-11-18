package hu.frontrider.arcana.registrationhandlers

import net.minecraft.init.Items
import net.minecraftforge.oredict.OreDictionary

class OreDictionaryRegistry {

    fun init(){
        initFlesh()
    }

    fun initFlesh(){
        arrayOf(Items.BEEF,Items.CHICKEN,Items.PORKCHOP,Items.FISH,Items.MUTTON).forEach {
            OreDictionary.registerOre("allFlesh",it)
        }
    }
}