package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.content.blocks.BlockTileEntity
import hu.frontrider.arcana.content.blocks.effect.BlockArcaneStoneDisableEnchants
import hu.frontrider.arcana.content.blocks.effect.BlockArcaneStoneEnableEnchants
import hu.frontrider.arcana.content.blocks.effect.EssentiaMine
import hu.frontrider.arcana.content.blocks.experiments.ExperimentTable
import hu.frontrider.arcana.content.blocks.plants.magictree.MagicTreeSapling
import hu.frontrider.arcana.content.blocks.plants.taintwine.mushroom.TaintWineCap
import hu.frontrider.arcana.content.blocks.plants.taintwine.mushroom.TaintWineFlower
import hu.frontrider.arcana.content.blocks.plants.taintwine.mushroom.TaintWineTrunk
import hu.frontrider.arcana.content.worldgen.generators.magictree.MagicTreeGenerator
import hu.frontrider.arcana.util.BlockFactory
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
    val essentiaMine = EssentiaMine()
    init {
        experimentTable = ExperimentTable()
    }

    @SubscribeEvent
    fun init(event: RegistryEvent.Register<Block>) {
        val magic_oak = BlockFactory
                .BlockFactoryHelper(MagicTreeSapling(MagicTreeGenerator(BlocksTC.logGreatwood.defaultState, oakLeaves, true)))
                .setResourourceLocation("magic_oak_sapling")
                .build()
        val silver_oak = BlockFactory
                .BlockFactoryHelper(MagicTreeSapling(MagicTreeGenerator(BlocksTC.logSilverwood.defaultState, oakLeaves, true)))
                .setResourourceLocation("silver_oak_sapling")
                .build()

        val taint_oak = BlockFactory
                .BlockFactoryHelper(MagicTreeSapling(MagicTreeGenerator(BlocksTC.taintLog.defaultState, oakLeaves, true)))
                .setResourourceLocation("tainted_oak_sapling")
                .build()

        val enableEnchants= BlockArcaneStoneEnableEnchants()
        val disableEnchants= BlockArcaneStoneDisableEnchants()
        ItemRegistry.blocks.addAll(arrayOf(magic_oak,silver_oak,taint_oak,enableEnchants,disableEnchants))

        event.registry.registerAll(
                //experimentTable,
                TaintWineCap(),
                TaintWineTrunk(),
                magic_oak,
                silver_oak,
                taint_oak,
                enableEnchants,
                disableEnchants,
                essentiaMine
        )

        GameRegistry.registerTileEntity((essentiaMine as BlockTileEntity<*>).tileEntityClass, Objects.requireNonNull<ResourceLocation>(essentiaMine.registryName))
       // GameRegistry.registerTileEntity((experimentTable as BlockTileEntity<*>).tileEntityClass, Objects.requireNonNull<ResourceLocation>(experimentTable.registryName))
     }

    companion object {
        lateinit var experimentTable: ExperimentTable
    }
}
