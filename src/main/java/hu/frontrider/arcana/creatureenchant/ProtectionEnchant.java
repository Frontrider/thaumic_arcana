package hu.frontrider.arcana.creatureenchant;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static hu.frontrider.arcana.creatureenchant.backend.CEnchantment.PROTECTION;

public class ProtectionEnchant extends CreatureEnchant<LivingHurtEvent> {
    public ProtectionEnchant() {
        super(LivingHurtEvent.class);
    }

    @Override
    @SubscribeEvent
    public void handleEvent(LivingHurtEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        int enchantLevel = getEnchantLevel(entity, PROTECTION);

        if (enchantLevel>0) {
                float amount = event.getAmount() / enchantLevel;
                System.out.println("event amount = " + event.getAmount());
                System.out.println("amount = " + amount);
                if (amount < 1 && entity.world.rand.nextBoolean())
                    amount = 1;
                event.setAmount(amount);
            }
    }

    @Override
    public ResourceLocation getIcon() {
        return null;
    }
}
