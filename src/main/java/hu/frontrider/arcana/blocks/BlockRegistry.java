package hu.frontrider.arcana.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Objects;

@SuppressWarnings("unchecked")
@Mod.EventBusSubscriber
public class BlockRegistry {

    public static Block experiment_table = new ExperimentTable();

    @SubscribeEvent
    static void init(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(experiment_table);

        GameRegistry.registerTileEntity(((BlockTileEntity)experiment_table).getTileEntityClass(), Objects.requireNonNull(experiment_table.getRegistryName()));
    }

}
