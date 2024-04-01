import java.util.HashMap;

public class Adventurer {
    private int id; //冒险者的id，为唯一标识
    private String name; //冒险者的名称，可能相同
    private int sumBottle = 0;
    private int sumEquipment = 0;
    private HashMap<Integer, Bottle> bottleMap; //储存冒险者持有的所有药水瓶
    private HashMap<Integer, Equipment> equipmentMap; //储存冒险者持有的所有药水瓶

    public Adventurer(int id, String name) {
        this.id = id;
        this.name = name;
        this.bottleMap = new HashMap<>();
        this.equipmentMap = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public int getSumBottle() {
        return sumBottle;
    }

    public int getSumEquipment() {
        return sumEquipment;
    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, Bottle> getBottleMap() {
        return bottleMap;
    }

    public HashMap<Integer, Equipment> getEquipmentMap() {
        return equipmentMap;
    }

    public void addBottle(int id, String name, int capacity) {
        Bottle tempBottle = new Bottle(id, name, capacity);
        bottleMap.put(tempBottle.getId(), tempBottle);
        sumBottle++;
    }

    public void addEquipment(int id, String name, int star) {
        Equipment tempEquipment = new Equipment(id, name, star);
        equipmentMap.put(tempEquipment.getId(), tempEquipment);
        sumEquipment++;
    }

    public void deleteBottle(int id) {
        Bottle tempBottle = bottleMap.get(id);
        sumBottle--;
        System.out.println(sumBottle + " " + tempBottle.getName());
        bottleMap.remove(id, tempBottle);
    }

    public void deleteEquipment(int id) {
        Equipment tempEquipment = equipmentMap.get(id);
        sumEquipment--;
        System.out.println(sumEquipment + " " + tempEquipment.getName());
        equipmentMap.remove(id, tempEquipment);
    }

    public void addEquipmentStar(int id) {
        Equipment tempEquipment = equipmentMap.get(id);
        tempEquipment.addStar();
        System.out.println(tempEquipment.getName() + " " + tempEquipment.getStar());
    }
}
