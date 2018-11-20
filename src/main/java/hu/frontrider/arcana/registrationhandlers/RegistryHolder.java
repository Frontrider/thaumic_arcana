package hu.frontrider.arcana.registrationhandlers;

import hu.frontrider.arcana.capabilities.implants.IImplant;
import hu.frontrider.arcana.creatureenchant.CreatureEnchant;
import hu.frontrider.arcana.creatureenchant.EnchantingBaseCircle;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.RegistryBuilder;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

@Mod.EventBusSubscriber(modid=MODID)
public class RegistryHolder {

    @SubscribeEvent
    static void register(RegistryEvent.NewRegistry event) {
        new RegistryBuilder<CreatureEnchant>()
                .setType(CreatureEnchant.class)
                .setIDRange(0, 100)
                .setName(new ResourceLocation(MODID, "creature_enchant"))
                .add((owner, stage, id, obj, oldObj) -> MinecraftForge.EVENT_BUS.register(obj))
                .create();

        new RegistryBuilder<EnchantingBaseCircle>()
                .setType(EnchantingBaseCircle.class)
                .setIDRange(0, 100)
                .setName(new ResourceLocation(MODID, "enchanting_base_circle"))
                .create();
        new RegistryBuilder<IImplant>()
                .setType(IImplant.class)
                .setIDRange(0,100)
                .setName(new ResourceLocation(MODID, "implant"))
                .add((owner, stage, id, obj, oldObj) -> MinecraftForge.EVENT_BUS.register(obj))
                .create();
    }
}
