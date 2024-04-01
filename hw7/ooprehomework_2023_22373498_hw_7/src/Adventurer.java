import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Adventurer {
    private final int id;
    private final String name;
    private int hitPoint = 500;
    private int level = 1;
    private HashMap<Integer, Adventurer> hiredMap = new HashMap<>();
    private HashMap<Integer, Bottle> bottles = new HashMap<>();
    private HashMap<Integer, Equipment> equipments = new HashMap<>();
    private HashMap<Integer, Food> foods = new HashMap<>();
    private final Bag bag = new Bag(level);
    private long money = 0;
    private int loss = 0;

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

    public long getMoney() {
        return money;
    }

    public void useMoney(long outcome) {
        money -= outcome;
    }

    public void addMoney(long income) {
        money += income;
    }

    public int getLoss() {
        return loss;
    }

    public void addLoss(int loss) {
        this.loss += loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    // Bottle
    public Bottle deleteBottle(int bottleId) {
        int size = bottles.size() - 1;
        System.out.println(size + " " + bottles.get(bottleId).getName());
        Bottle bottle = bottles.get(bottleId);
        bottles.remove(bottleId);
        bag.deleteBottle(bottleId);
        money += bottle.getPrice();
        return bottle;
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
        Bottle bottle = bottles.get(id);
        if (!bottle.getStatus()) {
            bag.getBottleBag().remove(id);
            bottles.remove(id);
        } else {
            int delta = bag.useBottle(id, hitPoint);
            hitPoint += delta;
        }
        System.out.println(id + " " + hitPoint);
        return true;
    }

    // Equipment
    public Equipment deleteEquipment(int equipmentId) {
        int size = equipments.size() - 1;
        System.out.println(size + " " + equipments.get(equipmentId).getName());
        Equipment equipment = equipments.get(equipmentId);
        equipments.remove(equipmentId);
        bag.deleteEquipment(equipmentId);
        money += equipment.getPrice();
        return equipment;
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

    public Food deleteFood(int foodId) {
        int size = foods.size() - 1;
        System.out.println(size + " " + foods.get(foodId).getName());
        Food food = foods.get(foodId);
        foods.remove(foodId);
        bag.deleteFood(foodId);
        money += food.getPrice();
        return food;
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
        adv.addLoss(damage);
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
            entry.getValue().addLoss(damage);
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
        if (!print) {
            sum += money;
        }
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
    public String checkName(int id) {
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
        return x;
    }

    public void sellAll(Shop shop) {
        long money = 0;
        for (Map.Entry<Integer, Bottle> entry : bag.getBottleBag().entrySet()) {
            shop.buyB(entry.getValue());
            money += entry.getValue().getPrice();
            bottles.remove(entry.getValue().getId());
        }
        for (Map.Entry<Integer, Equipment> entry : bag.getEquipmentBag().entrySet()) {
            shop.buyE(entry.getValue());
            money += entry.getValue().getPrice();
            equipments.remove(entry.getValue().getId());
        }
        for (Map.Entry<Integer, Food> entry : bag.getFoodBag().entrySet()) {
            shop.buyF(entry.getValue());
            money += entry.getValue().getPrice();
            foods.remove(entry.getValue().getId());
        }
        bag.clearBag();
        System.out.println(name + " emptied the backpack " + money);
        this.money += money;
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

    public HashMap<Integer, Adventurer> getHiredMap() {
        return hiredMap;
    }
}