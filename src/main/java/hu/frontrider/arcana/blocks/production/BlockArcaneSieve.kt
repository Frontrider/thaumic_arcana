package hu.frontrider.arcana.blocks.production

import hu.frontrider.arcana.ThaumicArcana
import hu.frontrider.arcana.blocks.BlockTileEntity
import hu.frontrider.arcana.blocks.production.tile.TileArcaneSieve
import hu.frontrider.arcana.sided.GuiHandler
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class BlockArcaneSieve : BlockTileEntity<TileArcaneSieve>(Material.WOOD, "arcane_sieve") {
    override val tileEntityClass: Class<TileArcaneSieve> = TileArcaneSieve::class.java

    override fun createTileEntity(world: World, state: IBlockState): TileArcaneSieve? {
        return TileArcaneSieve()
    }

    override fun onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        playerIn.openGui(ThaumicArcana, GuiHandler.ARCANE_SIEVE, worldIn, pos.x, pos.y, pos.z)
        return true
    }

    override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack)
        worldIn.scheduleUpdate(pos,this,10)
    }

    override fun updateTick(worldIn: World, pos: BlockPos, state: IBlockState, rand: Random) {
        super.updateTick(worldIn, pos, state, rand)
        val arcaneSieve = worldIn.getTileEntity(pos) as TileArcaneSieve
        arcaneSieve.update(worldIn,pos)
        worldIn.scheduleUpdate(pos,this,10)
    }

    override fun requiresUpdates(): Boolean {
        return true
    }

    override fun breakBlock(worldIn: World, pos: BlockPos, state: IBlockState) {
        val tileEntity = worldIn.getTileEntity(pos)!! as TileArcaneSieve
        tileEntity.getStoredItems().forEach {
            worldIn.spawnEntity(EntityItem(worldIn, pos.x + .5, pos.y + .5, pos.z + .5, it))
        }
    }

    override fun getRenderType(state: IBlockState?): EnumBlockRenderType {
        return EnumBlockRenderType.MODEL
    }

    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    override fun isFullCube(state: IBlockState?): Boolean {
        return false
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    override fun isOpaqueCube(state: IBlockState?): Boolean {
        return false
    }

}