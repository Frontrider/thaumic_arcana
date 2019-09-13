package hu.frontrider.arcana.blocks.experiments

import hu.frontrider.arcana.blocks.BlockTileEntity
import hu.frontrider.arcana.blocks.experiments.tiles.TileEntityBirthChamber
import hu.frontrider.arcana.blocks.experiments.tiles.TileEntitySoulManipulator
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.world.World

class BlockSoulManipulator: BlockTileEntity<TileEntitySoulManipulator>(Material.WOOD,"birth_chamber") {
    override val tileEntityClass: Class<TileEntitySoulManipulator>
        get() = TileEntitySoulManipulator::class.java

    override fun createTileEntity(world: World, state: IBlockState): TileEntitySoulManipulator {
        return TileEntitySoulManipulator()
    }
}