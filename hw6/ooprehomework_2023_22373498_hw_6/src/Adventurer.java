import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Adventurer {
    private int id;
    private String name;
    private int hitPoint = 500;
    private int level = 1;
    private HashMap<Integer, Adventurer> hiredMap = new HashMap<>();
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

    public void minusHitPoint(int value) {
        hitPoint -= value;
    }

    public void addLevel(int value) {
        level += value;
    }

    // Bottle
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

    public boolean useBottle(String bottleName, boolean fightMode) {
        int id = bag.getBottleId(bottleName);
        if (id == -1) {
            if (!fightMode) {
                System.out.println("fail to use " + bottleName);
            }
            return false;
        }
        int delta = bag.useBottle(id, hitPoint);
        hitPoint += delta;
        if (delta == 0) {
            bottles.remove(id);
        }
        System.out.println(id + " " + hitPoint);
        return true;
    }

    // Equipment
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
    public void addFood(int id, String name, int energy, long price) {
        Food food = new Food(id, name, energy, price);
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

    public void useFood(String foodName, boolean fightMode) {
        int id = bag.getFoodId(foodName);
        if (id == -1) {
            if (!fightMode) {
                System.out.println("fail to eat " + foodName);
            }
            return;
        }
        level += bag.useFood(id);
        foods.remove(id);
        bag.upgradeMaxCapacity(level);
        System.out.println(id + " " + level);
    }

    // 1v1
    public void fuckYou(Adventurer adv, String name) {
        int equipmentId = this.getBag().getEquipmentId(name);
        if (equipmentId == -1) {
            return;
        }
        Equipment equipment = equipments.get(equipmentId);
        int damage = 0;
        switch (equipment.getType()) {
            case 1:
                damage = equipment.getStar() * this.getLevel();
                break;
            case 2:
                damage = equipment.getStar() * this.getLevel() + equipment.getCritical();
                break;
            case 3:
                damage = (int) Math.floor(adv.getHitPoint() * equipment.getRatio());
                break;
            default:
                break;
        };
        adv.minusHitPoint(damage);
    }

    // AOE
    public void fuckEveryone(LinkedHashMap<Integer, Adventurer> participants, String name,
                             FightLogContainer container, String date) {
        int equipmentId = this.getBag().getEquipmentId(name);
        if (equipmentId == -1) {
            return;
        }
        Equipment equipment = equipments.get(equipmentId);
        for (Map.Entry<Integer, Adventurer> entry : participants.entrySet()) {
            if (entry.getValue().getId() == this.getId()) {
                continue;
            }
            int damage = 0;
            switch (equipment.getType()) {
                case 1:
                    damage = equipment.getStar() * this.getLevel();
                    break;
                case 2:
                    damage = equipment.getStar() * this.getLevel() + equipment.getCritical();
                    break;
                case 3:
                    damage = (int) Math.floor(entry.getValue().getHitPoint()
                            * equipment.getRatio());
                    break;
                default:
                    break;
            };
            entry.getValue().minusHitPoint(damage);
            container.addAttackedByAoe(date, this.getName(), entry.getValue().getName(), name);
            System.out.print(entry.getValue().getHitPoint() + " ");
        }
    }

    //Hire
    public void hire(HashMap<Integer, Adventurer> map, int id) {
        if (map.containsKey(id) && !hiredMap.containsKey(id)) {
            Adventurer employee = map.get(id);
            hiredMap.put(id, employee);
        }
    }

    //Value
    public long sumValue(boolean print) {
        long sum = 0;
        long cnt = 0;
        for (Map.Entry<Integer, Adventurer> entry : hiredMap.entrySet()) {
            sum += entry.getValue().sumValue(false);
            cnt++;
        }
        for (Map.Entry<Integer, Bottle> entry : bottles.entrySet()) {
            sum += entry.getValue().getPrice();
            cnt++;
        }
        for (Map.Entry<Integer, Equipment> entry : equipments.entrySet()) {
            sum += entry.getValue().getPrice();
            cnt++;
        }
        for (Map.Entry<Integer, Food> entry : foods.entrySet()) {
            sum += entry.getValue().getPrice();
            cnt++;
        }
        if (print) {
            System.out.println(cnt + " " + sum);
        }
        return sum;
    }

    public long maxValue(boolean print) {
        long max = 0;
        long res = 0;
        for (Map.Entry<Integer, Adventurer> entry : hiredMap.entrySet()) {
            max = entry.getValue().sumValue(false);
            res = Math.max(max, res);
        }
        for (Map.Entry<Integer, Bottle> entry : bottles.entrySet()) {
            max = entry.getValue().getPrice();
            res = Math.max(max, res);
        }
        for (Map.Entry<Integer, Equipment> entry : equipments.entrySet()) {
            max = entry.getValue().getPrice();
            res = Math.max(max, res);
        }
        for (Map.Entry<Integer, Food> entry : foods.entrySet()) {
            max = entry.getValue().getPrice();
            res = Math.max(max, res);
        }
        if (print) {
            System.out.println(res);
        }
        return res;
    }

    // Name
    public void checkName(int id) {
        String x = null;
        if (hiredMap.containsKey(id)) {
            x = "Adventurer";
        } else if (foods.containsKey(id)) {
            x = "Food";
        } else if (bottles.containsKey(id)) {
            x = bottles.get(id).getTypeString();
        } else if (equipments.containsKey(id)) {
            x = equipments.get(id).getTypeString();
        }
        System.out.println("Commodity whose id is " + id + " belongs to " + x);
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

    public void addHitPoint(int i) {
        hitPoint += i;
    }
}