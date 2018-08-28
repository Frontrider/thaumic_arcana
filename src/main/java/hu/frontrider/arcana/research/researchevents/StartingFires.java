package hu.frontrider.arcana.research.researchevents;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.research.ResearchEvent;

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
public class StartingFires {
    
    @SubscribeEvent
    public void fires(ResearchEvent.Research event) {
        final EntityPlayer player = event.getPlayer();
        final String researchKey = event.getResearchKey();
        if (researchKey.equals("ESSENTIASMELTER")) {
            final IPlayerKnowledge capability = player.getCapability(ThaumcraftCapabilities.KNOWLEDGE, null);
            final int researchStage = capability.getResearchStage(researchKey);
            if (researchStage == 2) {
                spawnFires(player.getEntityWorld(), player.getPosition());
            }
        }
    }

    void spawnFires(World world, BlockPos pos) {
        BlockPos shiftedPos = pos.east().north();
        for (int west = 0; west < 3; west++) {
            for (int south = 0; south < 3; south++) {
                if (world.isAirBlock(shiftedPos) && world.rand.nextInt(10)>6) {
                    world.setBlockState(shiftedPos, Blocks.FIRE.getDefaultState());
                }
                if (world.isAirBlock(shiftedPos.up()) && world.rand.nextInt(10)>6) {
                    world.setBlockState(shiftedPos.up(), Blocks.FIRE.getDefaultState());
                }
                shiftedPos = shiftedPos.west().south();
            }
        }

    }
}
