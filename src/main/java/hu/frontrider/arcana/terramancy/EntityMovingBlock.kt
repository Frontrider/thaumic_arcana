package hu.frontrider.arcana.terramancy

import net.minecraft.block.BlockFalling
import net.minecraft.entity.Entity
import net.minecraft.entity.item.EntityFallingBlock
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class EntityMovingBlock(world: World,val targetPos: BlockPos) :EntityFallingBlock(world) {

    init{
        setNoGravity(true)
    }

    override fun onEntityUpdate() {
        super.onEntityUpdate()
        if(this.position == targetPos) {


        }
    }

    fun place()
    {
      /*      this.setDead()
        val containedBlock = this.block!!.block
        if (containedBlock is BlockFalling) {
                    (containedBlock as BlockFalling).onEndFalling(this.world, targetPos, this.fallTile, targetPos)
                }

                if (this.tileEntityData != null && containedBlock.hasTileEntity(this.fallTile)) {
                    val tileentity = this.world.getTileEntity(targetPos)
                    if (tileentity != null) {
                        val nbttagcompound = tileentity.writeToNBT(NBTTagCompound())
                        val var10 = this.tileEntityData.keySet.iterator()

                        while (var10.hasNext()) {
                            val s = var10.next() as String
                            val nbtbase = this.tileEntityData.getTag(s)
                            if ("x" != s && "y" != s && "z" != s) {
                                nbttagcompound.setTag(s, nbtbase.copy())
                            }
                        }

                        tileentity.readFromNBT(nbttagcompound)
                        tileentity.markDirty()
                    }
                }
            }*/
    }

}