package hu.frontrider.thaumcraft.researchloader.crafting;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RawAlchemyRecipe {

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("research_key")
    @Expose
    private String researchKey;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("catalyst")
    @Expose
    private String catalyst;
    @SerializedName("aspects")
    @Expose
    private List<RawAspect> aspects = null;

    public String getResearchKey() {
        return researchKey;
    }

    public void setResearchKey(String researchKey) {
        this.researchKey = researchKey;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCatalyst() {
        return catalyst;
    }

    public void setCatalyst(String catalyst) {
        this.catalyst = catalyst;
    }

    public List<RawAspect> getAspects() {
        return aspects;
    }

    public void setAspects(List<RawAspect> aspects) {
        this.aspects = aspects;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}