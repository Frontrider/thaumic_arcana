package hu.frontrider.arcana.creatureenchant;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
}
