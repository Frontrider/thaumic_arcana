package hu.frontrider.arcana.creatureenchant.effect;

import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class ProtectionEnchant extends EffectEnchantBase {

    public ProtectionEnchant() {
        super(MobEffects.RESISTANCE,MobEffects.RESISTANCE,1,new ResourceLocation(MODID, "protection"),"protection");
    }

    @Override
    public AspectList formula() {
        return new AspectList()
                .merge(Aspect.PROTECT,100)
                .merge(Aspect.METAL,60)
                .merge(Aspect.AVERSION,10)
                .merge(Aspect.CRYSTAL,3)
                .merge(Aspect.EARTH,20)
                .merge(Aspect.MAGIC,70);
    }

    @Override
    public String getResearch() {
        return "CREATURE_ENCHANT";
    }
}
