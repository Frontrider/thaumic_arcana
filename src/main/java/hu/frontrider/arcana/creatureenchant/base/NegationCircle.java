package hu.frontrider.arcana.creatureenchant.base;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import net.minecraft.entity.EntityLivingBase;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class NegationCircle extends EnchantingBaseCircle {

    private Color color;

    public NegationCircle() {
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
        return null;
    }
}
