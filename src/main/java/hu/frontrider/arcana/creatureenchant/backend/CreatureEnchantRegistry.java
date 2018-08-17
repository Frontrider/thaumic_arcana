package hu.frontrider.arcana.creatureenchant.backend;

import hu.frontrider.arcana.creatureenchant.FertileEnchant;
import hu.frontrider.arcana.creatureenchant.ProtectionEnchant;
import hu.frontrider.arcana.creatureenchant.RespirationEnchant;
import hu.frontrider.arcana.creatureenchant.StrengthEnchant;
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
    }

    @SubscribeEvent
    public static void registryEvent(RegistryEvent.Register<CreatureEnchant> event) {
        event.getRegistry().registerAll(
                new StrengthEnchant(),
                new FertileEnchant(),
                new ProtectionEnchant(),
                new RespirationEnchant()
        );
    }

}
