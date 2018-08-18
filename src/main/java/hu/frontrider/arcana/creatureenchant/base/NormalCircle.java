package hu.frontrider.arcana.creatureenchant.base;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import net.minecraft.entity.EntityLivingBase;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class NormalCircle extends EnchantingBaseCircle {

    private Color color;
    public NormalCircle(){
        setRegistryName(MODID,"normal");
        color = new Color(255,255,255,255);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int doEffect(int multiplier, EntityLivingBase entityLiving, CreatureEnchant enchant) {
        return multiplier;
    }

    @Override
    public AspectList getFormula() {
        return new AspectList();
    }
}
