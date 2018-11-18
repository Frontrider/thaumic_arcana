package hu.frontrider.arcana.content.blocks

import hu.frontrider.gearcraft.core.util.data.toBlockPos
import hu.frontrider.gearcraft.core.util.data.toNBT
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockPlaceHolder(material: Material, name:String) :BlockTileEntity<PlaceHolderTile>(material, name){

    override val tileEntityClass: Class<PlaceHolderTile>
        get() = PlaceHolderTile::class.java

    override fun createTileEntity(world: World, state: IBlockState): PlaceHolderTile? {
        return PlaceHolderTile()
    }
    fun setPos(world:World, pos:BlockPos, targetPos: BlockPos){
        (world.getTileEntity(pos) as PlaceHolderTile).targePos = targetPos
    }

    override fun isOpaqueCube(state: IBlockState): Boolean {
        return false
    }

    override fun isFullBlock(state: IBlockState): Boolean {
        return false
    }

    override fun onBlockHarvested(worldIn: World, pos: BlockPos, state: IBlockState, player: EntityPlayer) {
        val targePos = (worldIn.getTileEntity(pos) as PlaceHolderTile).targePos
        val blockState = worldIn.getBlockState(targePos)
        (blockState.block as? BreakNotifier)?.notify(worldIn,targePos)
    }
}

class PlaceHolderTile(var targePos:BlockPos = BlockPos(0,0,0)):TileEntity(){
    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setTag("pos",targePos.toNBT())
        return compound
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        targePos = (compound.getTag("pos") as NBTTagCompound).toBlockPos()
    }
}

interface BreakNotifier{
    fun notify(world:World,pos:BlockPos)
}