package hu.frontrider.arcana.creatureenchant.backend;

import hu.frontrider.arcana.capabilities.CreatureEnchantCapability;
import hu.frontrider.arcana.capabilities.ICreatureEnchant;
import hu.frontrider.arcana.util.AspectUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import thaumcraft.api.aspects.AspectList;

import java.util.Collections;
import java.util.Map;

import static hu.frontrider.arcana.ThaumicArcana.MODID;
import static hu.frontrider.arcana.capabilities.CreatureEnchantProvider.CREATURE_ENCHANT_CAPABILITY;

public abstract class CreatureEnchant extends IForgeRegistryEntry.Impl<CreatureEnchant> {

    @GameRegistry.ObjectHolder(MODID +":normal")
    private static EnchantingBaseCircle baseCircle = null;

    private final ResourceLocation icon;
    private final String unlocalizedName;

    public CreatureEnchant(ResourceLocation resourceLocation, String unlocalizedName) {
        setRegistryName(resourceLocation);
        this.unlocalizedName = unlocalizedName;
        icon = new ResourceLocation(resourceLocation.getResourceDomain(), "textures/cenchant/" + resourceLocation.getResourcePath() + ".png");
    }

    public String getUnlocalizedName() {
        return "enchant.creature_enchant." + unlocalizedName;
    }

    public int getEnchantLevel(EntityLivingBase entity, CreatureEnchant enchantment) {
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
            int level = capability.getLevel(enchantment);
            if (capability.hasEnchant(enchantment))
            return capability.getCircle().doEffect(level,entity,enchantment);

        }
        return 0;
    }

    public static void setEnchantment(Entity entity,ICreatureEnchant enchants) {
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);

            capability.setStore(enchants.getStore());
            capability.setCircle(enchants.getCircle());
        }
    }

    public static boolean isEnchanted(Entity entity) {
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
            return capability.hasEnchant();
        }
        return false;
    }

    public static Map<CreatureEnchant, CreatureEnchantCapability.CreatureEnchantContainer> getCreatureEnchants(Entity entity) {
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
            return capability.getStore();
        }

        return Collections.emptyMap();
    }

    public static EnchantingBaseCircle getBaseCircle(Entity entity){
        if (entity.hasCapability(CREATURE_ENCHANT_CAPABILITY, null)) {
            ICreatureEnchant capability = entity.getCapability(CREATURE_ENCHANT_CAPABILITY, null);
            return capability.getCircle();
        }

        return baseCircle;
    }


    public static CreatureEnchant getForFormula(AspectList formula){
        for (CreatureEnchant enchant : GameRegistry.findRegistry(CreatureEnchant.class).getValuesCollection()) {
            if (AspectUtil.INSTANCE.aspectListEquals(enchant.formula(), formula))
                return enchant;
        }
        return null;
    }

    public abstract AspectList formula();

    public abstract String getResearch();


    @Override
    public String toString() {
        return "CreatureEnchant{" +
                ", registryName=" + getRegistryName() +
                ", research='" + getResearch() + '\'' +
                '}';
    }

    public ResourceLocation getIcon() {
        return icon;
    }
}
