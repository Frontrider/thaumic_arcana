package hu.frontrider.arcana.content.blocks.calculator

import hu.frontrider.arcana.content.blocks.BlockTileEntity
import hu.frontrider.arcana.content.blocks.calculator.tiles.TileTallyBox
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.world.World

class BlockTallyBox : BlockTileEntity<TileTallyBox>(Material.WOOD,"tally_box") {

    internal fun emitRedstone(){
            println("redstone")
    }

    override val tileEntityClass: Class<TileTallyBox>
        get() = TileTallyBox::class.java

    override fun createTileEntity(world: World, state: IBlockState): TileTallyBox? {
        return TileTallyBox(this)
    }

}