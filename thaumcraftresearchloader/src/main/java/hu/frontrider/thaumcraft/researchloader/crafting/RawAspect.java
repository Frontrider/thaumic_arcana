package hu.frontrider.thaumcraft.researchloader.crafting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RawAspect {

@SerializedName("tag")
@Expose
private String tag;
@SerializedName("amount")
@Expose
private int amount;

public String getTag() {
return tag;
}

public void setTag(String tag) {
this.tag = tag;
}

public int getAmount() {
return amount;
}

public void setAmount(int amount) {
this.amount = amount;
}

}