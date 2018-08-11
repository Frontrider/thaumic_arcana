package hu.frontrider.arcana.creatureenchant;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.creatureenchant.backend.CEnchantment.FERTILE;

public class FertileEnchant extends CreatureEnchant {

    public FertileEnchant() {
        super(new ResourceLocation(MODID, "textures/enchant/fertile.png"));
    }

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
    public AspectList formula() {
        return new AspectList()
                .merge(Aspect.LIFE,20)
                .merge(Aspect.DESIRE,30)
                .merge(Aspect.FIRE,5)
                .merge(Aspect.ORDER,3)
                .merge(Aspect.EXCHANGE,20)
                .merge(Aspect.MAGIC,50);
    }

    void createChild(EntityAnimal parentA, EntityAnimal parentB) {
        EntityAgeable child = parentA.createChild(parentB);
        child.setPosition(parentA.posX, parentA.posY, parentA.posZ);
        child.setGrowingAge(-24000);
        parentA.world.spawnEntity(child);
    }
}
