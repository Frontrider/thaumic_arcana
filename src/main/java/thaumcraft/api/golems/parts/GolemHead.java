package thaumcraft.api.golems.parts;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import thaumcraft.api.golems.EnumGolemTrait;

public class GolemHead {

    protected static GolemHead[] heads = new GolemHead[1];
    private static byte lastID = 0;
    public byte id;
    public String key;
    public String[] research;
    /**
     * The icon used in the golem builder
     */
    public ResourceLocation icon;
    public Object[] components;
    public EnumGolemTrait[] traits;
    public IHeadFunction function;
    public PartModel model;

    public GolemHead(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, EnumGolemTrait[] tags) {
        this.key = key;
        this.research = research;
        this.icon = icon;
        this.components = comp;
        this.traits = tags;
        this.model = model;
        this.function = null;
    }

    public GolemHead(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, IHeadFunction function, EnumGolemTrait[] tags) {
        this(key, research, icon, model, comp, tags);
        this.function = function;
    }

    public static void register(GolemHead thing) {
        thing.id = lastID;
        lastID++;
        // allocate space
        if (thing.id >= heads.length) {
            GolemHead[] temp = new GolemHead[thing.id + 1];
            System.arraycopy(heads, 0, temp, 0, heads.length);
            heads = temp;
        }
        heads[thing.id] = thing;
    }

    public static GolemHead[] getHeads() {
        return heads;
    }

    public String getLocalizedName() {
        return I18n.translateToLocal("golem.head." + this.key.toLowerCase());
    }

    public String getLocalizedDescription() {
        return I18n.translateToLocal("golem.head.text." + this.key.toLowerCase());
    }

    /**
     * This optional interface allows you to create a class that will add functionality for a specific part.
     */
    public interface IHeadFunction extends IGenericFunction {

    }

}