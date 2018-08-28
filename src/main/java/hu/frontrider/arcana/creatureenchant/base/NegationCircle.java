package hu.frontrider.arcana.creatureenchant.base;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import net.minecraft.entity.EntityLivingBase;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class NegationCircle extends EnchantingBaseCircle {

    private Color color;

    public NegationCircle() {
        super("negation");
        setRegistryName(MODID, "negation");
        color = new Color(255,0,0,255);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int doEffect(int multiplier, EntityLivingBase entityLiving, CreatureEnchant enchant) {
        return multiplier * -1;
    }

    @Override
    public AspectList getFormula() {
        return new AspectList()
                .merge(Aspect.BEAST,300)
                .merge(Aspect.FLUX,100)
                .merge(Aspect.LIFE,50)
                .merge(Aspect.ENTROPY,100);
    }

    @Override
    public String getResearch() {
        return "ENCHANT_MODIFICATION";
    }
}
