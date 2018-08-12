package thaumcraft.api.crafting;

public interface IStabilizable {
	void addStability(int multiples);
	EnumStability getStability();
	
	enum EnumStability {
		VERY_STABLE, STABLE, UNSTABLE, VERY_UNSTABLE 
	}
}
