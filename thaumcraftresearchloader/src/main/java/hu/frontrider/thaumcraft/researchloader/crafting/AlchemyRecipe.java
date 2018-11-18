package hu.frontrider.thaumcraft.researchloader.crafting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import java.util.Map;

public class AlchemyRecipe {

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("research_key")
    @Expose
    private String researchKey;
    @SerializedName("result")
    @Expose
    private ItemStack result;
    @SerializedName("catalyst")
    @Expose
    private ItemStack catalyst;
    @SerializedName("aspects")
    @Expose
    private AspectList aspects = null;

    public String getResearchKey() {
        return researchKey;
    }

    public void setResearchKey(String researchKey) {
        this.researchKey = researchKey;
    }

    public ItemStack getResult() {
        return result;
    }

    public void setResult(ItemStack result) {
        this.result = result;
    }

    public ItemStack getCatalyst() {
        return catalyst;
    }

    public void setCatalyst(ItemStack catalyst) {
        this.catalyst = catalyst;
    }

    public AspectList getAspects() {
        return aspects;
    }

    public void setAspects(AspectList aspects) {
        this.aspects = aspects;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}