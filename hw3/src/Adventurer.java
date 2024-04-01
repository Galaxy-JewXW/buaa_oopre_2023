import java.util.HashMap;

public class Adventurer {
    private int id;
    private String name;
    private int hitPoint = 500;
    private int level = 1;
    private HashMap<Integer, Bottle> bottles = new HashMap<>();
    private HashMap<Integer, Equipment> equipments = new HashMap<>();
    private HashMap<Integer, Food> foods = new HashMap<>();
    private Bag bag = new Bag(level);

    public Adventurer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getLevel() {
        return level;
    }

    public void addHitPoint(int value) {
        hitPoint += value;
    }

    public void addLevel(int value) {
        level += value;
    }

    // Bottle
    public void addBottle(int id, String name, int capacity) {
        Bottle bottle = new Bottle(id, name, capacity);
        bottles.put(bottle.getId(), bottle);
    }

    public void deleteBottle(int bottleId) {
        int size = bottles.size() - 1;
        System.out.println(size + " " + bottles.get(bottleId).getName());
        bottles.remove(bottleId);
        bag.deleteBottle(bottleId);
    }

    public void carryBottle(int bottleId) {
        if (bottles.containsKey(bottleId)) {
            bag.upgradeMaxCapacity(level);
            bag.addBottle(bottleId, bottles.get(bottleId));
        }
    }

    public void useBottle(String bottleName) {
        int id = bag.getBottleId(bottleName);
        if (id == -1) {
            System.out.println("fail to use " + bottleName);
            return;
        }
        int delta = bag.useBottle(id);
        hitPoint += delta;
        if (delta == 0) {
            bottles.remove(id);
        }
        System.out.println(id + " " + hitPoint);
    }

    // Equipment
    public void addEquipment(int id, String name, int star) {
        Equipment equipment = new Equipment(id, name, star);
        equipments.put(equipment.getId(), equipment);
    }

    public void deleteEquipment(int equipmentId) {
        int size = equipments.size() - 1;
        System.out.println(size + " " + equipments.get(equipmentId).getName());
        equipments.remove(equipmentId);
        bag.deleteEquipment(equipmentId);
    }

    public void addOneStar(int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);
        equipment.addOneStar();
        System.out.println(equipment.getName() + " " + equipment.getStar());
    }

    public void carryEquipment(int equipmentId) {
        if (equipments.containsKey(equipmentId)) {
            bag.addEquipment(equipmentId, equipments.get(equipmentId));
        }
    }

    // Food
    public void addFood(int id, String name, int energy) {
        Food food = new Food(id, name, energy);
        foods.put(food.getId(), food);
    }

    public void deleteFood(int foodId) {
        int size = foods.size() - 1;
        System.out.println(size + " " + foods.get(foodId).getName());
        foods.remove(foodId);
        bag.deleteFood(foodId);
    }

    public void carryFood(int foodId) {
        if (foods.containsKey(foodId)) {
            bag.addFood(foodId, foods.get(foodId));
        }
    }

    public void useFood(String foodName) {
        int id = bag.getFoodId(foodName);
        if (id == -1) {
            System.out.println("fail to eat " + foodName);
            return;
        }
        level += bag.useFood(id);
        foods.remove(id);
        bag.upgradeMaxCapacity(level);

        System.out.println(id + " " + level);
    }

    // 以下为测试代码

    public Bag getBag() {
        return bag;
    }

    public HashMap<Integer, Bottle> getBottles() {
        return bottles;
    }

    public HashMap<Integer, Equipment> getEquipments() {
        return equipments;
    }

    public HashMap<Integer, Food> getFoods() {
        return foods;
    }
}