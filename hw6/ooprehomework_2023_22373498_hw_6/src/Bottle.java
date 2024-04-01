public class Bottle {
    private int id;
    private String name;
    private int capacity;
    private final long price;
    private String typeString;
    private int type;
    private double ratio;
    private boolean status;

    public Bottle(int id, String name, int capacity, long price, String typeString) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.typeString = typeString;
        this.status = true; // not used
        switch (typeString) {
            case "RegularBottle":
                this.type = 1;
                break;
            case "ReinforcedBottle":
                this.type = 2;
                break;
            case "RecoverBottle":
                this.type = 3;
                break;
            default:
                break;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public long getPrice() {
        return price;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }

    public int getType() {
        return type;
    }

    public String getTypeString() {
        return typeString;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean newStatus) {
        status = newStatus;
    }
}
