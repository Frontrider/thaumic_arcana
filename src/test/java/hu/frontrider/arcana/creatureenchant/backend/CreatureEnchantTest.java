package hu.frontrider.arcana.creatureenchant.backend;

import hu.frontrider.arcana.creatureenchant.CreatureEnchant;
import net.minecraft.util.ResourceLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import thaumcraft.api.aspects.AspectList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatureEnchantTest {
    private EnchantBase enchantBase;


    @BeforeEach
    void setUp() {
        enchantBase = new EnchantBase();
    }


    @Test
    void getUnlocalizedName() {

        assertEquals("enchant.creature_enchant.unlocalised_name", enchantBase.getUnlocalizedName());
    }

    @Test
    void getEnchantLevel() {
    }

    @Test
    void setEnchantment() {
    }

    @Test
    void isEnchanted() {
    }

    @Test
    void getCreatureEnchants() {
    }

    @Test
    void getCreatureEnchants1() {
    }

    @Test
    void getIcon() {
        ResourceLocation icon = enchantBase.getRegistryName();
        assertEquals("mod",icon.getResourceDomain());
        assertEquals("id",icon.getResourcePath());
    }
    /**
     * Exists, so I can test the abstract class.
     *
     * */
    class EnchantBase extends CreatureEnchant {

        public EnchantBase() {
            super(new ResourceLocation("mod", "id"), "unlocalised_name");
        }

        @Override
        public AspectList formula() {
            return null;
        }

        @Override
        public String getResearch() {
            return null;
        }
    }
}