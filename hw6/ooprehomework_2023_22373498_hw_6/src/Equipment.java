public class Equipment {
    private int id;
    private String name;
    private int star;
    private int type;
    private long price;
    private String typeString;
    private double ratio;
    private int critical;

    public Equipment(int id, String name, int star, long price, String typeString) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.price = price;
        this.typeString = typeString;
        switch (typeString) {
            case "RegularEquipment":
                this.type = 1;
                break;
            case "CritEquipment":
                this.type = 2;
                break;
            case "EpicEquipment":
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

    public int getStar() {
        return star;
    }

    public void addOneStar() {
        star++;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public double getRatio() {
        return ratio;
    }

    public int getCritical() {
        return critical;
    }

    public int getType() {
        return type;
    }

    public long getPrice() {
        return price;
    }

    public String getTypeString() {
        return typeString;
    }
}