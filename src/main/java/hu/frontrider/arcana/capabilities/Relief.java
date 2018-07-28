package hu.frontrider.arcana.capabilities;

public class Relief implements IRelief {

    private float store = 0;

    @Override
    public float getHealthStore() {
        return store;
    }

    @Override
    public void setHealthStore(float health) {
        store = health;
    }

    @Override
    public void addHealthStore(float health) {
        store += health;
    }

    @Override
    public void reset() {
        store = 0;
    }
}
