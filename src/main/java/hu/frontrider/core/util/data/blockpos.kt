package hu.frontrider.core.util.data

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos

fun BlockPos.toNBT(): NBTTagCompound {
    val nbtTagCompound = NBTTagCompound()
    nbtTagCompound.setInteger("x",x)
    nbtTagCompound.setInteger("y",y)
    nbtTagCompound.setInteger("z",z)
    return nbtTagCompound
}

fun NBTTagCompound.toBlockPos(): BlockPos {
    return BlockPos(
            this.getInteger("x"),
            this.getInteger("y"),
            this.getInteger("z")
    )
}

fun BlockPos.inRange(blockPos: BlockPos,range:Int): Boolean {
    val distance = blockPos.getDistance(x, y, z)
    return distance <=range
}