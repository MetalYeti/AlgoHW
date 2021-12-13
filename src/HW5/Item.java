package HW5;

public class Item{
    public String name;
    public int weight;
    public int cost;

    public Item(String name, int weight, int cost) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (weight != item.weight) return false;
        if (cost != item.cost) return false;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + weight;
        result = 31 * result + cost;
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
