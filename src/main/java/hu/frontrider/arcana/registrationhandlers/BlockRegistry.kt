package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.blocks.BlockTileEntity
import hu.frontrider.arcana.blocks.experiments.BlockArcaneCage
import hu.frontrider.arcana.blocks.experiments.ExperimentTable
import hu.frontrider.arcana.blocks.taintwine.mushroom.TaintWineCap
import hu.frontrider.arcana.blocks.taintwine.mushroom.TaintWineFlower
import hu.frontrider.arcana.blocks.taintwine.mushroom.TaintWineTrunk
import net.minecraft.block.Block
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import java.util.*

class BlockRegistry {
    init {
        experimentTable = ExperimentTable()
        blockArcaneCage = BlockArcaneCage()
    }

    @SubscribeEvent
    fun init(event: RegistryEvent.Register<Block>) {
        event.registry.registerAll(experimentTable, blockArcaneCage,
                TaintWineCap(), TaintWineFlower(), TaintWineTrunk()
        )
        GameRegistry.registerTileEntity((experimentTable as BlockTileEntity<*>).tileEntityClass, Objects.requireNonNull<ResourceLocation>(experimentTable.registryName))
        GameRegistry.registerTileEntity((blockArcaneCage as BlockTileEntity<*>).tileEntityClass, Objects.requireNonNull<ResourceLocation>(blockArcaneCage.registryName))
    }

    companion object {
        lateinit var experimentTable: ExperimentTable
        lateinit var blockArcaneCage: BlockArcaneCage
    }
}
