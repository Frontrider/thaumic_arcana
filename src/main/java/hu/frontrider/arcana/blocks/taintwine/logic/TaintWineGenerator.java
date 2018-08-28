package hu.frontrider.arcana.blocks.taintwine.logic;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.aura.AuraHelper;

import java.util.Random;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
public class TaintWineGenerator extends WorldGenAbstractTree {

    @GameRegistry.ObjectHolder(MODID + ":taint_wine_trunk")
    Block taintWineTrunk = null;

    public TaintWineGenerator() {
        super(false);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height;
        
        for (height = 0; height < 5; height++) {
            worldIn.setBlockState(position.up(height), taintWineTrunk.getDefaultState());
        }
        
        AuraHelper.polluteAura(worldIn,position,20,true);
        return true;
    }
}
