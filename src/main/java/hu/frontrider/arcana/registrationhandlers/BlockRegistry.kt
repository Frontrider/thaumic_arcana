package hu.frontrider.arcana.registrationhandlers

import hu.frontrider.arcana.blocks.BlockTileEntity
import hu.frontrider.arcana.blocks.effect.BlockArcaneStoneDisableEnchants
import hu.frontrider.arcana.blocks.effect.BlockArcaneStoneEnableEnchants
import hu.frontrider.arcana.blocks.effect.EssentiaMine
import hu.frontrider.arcana.blocks.plants.magictree.AuraLeaves
import hu.frontrider.arcana.blocks.plants.magictree.AuraLog
import hu.frontrider.arcana.blocks.plants.magictree.MagicTreeSapling
import hu.frontrider.arcana.blocks.plants.taintwine.mushroom.TaintWineCap
import hu.frontrider.arcana.blocks.plants.taintwine.mushroom.TaintWineTrunk
import hu.frontrider.arcana.worldgen.generators.magictree.MagicTreeGenerator
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
    companion object {
    }

    @SubscribeEvent
    fun init(event: RegistryEvent.Register<Block>) {
        val auraLog = BlockFactory
                .start(AuraLog())
                .setResourourceLocation("aura_wood_log")
                .build()
        val auraLeaves = BlockFactory
                .start(AuraLeaves())
                .setResourourceLocation("aura_leaves")
                .build()
        val oakLeaves = Blocks.LEAVES.defaultState.withProperty<BlockPlanks.EnumType, BlockPlanks.EnumType>(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty<Boolean, Boolean>(BlockOldLeaf.CHECK_DECAY, java.lang.Boolean.valueOf(false));
        val essentiaMine = EssentiaMine()

        val magicOak = BlockFactory
                .BlockFactoryHelper(MagicTreeSapling(MagicTreeGenerator(BlocksTC.logGreatwood.defaultState, oakLeaves, true)))
                .setResourourceLocation("magic_oak_sapling")
                .build()

        val silverOak = BlockFactory
                .BlockFactoryHelper(MagicTreeSapling(MagicTreeGenerator(BlocksTC.logSilverwood.defaultState, oakLeaves, true)))
                .setResourourceLocation("silver_oak_sapling")
                .build()

        val taintOak = BlockFactory
                .BlockFactoryHelper(MagicTreeSapling(MagicTreeGenerator(BlocksTC.taintLog.defaultState, oakLeaves, true)))
                .setResourourceLocation("tainted_oak_sapling")
                .build()

        val auraWood = BlockFactory
                .BlockFactoryHelper(MagicTreeSapling(MagicTreeGenerator(auraLog.defaultState, auraLeaves.defaultState, true)))
                .setResourourceLocation("aura_wood_sapling")
                .build()

        val enableEnchants = BlockArcaneStoneEnableEnchants()
        val disableEnchants = BlockArcaneStoneDisableEnchants()

        ItemRegistry.blocks.addAll(arrayOf(auraLeaves, auraLog, auraWood, magicOak, silverOak, taintOak, enableEnchants, disableEnchants))

        event.registry.registerAll(
                auraLeaves, auraLog, auraWood,
                //experimentTable,
                TaintWineCap(),
                TaintWineTrunk(),
                magicOak,
                silverOak,
                taintOak,
                enableEnchants,
                disableEnchants,
                essentiaMine
        )

        GameRegistry.registerTileEntity((essentiaMine as BlockTileEntity<*>).tileEntityClass, Objects.requireNonNull<ResourceLocation>(essentiaMine.registryName))
        // GameRegistry.registerTileEntity((experimentTable as BlockTileEntity<*>).tileEntityClass, Objects.requireNonNull<ResourceLocation>(experimentTable.registryName))
    }

}