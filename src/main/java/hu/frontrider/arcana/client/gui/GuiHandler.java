package hu.frontrider.arcana.client.gui;

import hu.frontrider.arcana.blocks.tiles.TileEntityExperimentTable;
import hu.frontrider.arcana.containers.ContainerExperimentTableCage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    public static final int EXPERIMENT_TABLE_CAGE = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case EXPERIMENT_TABLE_CAGE:
                return new ContainerExperimentTableCage(player.inventory, (TileEntityExperimentTable)world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case EXPERIMENT_TABLE_CAGE:
                TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
                return new GuiExperimentTableCage((Container) getServerGuiElement(ID, player, world, x, y, z), player.inventory, (TileEntityExperimentTable) tileEntity);
            default:
                return null;
        }
    }
}
