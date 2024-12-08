public class Item {
    private final String name;
    private final int effect;
    private final int cost;

    public Item(String name, int effect, int cost) {
        this.name = name;
        this.effect = effect;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getEffect() {
        return effect;
    }

    public int getCost() {
        return cost;
    }
}
