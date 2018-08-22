package hu.frontrider.arcana.registrationhandlers;

import hu.frontrider.arcana.blocks.BlockArcaneCage;
import hu.frontrider.arcana.blocks.BlockTileEntity;
import hu.frontrider.arcana.blocks.ExperimentTable;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Objects;

@SuppressWarnings("unchecked")
public class BlockRegistry {
    static ExperimentTable experimentTable;
    static BlockArcaneCage blockArcaneCage;

    public BlockRegistry() {
        experimentTable = new ExperimentTable();
        blockArcaneCage = new BlockArcaneCage();
    }

    @SubscribeEvent
    public void init(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(experimentTable,blockArcaneCage);
        GameRegistry.registerTileEntity(((BlockTileEntity)experimentTable).getTileEntityClass(), Objects.requireNonNull(experimentTable.getRegistryName()));
        GameRegistry.registerTileEntity(((BlockTileEntity)blockArcaneCage).getTileEntityClass(), Objects.requireNonNull(blockArcaneCage.getRegistryName()));
    }
}
