package hu.frontrider.arcana.creatureenchant.effect;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public abstract class EffectEnchantBase extends CreatureEnchant {

    private final Potion positive;
    private final Potion negative;
    private final double negativeMultiplier;

    public EffectEnchantBase(Potion positive, Potion negative, double negativeMultiplier, ResourceLocation resourceLocation, String unlocalisedName) {
        super(resourceLocation,unlocalisedName);
        this.positive = positive;
        this.negative = negative;
        this.negativeMultiplier = negativeMultiplier;
    }

    @SubscribeEvent
    public void tick(LivingEvent.LivingUpdateEvent event) {

        EntityLivingBase entity = event.getEntityLiving();
        int enchantLevel = getEnchantLevel(entity, this);

        if (enchantLevel > 0) {
            if(entity.getActivePotionEffect(positive) == null || entity.getActivePotionEffect(positive).getDuration() <30)
            entity.addPotionEffect(new PotionEffect(positive,240,enchantLevel-1,true,false));
        }

        if (enchantLevel < 0) {
            if(entity.getActivePotionEffect(negative) == null || entity.getActivePotionEffect(negative).getDuration() <30)
            entity.addPotionEffect(new PotionEffect(negative,240, (int) ((enchantLevel+1)*negativeMultiplier),true,false));
        }
    }

}
