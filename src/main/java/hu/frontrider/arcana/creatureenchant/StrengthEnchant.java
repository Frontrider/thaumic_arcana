package hu.frontrider.arcana.creatureenchant;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static hu.frontrider.arcana.creatureenchant.backend.CEnchantment.STRENGTH;

public class StrengthEnchant extends CreatureEnchant<LivingHurtEvent> {
    public StrengthEnchant() {
        super(LivingHurtEvent.class);
    }

    @Override
    @SubscribeEvent
    public void handleEvent(LivingHurtEvent event) {

        DamageSource source = event.getSource();
        if (source.getTrueSource() != null) {
            int enchantLevel = getEnchantLevel(event.getSource().getTrueSource(), STRENGTH);
            if (enchantLevel > 0) {
                event.setAmount(event.getAmount() + enchantLevel);
            }
        }
    }

    @Override
    public ResourceLocation getIcon() {
        return null;
    }
}
