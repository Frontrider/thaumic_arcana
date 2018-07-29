package hu.frontrider.arcana.effect;

import hu.frontrider.arcana.items.ItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.common.lib.potions.PotionWarpWard;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

@Mod.EventBusSubscriber
public class PotionRegistry {

    private static PotionType relief;

    @SubscribeEvent
    public static void register(RegistryEvent.Register<PotionType> event) {

        SweetRelief.instance = new SweetRelief();
        PotionEffect reliefEffect = new PotionEffect(SweetRelief.instance, 3600, 1, false, false);
        reliefEffect.addCurativeItem(new ItemStack(Items.GOLDEN_CARROT));

        PotionEffect wardingEffect = new PotionEffect(PotionWarpWard.instance, 3600, 0, false, false);
        relief = new PotionType(reliefEffect, wardingEffect);
        relief.setRegistryName(MODID, "sweet_relief");

        event.getRegistry().register(relief);
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {
        SweetRelief.instance = new SweetRelief();
        event.getRegistry().register(SweetRelief.instance);
    }

    public static void registerRecipes() {
        PotionHelper.addMix(PotionTypes.AWKWARD, ItemRegistry.relief_item, relief);
    }
}
