package hu.frontrider.arcana.capabilities;

public class CreatureEnchant implements ICreatureEnchant {
    private int level = 0;
    private String name = "";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    public enum ENCHANTS {
        //enchantments
        PROTECTION,
        FERTILE,
        RESPIRATION,
        STRENGTH,

        //curses
        CURSE_OF_IMMOBILITY,
        CURSE_OF_DECAY,
        CURSE_OF_SLOWNESS,
        CURSE_OF_WEAKNESS,
        CURSE_OF_DUMBNESS

    }


}
