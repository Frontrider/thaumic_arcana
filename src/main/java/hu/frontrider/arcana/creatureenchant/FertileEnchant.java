package hu.frontrider.arcana.creatureenchant;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static hu.frontrider.arcana.creatureenchant.backend.CEnchantment.FERTILE;

public class FertileEnchant extends CreatureEnchant<BabyEntitySpawnEvent> {

    public FertileEnchant() {
        super(BabyEntitySpawnEvent.class);
    }

    @Override
    @SubscribeEvent
    public void handleEvent(BabyEntitySpawnEvent event) {
        EntityAnimal parentA = (EntityAnimal) event.getParentA();
        EntityAnimal parentB = (EntityAnimal) event.getParentB();

        if (getEnchantLevel(parentA,FERTILE)>0) {
            createChild(parentA, parentB);
        }
        if (getEnchantLevel(parentB,FERTILE)>0) {
            createChild(parentB, parentA);
        }
    }

    @Override
    public ResourceLocation getIcon() {
        return null;
    }

    void createChild(EntityAnimal parentA, EntityAnimal parentB) {
        EntityAgeable child = parentA.createChild(parentB);
        child.setPosition(parentA.posX, parentA.posY, parentA.posZ);
        child.setGrowingAge(-24000);
        parentA.world.spawnEntity(child);
    }
}
