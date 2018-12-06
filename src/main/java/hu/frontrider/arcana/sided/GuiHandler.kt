package hu.frontrider.arcana.sided

import hu.frontrider.arcana.blocks.experiments.tiles.TileEntityExperimentTable
import hu.frontrider.arcana.blocks.production.tile.TileArcaneSieve
import hu.frontrider.arcana.containers.ContainerArcaneSieve
import hu.frontrider.arcana.containers.ContainerExperimentTableCage
import hu.frontrider.arcana.sided.client.gui.GuiArcaneSieve
import hu.frontrider.arcana.sided.client.gui.GuiExperimentTable
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
            EXPERIMENT_TABLE_CAGE -> ContainerExperimentTableCage(player.inventory, (world.getTileEntity(BlockPos(x, y, z)) as TileEntityExperimentTable))
            ARCANE_SIEVE -> ContainerArcaneSieve(player.inventory, (world.getTileEntity(BlockPos(x, y, z)) as TileArcaneSieve))
            else -> null
        }
    }

    @SideOnly(Side.CLIENT)
    override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        return when (ID) {
            EXPERIMENT_TABLE_CAGE -> {
                val tileEntity = world.getTileEntity(BlockPos(x, y, z))
                GuiExperimentTable(getServerGuiElement(ID, player, world, x, y, z) as Container, player.inventory, tileEntity as TileEntityExperimentTable)
            }
            ARCANE_SIEVE -> {
                val tileEntity = world.getTileEntity(BlockPos(x, y, z))
                GuiArcaneSieve(getServerGuiElement(ID, player, world, x, y, z) as Container, player.inventory, tileEntity as TileArcaneSieve)
            }
            else -> null
        }
    }

    companion object GuiHolder {
        const val EXPERIMENT_TABLE_CAGE = 0
        const val ARCANE_SIEVE = 2
    }
}
