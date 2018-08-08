package hu.frontrider.arcana.creatureenchant;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.creatureenchant.backend.CEnchantment.RESPIRATION;

public class RespirationEnchant extends CreatureEnchant<LivingEvent.LivingUpdateEvent> {
    public RespirationEnchant() {
        super(LivingEvent.LivingUpdateEvent.class);
    }

    @SubscribeEvent
    public void handleEvent(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
            if (entity.isInWater()) {
                int enchantLevel = getEnchantLevel(entity, RESPIRATION);
                if (enchantLevel >0) {
                    entity.setAir(300);
                }
            }
    }

    @Override
    public ResourceLocation getIcon() {
        return null;
    }

    @Override
    public AspectList formula() {
        return new AspectList()
                .merge(Aspect.AIR,200)
                .merge(Aspect.WATER,50)
                .merge(Aspect.LIFE,5)
                .merge(Aspect.ORDER,10)
                .merge(Aspect.SENSES,20)
                .merge(Aspect.MAGIC,80);
    }
}
