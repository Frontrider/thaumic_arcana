package thaumcraft.api.golems;

import net.minecraft.item.ItemStack;
import thaumcraft.api.golems.parts.*;

import java.util.Set;

public interface IGolemProperties {

    Set<EnumGolemTrait> getTraits();

    boolean hasTrait(EnumGolemTrait tag);

    long toLong();

    ItemStack[] generateComponents();

    GolemMaterial getMaterial();

    //material
    void setMaterial(GolemMaterial mat);

    GolemHead getHead();

    //head
    void setHead(GolemHead mat);

    GolemArm getArms();

    //arms
    void setArms(GolemArm mat);

    GolemLeg getLegs();

    //legs
    void setLegs(GolemLeg mat);

    GolemAddon getAddon();

    //addon
    void setAddon(GolemAddon mat);

    int getRank();

    //rank
    void setRank(int r);


}