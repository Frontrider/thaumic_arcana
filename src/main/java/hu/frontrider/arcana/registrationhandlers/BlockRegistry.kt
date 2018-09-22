package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.content.blocks.BlockTileEntity
import hu.frontrider.arcana.content.blocks.experiments.ExperimentTable
import hu.frontrider.arcana.content.blocks.plants.magictree.MagicTreeSapling
import hu.frontrider.arcana.content.blocks.plants.taintwine.mushroom.TaintWineCap
import hu.frontrider.arcana.content.blocks.plants.taintwine.mushroom.TaintWineFlower
import hu.frontrider.arcana.content.blocks.plants.taintwine.mushroom.TaintWineTrunk
import hu.frontrider.arcana.content.worldgen.generators.magictree.MagicTreeGenerator
import hu.frontrider.arcana.core.BlockFactory
import net.minecraft.block.Block
import net.minecraft.block.BlockOldLeaf
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import thaumcraft.api.blocks.BlocksTC
import java.util.*

class BlockRegistry {

    val oakLeaves= Blocks.LEAVES.defaultState.withProperty<BlockPlanks.EnumType, BlockPlanks.EnumType>(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty<Boolean, Boolean>(BlockOldLeaf.CHECK_DECAY, java.lang.Boolean.valueOf(false));

    init {
        experimentTable = ExperimentTable()
    }

    @SubscribeEvent
    fun init(event: RegistryEvent.Register<Block>) {
        val magc_oak = BlockFactory
                .BlockFactoryHelper(MagicTreeSapling(MagicTreeGenerator(BlocksTC.logGreatwood.defaultState, oakLeaves, true)))
                .setResourourceLocation("magic_oak_sapling")
                .build()

        ItemRegistry.blocks.add(magc_oak)
        event.registry.registerAll(experimentTable,
                TaintWineCap(),
                TaintWineFlower(),
                TaintWineTrunk(),
                magc_oak
        )
        GameRegistry.registerTileEntity((experimentTable as BlockTileEntity<*>).tileEntityClass, Objects.requireNonNull<ResourceLocation>(experimentTable.registryName))
    }

    companion object {
        lateinit var experimentTable: ExperimentTable
    }
}
