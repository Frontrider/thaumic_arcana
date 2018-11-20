package hu.frontrider.arcana.research.researchevents

import net.minecraft.init.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import thaumcraft.api.capabilities.ThaumcraftCapabilities
import thaumcraft.api.research.ResearchEvent

/**
 * @author Kis András Gábor
 * 2018.08.28.
 */
class StartingFires {

    @SubscribeEvent
    fun fires(event: ResearchEvent.Research) {
        val player = event.player
        val researchKey = event.researchKey
        if (researchKey == "ESSENTIASMELTER") {
            val capability = player.getCapability(ThaumcraftCapabilities.KNOWLEDGE, null)
            val researchStage = capability!!.getResearchStage(researchKey)
            if (researchStage == 2) {
                spawnFires(player.entityWorld, player.position)
            }
        }
    }

    internal fun spawnFires(world: World, pos: BlockPos) {
        var shiftedPos = pos.east().north()
        for (west in 0..2) {
            for (south in 0..2) {
                if (world.isAirBlock(shiftedPos) && world.rand.nextInt(10) > 6) {
                    world.setBlockState(shiftedPos, Blocks.FIRE.defaultState)
                }
                if (world.isAirBlock(shiftedPos.up()) && world.rand.nextInt(10) > 6) {
                    world.setBlockState(shiftedPos.up(), Blocks.FIRE.defaultState)
                }
                shiftedPos = shiftedPos.west().south()
            }
        }

    }
}
