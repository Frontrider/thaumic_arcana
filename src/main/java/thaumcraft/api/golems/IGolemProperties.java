package thaumcraft.api.golems;

import net.minecraft.item.ItemStack;
import thaumcraft.api.golems.parts.*;

import java.util.Set;

public interface IGolemProperties {

	Set<EnumGolemTrait> getTraits();

	boolean hasTrait(EnumGolemTrait tag);
	
	long toLong();

	ItemStack[] generateComponents();
	
	
	//material
    void setMaterial(GolemMaterial mat);

	GolemMaterial getMaterial();

	//head
    void setHead(GolemHead mat);

	GolemHead getHead();

	//arms
    void setArms(GolemArm mat);

	GolemArm getArms();

	//legs
    void setLegs(GolemLeg mat);

	GolemLeg getLegs();

	//addon
    void setAddon(GolemAddon mat);

	GolemAddon getAddon();

	//rank
    void setRank(int r);

	int getRank();

	
	
	

}