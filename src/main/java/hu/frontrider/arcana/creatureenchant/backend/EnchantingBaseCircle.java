package hu.frontrider.arcana.creatureenchant.backend;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.registries.IForgeRegistryEntry;
import thaumcraft.api.aspects.AspectList;

/**
 * Stores information about the large circle. Adds a generic effect to all enchantments.
 * @author frontrider
 * */
public abstract class EnchantingBaseCircle extends IForgeRegistryEntry.Impl<EnchantingBaseCircle> {


    public abstract Color getColor();

    /**
     * Allows the circle to act on the entity Only called when an effect would do something
     *
     * @param multiplier the multiplier of the enchant
     * @param entityLiving the entity this circle is applied to
     * @param enchant the enchant that we modify.
     * */
    public abstract int doEffect(int multiplier, EntityLivingBase entityLiving, CreatureEnchant enchant);

    public abstract AspectList getFormula();

    public static class Color{
        public Color(int r,int g,int b,int a){

            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
        }
        int r;
        int g;
        int b;
        int a;

        public int getR() {
            return r;
        }

        public int getG() {
            return g;
        }

        public int getB() {
            return b;
        }

        public int getA() {
            return a;
        }
    }
}
