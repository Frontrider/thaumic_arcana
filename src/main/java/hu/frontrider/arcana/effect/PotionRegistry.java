package hu.frontrider.arcana.effect;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class PotionRegistry {


    @SubscribeEvent
    public static void register(RegistryEvent.Register<PotionType> event) {
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {

    }

    public static void registerRecipes() {
    }
}
