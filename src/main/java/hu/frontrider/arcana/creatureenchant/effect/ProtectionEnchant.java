package hu.frontrider.arcana.creatureenchant.effect;

import hu.frontrider.arcana.creatureenchant.backend.CreatureEnchant;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class ProtectionEnchant extends CreatureEnchant {

    public ProtectionEnchant() {
        super(new ResourceLocation(MODID, "protection"),"protection");
    }

    @SubscribeEvent
    public void handleEvent(LivingHurtEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        int enchantLevel = getEnchantLevel(entity, this);

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
    public AspectList formula() {
        return new AspectList()
                .merge(Aspect.PROTECT,100)
                .merge(Aspect.METAL,60)
                .merge(Aspect.AVERSION,10)
                .merge(Aspect.CRYSTAL,3)
                .merge(Aspect.EARTH,20)
                .merge(Aspect.MAGIC,70);
    }

    @Override
    public String getResearch() {
        return "CREATURE_ENCHANT";
    }
}
