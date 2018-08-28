package hu.frontrider.arcana.creatureenchant.backend;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.registries.IForgeRegistryEntry;
import thaumcraft.api.aspects.AspectList;

/**
 * Stores information about the large circle. Adds a generic effect to all enchantments.
 *
 * @author frontrider
 */
public abstract class EnchantingBaseCircle extends IForgeRegistryEntry.Impl<EnchantingBaseCircle> {

    final String unlocalizedName;

    protected EnchantingBaseCircle(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    public abstract Color getColor();

    /**
     * Allows the circle to act on the entity Only called when an effect would do something
     *
     * @param multiplier   the multiplier of the enchant
     * @param entityLiving the entity this circle is applied to
     * @param enchant      the enchant that we modify.
     */
    public abstract int doEffect(int multiplier, EntityLivingBase entityLiving, CreatureEnchant enchant);

    public abstract AspectList getFormula();

    public abstract String getResearch();

    public String getUnlocalizedName() {
        return "enchant.modifier."+unlocalizedName;
    }

    public static class Color {
        public Color(int r, int g, int b, int a) {

            this.r = r / 255;
            this.g = g / 255;
            this.b = b / 255;
            this.a = a / 255;
        }

        float r;
        float g;
        float b;
        float a;

        public float getR() {
            return r;
        }

        public float getG() {
            return g;
        }

        public float getB() {
            return b;
        }

        public float getA() {
            return a;
        }
    }
}
