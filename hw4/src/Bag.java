import java.util.HashMap;
import java.util.Map;

public class Bag {
    private int maxCapacity;
    private HashMap<Integer, Bottle> bottleBag = new HashMap<>();
    private HashMap<Integer, Equipment> equipmentBag = new HashMap<>();
    private HashMap<Integer, Food> foodBag = new HashMap<>();

    public Bag(int adventurerLevel) {
        this.maxCapacity = adventurerLevel / 5 + 1;
    }

    public void upgradeMaxCapacity(int adventurerLevel) {
        maxCapacity = adventurerLevel / 5 + 1;
    }

    // Bottle
    public void addBottle(int bottleId, Bottle bottle) {
        int count = 0;
        for (Bottle b : bottleBag.values()) {
            if (b.getName().equals(bottle.getName())) {
                count++;
            }
        }
        if (count < maxCapacity) {
            bottleBag.put(bottleId, bottle);
        }
    }

    public void deleteBottle(int bottleId) {
        bottleBag.remove(bottleId);
    }

    public int getBottleId(String bottleName) {
        int minId = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Bottle> entry : bottleBag.entrySet()) {
            Bottle bottle = entry.getValue();
            if (bottleName.equals(bottle.getName()) && bottle.getId() < minId) {
                minId = bottle.getId();
            }
        }
        if (minId == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minId;
        }
    }

    public int useBottle(int bottleId) {
        if (bottleBag.containsKey(bottleId)) {
            int res = bottleBag.get(bottleId).getCapacity();
            if (res == 0) {
                bottleBag.remove(bottleId);
            } else {
                bottleBag.get(bottleId).setCapacity(0);
            }
            return res;
        } else {
            return -1;
        }
    }

    // Equipment
    public boolean isIn(String equipmentName) {
        for (Equipment equipment : equipmentBag.values()) {
            if (equipment.getName().equals(equipmentName)) {
                return true;
            }
        }
        return false;
    }

    public void addEquipment(int equipmentId, Equipment equipment) {
        if (isIn(equipment.getName())) {
            for (Map.Entry<Integer, Equipment> entry : equipmentBag.entrySet()) {
                if (equipment.getName().equals(entry.getValue().getName())) {
                    equipmentBag.remove(entry.getValue().getId());
                    break;
                }
            }
        }
        equipmentBag.put(equipmentId, equipment);
    }

    public void deleteEquipment(int equipmentId) {
        equipmentBag.remove(equipmentId);
    }

    public int getEquipmentId(String equipmentName) {
        for (Map.Entry<Integer, Equipment> entry : equipmentBag.entrySet()) {
            Equipment equipment = entry.getValue();
            if (equipment.getName().equals(equipmentName)) {
                return equipment.getId();
            }
        }
        return -1;
    }

    // Food
    public void addFood(int foodId, Food food) {
        foodBag.put(foodId, food);
    }

    public void deleteFood(int foodId) {
        foodBag.remove(foodId);
    }

    public int getFoodId(String foodName) {
        int minId = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Food> entry : foodBag.entrySet()) {
            Food food = entry.getValue();
            if (foodName.equals(food.getName()) && food.getId() < minId) {
                minId = food.getId();
            }
        }
        if (minId == Integer.MAX_VALUE) {
            return -1;
        } else {
            return minId;
        }
    }

    public int useFood(int foodId) {
        if (foodBag.containsKey(foodId)) {
            int energy = foodBag.get(foodId).getEnergy();
            deleteFood(foodId);
            return energy;
        } else {
            return 0;
        }
    }

    //以下为测试

    public HashMap<Integer, Bottle> getBottleBag() {
        return bottleBag;
    }

    public HashMap<Integer, Equipment> getEquipmentBag() {
        return equipmentBag;
    }

    public HashMap<Integer, Food> getFoodBag() {
        return foodBag;
    }
}
