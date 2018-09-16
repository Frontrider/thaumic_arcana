package hu.frontrider.arcana.util

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import thaumcraft.api.aspects.Aspect
import thaumcraft.api.aspects.AspectList
import java.util.*


object AspectUtil {

    val random: Aspect
        get() {
            val aspects = Aspect.aspects.values
            return aspects.toTypedArray()[Random().nextInt(aspects.size)] as Aspect
        }

    fun aspectEquals(aspect1: Aspect, aspect2: Aspect): Boolean {
        return aspect1.tag == aspect2.tag
    }

    fun aspectListEquals(aspect1: AspectList, aspect2: AspectList): Boolean {

        return if (aspect1.aspects.size != aspect2.aspects.size) false else aspect1.aspects.entries.stream().map result@{ aspectIntegerEntry ->
            val key = aspectIntegerEntry.key
            if (!aspect2.aspects.containsKey(key))
                return@result false

            val amount = aspect2.aspects[key]
            amount == aspectIntegerEntry.value

        }.reduce(true) { aBoolean, aBoolean2 -> aBoolean!! && aBoolean2!! }

    }

    fun getStoredAspects(itemStack: ItemStack): AspectList {
        val aspectList = AspectList()

        aspectList.readFromNBT(itemStack.tagCompound!!)

        return aspectList
    }

    fun hashAspectList(list: AspectList): Int {
        val nbtTagCompound = NBTTagCompound()
        list.writeToNBT(nbtTagCompound)
        return nbtTagCompound.hashCode()
    }
}
