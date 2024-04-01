public class Food {
    private int id;
    private String name;
    private int energy;
    private long price;

    public Food(int id, String name, int energy, long price) {
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public long getPrice() {
        return price;
    }
}
