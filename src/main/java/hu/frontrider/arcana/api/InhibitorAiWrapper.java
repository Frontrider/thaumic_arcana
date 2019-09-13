package hu.frontrider.arcana.api;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.ResourceLocation;

public interface InhibitorAiWrapper {
    ResourceLocation getID();
    EntityAIBase get(EntityCreature entityCreature);
    default int getPriority() {return 1;}
}