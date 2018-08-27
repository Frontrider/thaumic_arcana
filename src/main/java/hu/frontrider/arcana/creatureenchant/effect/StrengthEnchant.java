package hu.frontrider.arcana.creatureenchant.effect;

import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class StrengthEnchant extends EffectEnchantBase {

    public StrengthEnchant() {
        super(MobEffects.STRENGTH,
                MobEffects.WEAKNESS,
                -1, new ResourceLocation(MODID, "strength"),
                "strength");
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
