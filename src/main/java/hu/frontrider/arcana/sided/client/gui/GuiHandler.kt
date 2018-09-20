package hu.frontrider.arcana.sided.client.gui

import hu.frontrider.arcana.content.blocks.experiments.tiles.TileEntityExperimentTable
import hu.frontrider.arcana.content.containers.ContainerExperimentTableCage
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class GuiHandler : IGuiHandler {

    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        return when (ID) {
            EXPERIMENT_TABLE_CAGE -> ContainerExperimentTableCage(player.inventory, (world.getTileEntity(BlockPos(x, y, z)) as TileEntityExperimentTable?)!!)
            else -> null
        }
    }

    @SideOnly(Side.CLIENT)
    override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        return when (ID) {
            EXPERIMENT_TABLE_CAGE -> {
                val experimentTable = world.getTileEntity(BlockPos(x, y, z))
                GuiExperimentTableCage(getServerGuiElement(ID, player, world, x, y, z) as Container, player.inventory, experimentTable as TileEntityExperimentTable)
            }
            else -> null
        }
    }

    companion object {
        const val EXPERIMENT_TABLE_CAGE = 0
    }
}
