package hu.frontrider.arcana.registrationhandlers;

import hu.frontrider.arcana.blocks.BlockTileEntity;
import hu.frontrider.arcana.blocks.experiments.BlockArcaneCage;
import hu.frontrider.arcana.blocks.experiments.ExperimentTable;
import hu.frontrider.arcana.blocks.taintwine.mushroom.TaintWineCap;
import hu.frontrider.arcana.blocks.taintwine.mushroom.TaintWineFlower;
import hu.frontrider.arcana.blocks.taintwine.mushroom.TaintWineTrunk;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Objects;

@SuppressWarnings("unchecked")
public class BlockRegistry {
    public static ExperimentTable experimentTable;
    public static BlockArcaneCage blockArcaneCage;

    public BlockRegistry() {
        experimentTable = new ExperimentTable();
        blockArcaneCage = new BlockArcaneCage();
    }

    @SubscribeEvent
    public void init(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(experimentTable,blockArcaneCage,
                new TaintWineCap(),new TaintWineFlower(),new TaintWineTrunk()
        );
        GameRegistry.registerTileEntity(((BlockTileEntity)experimentTable).getTileEntityClass(), Objects.requireNonNull(experimentTable.getRegistryName()));
        GameRegistry.registerTileEntity(((BlockTileEntity)blockArcaneCage).getTileEntityClass(), Objects.requireNonNull(blockArcaneCage.getRegistryName()));
    }
}
