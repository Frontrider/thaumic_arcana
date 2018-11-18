package hu.frontrider.arcana.content.blocks.effect.tiles

import hu.frontrider.arcana.content.AspectEffectMap
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.EntityLivingBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.potion.PotionEffect
import net.minecraft.tileentity.TileEntity
import thaumcraft.api.aspects.Aspect

class TileEssentiaMine() : TileEntity() {
    private var time = 0
    var aspect: Aspect = Aspect.BEAST

    constructor(time: Int) : this() {
        this.time = time
    }

    fun applyEffect(entity: EntityLivingBase) {
        entity.addPotionEffect(PotionEffect(AspectEffectMap.map[aspect]!!.potion,40,0))
        time -= 1
    }

    val isUsedUp:Boolean
            get() = time <=0

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setInteger("time", time)
        compound.setString("aspect", aspect.tag)
        return compound
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        if (compound.hasKey("time"))
            time = compound.getInteger("time")
        if (compound.hasKey("aspect"))
            aspect = Aspect.getAspect(compound.getString("aspect"))
    }

    fun setAspect(string: String) {
        aspect = Aspect.getAspect(string)
    }
}