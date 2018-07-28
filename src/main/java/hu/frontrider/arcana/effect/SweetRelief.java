package hu.frontrider.arcana.effect;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static hu.frontrider.arcana.ThaumicArcana.MODID;

public class SweetRelief extends Potion {

    static final ResourceLocation rl = new ResourceLocation(MODID, "textures/misc/potions.png");

    public static Potion instance;

    public SweetRelief() {
        super(false, 14591487);
        this.setIconIndex(0, 0);
        this.setPotionName("potion.sweet_relief");
        setRegistryName(MODID, "sweet_relief");
        this.setIconIndex(1, 1);
        this.setEffectiveness(0.25D);
    }

    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(rl);
        return super.getStatusIconIndex();
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        System.out.println("affected");
    }
}