package hu.frontrider.arcana.blocks.experiments

import hu.frontrider.arcana.blocks.BlockTileEntity
import hu.frontrider.arcana.blocks.experiments.tiles.TileEntityBirthChamber
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.world.World

class BlockBirthChamber :BlockTileEntity<TileEntityBirthChamber>(Material.WOOD,"birth_chamber"){
    override val tileEntityClass: Class<TileEntityBirthChamber>
        get() = TileEntityBirthChamber::class.java

    override fun createTileEntity(world: World, state: IBlockState): TileEntityBirthChamber? {
        return TileEntityBirthChamber()
    }
}