package hu.frontrider.arcana.api;

import hu.frontrider.core.api.RegistryEvent;
import net.minecraft.entity.ai.EntityAIBase;

import java.util.List;

public class InhibitorAIRegistryEvent extends RegistryEvent<InhibitorAiWrapper> {
    public InhibitorAIRegistryEvent(List<InhibitorAiWrapper> wrappers) {
        super(wrappers);
    }
}
