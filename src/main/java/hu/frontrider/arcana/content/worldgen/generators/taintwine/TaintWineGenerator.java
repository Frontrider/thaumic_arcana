package hu.frontrider.arcana.content.worldgen.generators.taintwine;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.common.blocks.world.taint.TaintHelper;

import java.util.Random;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
public class TaintWineGenerator extends WorldGenAbstractTree {

    @GameRegistry.ObjectHolder(MODID + ":taint_wine_trunk")
    static Block taintWineTrunk = null;

    @GameRegistry.ObjectHolder(MODID + ":taint_wine_cap")
    static Block taintWineCap = null;

    public TaintWineGenerator() {
        super(false);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height;

        for (height = 0; height < 5; height++) {
            worldIn.setBlockState(position.up(height), taintWineTrunk.getDefaultState());
        }
        int capsize = 3;
        BlockPos capTemp = position.west(capsize).north(capsize).up(5);

        for (int eastSize = 0; eastSize < capsize * 2 + 1; eastSize++) {
            for (int southSize = 0; southSize < capsize * 2 + 1; southSize++) {
                if (worldIn.rand.nextInt(10) > 2) {
                    worldIn.setBlockState(capTemp, taintWineCap.getDefaultState());
                }
                capTemp = capTemp.south();
            }
            capTemp = capTemp.east().north(capsize * 2 + 1);
        }
        capTemp = position.west(capsize).north(capsize).up(4);
        for (int eastSize = 0; eastSize < capsize * 2 + 1; eastSize++) {
            for (int southSize = 0; southSize < capsize * 2 + 1; southSize++) {
                if (worldIn.rand.nextInt(10) > 5) {
                    if (worldIn.isAirBlock(position)) {
                        worldIn.setBlockState(capTemp, BlocksTC.taintFibre
                                .getDefaultState());
                    }
                }
                capTemp = capTemp.south();
            }
            capTemp = capTemp.east().north(capsize * 2 + 1);
        }
        capTemp = position.west(capsize).north(capsize).down();
        for (int eastSize = 0; eastSize < capsize * 2 + 1; eastSize++) {
            for (int southSize = 0; southSize < capsize * 2 + 1; southSize++) {
                if (worldIn.rand.nextInt(10) > 2) {
                    final IBlockState blockState = worldIn.getBlockState(position);
                    if (!blockState.getBlock().isPassable(worldIn, position))
                        worldIn.setBlockState(capTemp, BlocksTC.taintSoil.getDefaultState());
                }
                capTemp = capTemp.south();
            }
            capTemp = capTemp.east().north(capsize * 2 + 1);
        }
        AuraHelper.polluteAura(worldIn, position, 20, true);
        TaintHelper.spreadFibres(worldIn,position);
        
        return true;
    }
}
