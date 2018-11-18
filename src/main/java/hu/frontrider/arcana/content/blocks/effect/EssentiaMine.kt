package hu.frontrider.arcana.content.blocks.effect

import hu.frontrider.arcana.content.blocks.BlockTileEntity
import hu.frontrider.arcana.content.blocks.effect.tiles.TileEssentiaMine
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class EssentiaMine : BlockTileEntity<TileEssentiaMine>(Material.CIRCUITS, "essentia_mine") {
    override val tileEntityClass: Class<TileEssentiaMine>
        get() = TileEssentiaMine::class.java

    override fun createTileEntity(world: World, state: IBlockState): TileEssentiaMine? {
        return TileEssentiaMine(1000)
    }

    override fun onEntityWalk(worldIn: World, pos: BlockPos, entityIn: Entity) {
        val tileEntity = worldIn.getTileEntity(pos)
        if (tileEntity != null) {
            if (entityIn is EntityLivingBase)
                (tileEntity as TileEssentiaMine).apply {
                    applyEffect(entityIn)
                    if (isUsedUp)
                        worldIn.setBlockToAir(pos)
                }

        } else {
            worldIn.setBlockToAir(pos)
        }
    }

    override fun onEntityCollidedWithBlock(worldIn: World, pos: BlockPos, state: IBlockState, entityIn: Entity) {
        onEntityWalk(worldIn, pos, entityIn)
    }

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return aabb
    }

    override fun isCollidable(): Boolean {
        return true
    }

    override fun isTopSolid(state: IBlockState): Boolean {
        return false
    }

    override fun isFullBlock(state: IBlockState): Boolean {
        return  false
    }

    override fun isOpaqueCube(state: IBlockState): Boolean {
        return false
    }

    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }


    companion object {
        val aabb = AxisAlignedBB(0.0,0.0,0.0,1.0,1/16.0,1.0)
    }
}