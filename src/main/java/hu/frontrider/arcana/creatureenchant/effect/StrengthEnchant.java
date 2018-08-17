package hu.frontrider.arcana.creatureenchant.effect;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class StrengthEnchant extends CreatureEnchant {

    public StrengthEnchant() {
        super(new ResourceLocation(MODID, "strength"), "strength");
    }

    @SubscribeEvent
    public void handleEvent(LivingHurtEvent event) {

        DamageSource source = event.getSource();
        if (source.getTrueSource() != null) {
            Entity trueSource = event.getSource().getTrueSource();
            if (trueSource instanceof EntityLivingBase) {
                int enchantLevel = getEnchantLevel((EntityLivingBase) trueSource, this);
                if (enchantLevel > 0) {
                    event.setAmount(event.getAmount() + enchantLevel);
                }
            }
        }
    }

    @Override
    public AspectList formula() {
        return new AspectList()
                .merge(Aspect.LIFE, 20)
                .merge(Aspect.ENERGY, 200)
                .merge(Aspect.FIRE, 50)
                .merge(Aspect.ORDER, 300)
                .merge(Aspect.MAGIC, 50);
    }

    @Override
    public String getResearch() {
        return "CREATURE_ENCHANT_ADVANCED";
    }
}
