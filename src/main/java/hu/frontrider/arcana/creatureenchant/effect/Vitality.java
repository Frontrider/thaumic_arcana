package hu.frontrider.arcana.creatureenchant.effect;

import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class Vitality extends EffectEnchantBase {

    public Vitality() {
        super(MobEffects.REGENERATION,MobEffects.WITHER,-.5,new ResourceLocation(MODID, "vitality"), "vitality");
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
