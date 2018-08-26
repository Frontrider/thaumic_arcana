package hu.frontrider.arcana.registrationhandlers;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.backend.EnchantingBaseCircle;
import hu.frontrider.arcana.creatureenchant.base.NegationCircle;
import hu.frontrider.arcana.creatureenchant.base.NormalCircle;
import hu.frontrider.arcana.creatureenchant.effect.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.RegistryBuilder;

import static hu.frontrider.arcana.ThaumicArcana.MODID;


@Mod.EventBusSubscriber
public class CreatureEnchantRegistry {

    @SubscribeEvent
    public static void register(RegistryEvent.NewRegistry newRegistry) {
        new RegistryBuilder<CreatureEnchant>()
                .setType(CreatureEnchant.class)
                .setIDRange(0, 100)
                .setName(new ResourceLocation(MODID,"creature_enchant"))
                .add((iForgeRegistryInternal, registryManager, i, enchant, v1) -> MinecraftForge.EVENT_BUS.register(enchant))
                .create();

        new RegistryBuilder<EnchantingBaseCircle>()
                .setType(EnchantingBaseCircle.class)
                .setIDRange(0, 100)
                .setName(new ResourceLocation(MODID,"enchanting_base_circle"))
                .create();
    }

    @SubscribeEvent
    public static void registryEventCreatureEnchant(RegistryEvent.Register<CreatureEnchant> event) {
        event.getRegistry().registerAll(
                new StrengthEnchant(),
                new FertileEnchant(),
                new ProtectionEnchant(),
                new RespirationEnchant(),
                new SpiderFingers(),
                new Speed(),
                new Vitality()
        );
    }
    @SubscribeEvent
    public static void registryEventEnchantingBaseCircle(RegistryEvent.Register<EnchantingBaseCircle> event) {
        event.getRegistry().registerAll(
                new NormalCircle(),
                new NegationCircle()
        );
    }

}
