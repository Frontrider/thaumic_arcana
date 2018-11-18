package hu.frontrider.thaumcraft.researchloader.advancement;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.research.ResearchEvent;

import java.util.Optional;

public class ResearchEventHandler {

    @SubscribeEvent
    public void handleEvent(ResearchEvent.Research event) {
        EntityPlayerMP player = (EntityPlayerMP) event.getPlayer();
        String researchKey = event.getResearchKey();
        if (researchKey.equals("PLANT_EXPERIMENTS")) {
            IPlayerKnowledge capability = player.getCapability(ThaumcraftCapabilities.KNOWLEDGE, null);
            int researchStage = capability.getResearchStage(researchKey);
            if (researchStage == 1) {
                Optional<ResearchAdvancement> found = Optional.empty();
                for (ResearchAdvancement adv : Triggers.TRIGGER_ARRAY) {
                    if (adv.getId().getResourcePath().equals("research_" + researchKey.toLowerCase() + "_" + researchStage)) {
                        found = Optional.of(adv);
                        break;
                    }
                }
                found.ifPresent(researchAdvancement -> researchAdvancement.trigger((EntityPlayerMP) event.getPlayer()));
            }
        }

    }
}
