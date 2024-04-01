import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shop {
    private final HashMap<Integer, Bottle> bottles = new HashMap<>();
    private final HashMap<Integer, Equipment> equipments  = new HashMap<>();
    private final HashMap<Integer, Food> foods = new HashMap<>();
    private long bottlePrice;
    private long equipmentPrice;
    private long foodPrice;
    private int capacity;
    private int star;
    private int energy;

    public void buyB(Bottle bottle) {
        bottles.put(bottle.getId(), bottle);
        int size = bottles.size();
        long sumPrice = 0;
        int sumCapacity = 0;
        for (Map.Entry<Integer, Bottle> entry : bottles.entrySet()) {
            sumPrice += entry.getValue().getPrice();
            sumCapacity += entry.getValue().getCapacity();
        }
        bottlePrice = sumPrice / size;
        capacity = sumCapacity / size;
    }

    public void buyE(Equipment equipment) {
        equipments.put(equipment.getId(), equipment);
        int size = equipments.size();
        long sumPrice = 0;
        int sumStar = 0;
        for (Map.Entry<Integer, Equipment> entry : equipments.entrySet()) {
            sumPrice += entry.getValue().getPrice();
            sumStar += entry.getValue().getStar();
        }
        equipmentPrice = sumPrice / size;
        star = sumStar / size;
    }

    public void buyF(Food food) {
        foods.put(food.getId(), food);
        int size = foods.size();
        long sumPrice = 0;
        int sumEnergy = 0;
        for (Map.Entry<Integer, Food> entry : foods.entrySet()) {
            sumPrice += entry.getValue().getPrice();
            sumEnergy += entry.getValue().getEnergy();
        }
        foodPrice = sumPrice / size;
        energy = sumEnergy / size;
    }

    public void sell(Adventurer adv, ArrayList<String> l) {
        String type = l.get(4);
        switch (type) {
            case "RegularBottle":
            case "RecoverBottle":
            case "ReinforcedBottle":
                sellBottle(adv, l);
                break;
            case "RegularEquipment":
            case "CritEquipment":
            case "EpicEquipment":
                sellEquipment(adv, l);
                break;
            case "Food":
                sellFood(adv, l);
                break;
            default:
                break;
        }
    }

    private void sellBottle(Adventurer adv, ArrayList<String> l) {
        int id = Integer.parseInt(l.get(2));
        String name = l.get(3);
        String type = l.get(4);
        boolean sold = true;
        switch (type) {
            case "RegularBottle": {
                Bottle bottle = new Bottle(id, name, capacity, bottlePrice, type);
                if (adv.getMoney() >= bottlePrice) {
                    adv.getBottles().put(id, bottle);
                    adv.useMoney(bottlePrice);
                } else {
                    sold = false;
                } break;
            } case "RecoverBottle": case "ReinforcedBottle": {
                Bottle bottle = new Bottle(id, name, capacity, bottlePrice, type);
                bottle.setRatio(Double.parseDouble(l.get(5)));
                if (adv.getMoney() >= bottlePrice) {
                    adv.getBottles().put(id, bottle);
                    adv.useMoney(bottlePrice);
                } else {
                    sold = false;
                } break;
            } default:
                break;
        }
        if (sold) {
            System.out.println("successfully buy " + name + " for " + bottlePrice);
        } else {
            System.out.println("failed to buy " + name + " for " + bottlePrice);
        }
    }

    private void sellEquipment(Adventurer adv, ArrayList<String> l) {
        int id = Integer.parseInt(l.get(2));
        String name = l.get(3);
        String type = l.get(4);
        boolean sold = true;
        switch (type) {
            case "RegularEquipment": {
                Equipment e = new Equipment(id, name, star, equipmentPrice, type);
                if (adv.getMoney() >= equipmentPrice) {
                    adv.getEquipments().put(id, e);
                    adv.useMoney(equipmentPrice);
                } else {
                    sold = false;
                } break;
            } case "CritEquipment": {
                Equipment e = new Equipment(id, name, star, equipmentPrice, type);
                e.setCritical(Integer.parseInt(l.get(5)));
                if (adv.getMoney() >= equipmentPrice) {
                    adv.getEquipments().put(id, e);
                    adv.useMoney(equipmentPrice);
                } else {
                    sold = false;
                } break;
            } case "EpicEquipment": {
                Equipment e = new Equipment(id, name, star, equipmentPrice, type);
                e.setRatio(Double.parseDouble(l.get(5)));
                if (adv.getMoney() >= equipmentPrice) {
                    adv.getEquipments().put(id, e);
                    adv.useMoney(equipmentPrice);
                } else {
                    sold = false;
                } break;
            } default:
                break;
        }
        if (sold) {
            System.out.println("successfully buy " + name + " for " + equipmentPrice);
        } else {
            System.out.println("failed to buy " + name + " for " + equipmentPrice);
        }
    }

    private void sellFood(Adventurer adv, ArrayList<String> l) {
        int id = Integer.parseInt(l.get(2));
        String name = l.get(3);
        Food f = new Food(id, name, energy, foodPrice);
        if (adv.getMoney() >= foodPrice) {
            adv.getFoods().put(id, f);
            adv.useMoney(foodPrice);
            System.out.println("successfully buy " + name + " for " + foodPrice);
        } else {
            System.out.println("failed to buy " + name + " for " + foodPrice);
        }
    }
}
