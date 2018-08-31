package hu.frontrider.arcana.blocks;

import hu.frontrider.arcana.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aura.AuraHelper;

import java.util.Random;

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
public class BlockTaintBase extends BlockBase {
    private final int pollutionOnBreak;

    public BlockTaintBase(Material materialIn, String name, int pollutionOnBreak) {
        super(materialIn, name);
        this.pollutionOnBreak = pollutionOnBreak;
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        
    }

    @Override
    public void dropXpOnBlockBreak(World worldIn, BlockPos pos, int amount) {
        final int i = worldIn.rand.nextInt(4);
        for (int j = 0; j < i; j++) {
            final EntityXPOrb entityXPOrb = new EntityXPOrb(worldIn);
            entityXPOrb.setPosition(pos.getX()+.5,pos.getY()+.5,pos.getZ()+.5);
            worldIn.spawnEntity(entityXPOrb);    
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
        AuraHelper.polluteAura(worldIn,pos,pollutionOnBreak,true);
        if(worldIn.isRemote)
            return;
        
        final ItemStack itemStack = ThaumcraftApiHelper.makeCrystal(Aspect.FLUX,worldIn.rand.nextInt(3));
        final EntityItem fluxDrop = new EntityItem(worldIn);
        fluxDrop.posX = pos.getX()+.5;
        fluxDrop.posY = pos.getY();
        fluxDrop.posZ = pos.getZ()+.5;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        if(worldIn.rand.nextBoolean()) {
            AuraHelper.drainVis(worldIn,pos,2,false);
        }
    }
}