package hu.frontrider.arcana.creatureenchant.effect;

import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class Speed extends EffectEnchantBase {

    public Speed() {
        super(MobEffects.SPEED,MobEffects.SLOWNESS,-1,new ResourceLocation(MODID, "speed"), "speed");
    }

    @Override
    public AspectList formula() {
        return new AspectList()
                .merge(Aspect.SENSES, 20)
                .merge(Aspect.ENERGY, 100)
                .merge(Aspect.MAGIC, 20);
    }

    @Override
    public String getResearch() {
        return "CREATURE_ENCHANT_ADVANCED";
    }
}
