import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class AdventurerTest {

    @Test
    public void getId() {
        Adventurer adventurer = new Adventurer(12, "sb");
        assertEquals(adventurer.getId(), 12);
    }

    @Test
    public void getName() {
        Adventurer adventurer = new Adventurer(12, "sb");
        assertEquals("sb", adventurer.getName());
    }

    @Test
    public void getHitPoint() {
        Adventurer adventurer = new Adventurer(12, "sb");
        assertEquals(adventurer.getHitPoint(),500);
    }

    @Test
    public void getLevel() {
        Adventurer adventurer = new Adventurer(12, "sb");
        assertEquals(adventurer.getLevel(), 1);
    }

    @Test
    public void addHitPoint() {
        Adventurer adventurer = new Adventurer(12, "sb");
        adventurer.addHitPoint(1919810);
        assertEquals(adventurer.getHitPoint(), 1919810 + 500);
    }

    @Test
    public void addLevel() {
        Adventurer adventurer = new Adventurer(12, "sb");
        adventurer.addLevel(19);
        assertEquals(20, adventurer.getLevel());
    }

    @Test
    public void addBottle() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 191; i++) {
            adventurer.getBottles().put(i, new Bottle(i, "sb", i, i, "RegularBottle"));
        }
        assertEquals(adventurer.getBottles().size(),191);
    }

    @Test
    public void deleteBottle() {
        Shop shop = new Shop();
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 191; i++) {
            adventurer.getBottles().put(i, new Bottle(i, "sb", i, i, "RegularBottle"));
        }
        for (int i = 0; i < 191 - 42; i++) {
            shop.buyB(adventurer.deleteBottle(i));
        }
        assertEquals(adventurer.getBottles().size(), 42);
        assertEquals(adventurer.getMoney(), 11026);
    }

    @Test
    public void carryBottle() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 191; i++) {
            adventurer.getBottles().put(i, new Bottle(i, "sb", i, i, "RecoverBottle"));
            adventurer.getBottles().get(i).setRatio(0.75);
        }
        adventurer.carryBottle(19);
        adventurer.carryBottle(18);
        adventurer.carryBottle(114514);
        assertEquals(adventurer.getBag().getBottleBag().size(), 1);
    }

    @Test
    public void useBottle() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 191; i++) {
            adventurer.getBottles().put(i, new Bottle(i, "sb", i, i, "RegularBottle"));
        }
        adventurer.carryBottle(19);
        adventurer.carryBottle(17);
        adventurer.useBottle("sb", false);
        adventurer.useBottle("sb", true);
        adventurer.useBottle("pan", false);
        assertEquals(adventurer.getHitPoint(), 500 + 19);
    }

    @Test
    public void addEquipment() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 99; i++) {
            adventurer.getEquipments().put(i, new Equipment(i, "a", i, i, "RegulaEquipment"));
        }
        assertEquals(adventurer.getEquipments().size(), 99);
    }

    @Test
    public void deleteEquipment() {
        Shop shop = new Shop();
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 99; i++) {
            adventurer.getEquipments().put(i, new Equipment(i, "a", i, i, "RegulaEquipment"));
        }
        for (int i = 0; i < 99 - 32; i++) {
            shop.buyE(adventurer.deleteEquipment(i));
        }
        assertEquals(adventurer.getEquipments().size(), 32);
        assertEquals(adventurer.getMoney(), 2211);
    }

    @Test
    public void addOneStar() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 99; i++) {
            adventurer.getEquipments().put(i, new Equipment(i, "a", i, i, "EpicEquipment"));
            adventurer.getEquipments().get(i).setRatio(0.5);
        }
        adventurer.addOneStar(1);
        assertEquals(adventurer.getEquipments().get(1).getStar(), 2);
    }

    @Test
    public void carryEquipment() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 99; i++) {
            adventurer.getEquipments().put(i, new Equipment(i, "a", i, i, "EpicEquipment"));
            adventurer.getEquipments().get(i).setRatio(0.5);
        }
        adventurer.carryEquipment(1);
        adventurer.carryEquipment(2);
        adventurer.carryEquipment(3);
        assertEquals(adventurer.getBag().getEquipmentBag().size(), 1);
    }

    @Test
    public void addFood() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 123; i++) {
            adventurer.addFood(i, "f", i, i);
        }
        assertEquals(adventurer.getFoods().size(), 123);
    }

    @Test
    public void deleteFood() {
        Adventurer adventurer = new Adventurer(12, "sb");
        Shop shop = new Shop();
        for (int i = 0; i < 123; i++) {
            adventurer.getFoods().put(i + 1, new Food(i, "f", i, 2));
        }
        for (int i = 0; i < 123 - 67; i++) {
            shop.buyF(adventurer.deleteFood(i + 1));
        }
        assertEquals(adventurer.getFoods().size(), 67);
        assertEquals(adventurer.getMoney(), 112);
    }

    @Test
    public void carryFood() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 123; i++) {
            adventurer.getFoods().put(i + 1, new Food(i, "f", i, i));
        }
        for (int i = 0; i < 67; i++) {
            adventurer.carryFood(i);
        }
        assertEquals(adventurer.getBag().getFoodBag().size(), 66);
    }

    @Test
    public void useFood() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 123; i++) {
            adventurer.getFoods().put(i + 1, new Food(i + 1, "f", i, i));
        }
        for (int i = 0; i < 67; i++) {
            adventurer.carryFood(i + 1);
        }
        for (int i = 0; i < 3; i++) {
            adventurer.useFood("f", false);
        }
        adventurer.useFood("food", true);
        adventurer.useFood("apple", false);
        assertEquals(adventurer.getLevel(), 4);
    }

    @Test
    public void sumValue() {
        HashMap<Integer, Adventurer> map = new HashMap<>();
        Adventurer a1 = new Adventurer(1, "a");
        Adventurer a2 = new Adventurer(2, "b");
        map.put(1, a1);
        map.put(2, a2);
        a1.addFood(9, "s", 12, 18);
        a1.getBottles().put(3, new Bottle(3, "a", 10, 7, "RegularBottle"));
        a1.getEquipments().put(4, new Equipment(4, "a", 10, 10, "RegularEquipment"));
        a2.getEquipments().put(5, new Equipment(5, "k", 10, 15, "RegularEquipment"));
        a1.hire(map, 2);
        a1.hire(map, 2);
        a1.hire(map, 15);
        assertEquals(a1.sumValue(false), 50);
        assertEquals(a2.sumValue(true), 15);
    }

    @Test
    public void maxValue() {
        HashMap<Integer, Adventurer> map = new HashMap<>();
        Adventurer a1 = new Adventurer(1, "a");
        Adventurer a2 = new Adventurer(2, "b");
        map.put(1, a1);
        map.put(2, a2);
        a1.addFood(9, "s", 12, 18);
        a1.getBottles().put(3, new Bottle(3, "a", 10, 7, "RegularBottle"));
        a1.getEquipments().put(4, new Equipment(4, "a", 10, 10, "RegularEquipment"));
        a2.getEquipments().put(5, new Equipment(5, "k", 10, 15, "RegularEquipment"));
        a1.hire(map, 2);
        a1.hire(map, 2);
        a1.hire(map, 15);
        assertEquals(a1.maxValue(false), 18);
        assertEquals(a2.maxValue(true), 15);
    }

    @Test
    public void getHiredMap() {
        HashMap<Integer, Adventurer> map = new HashMap<>();
        Adventurer a1 = new Adventurer(1, "a");
        Adventurer a2 = new Adventurer(2, "b");
        map.put(1, a1);
        map.put(2, a2);
        a1.hire(map, 2);
        assertEquals(a1.getHiredMap().size(), 1);
    }

    @Test
    public void getMoney() {
        Adventurer a1 = new Adventurer(1, "a");
        a1.addMoney(114514);
        a1.useMoney(4514);
        assertEquals(a1.getMoney(), 110000);
        assertEquals(a1.getLoss(), 0);
    }

    @Test
    public void checkName() {
        HashMap<Integer, Adventurer> map = new HashMap<>();
        Adventurer a1 = new Adventurer(1, "a");
        Adventurer a2 = new Adventurer(2, "b");
        map.put(1, a1);
        map.put(2, a2);
        a1.hire(map, 2);
        a1.getBottles().put(3, new Bottle(3, "c", 10, 10, "R"));
        a1.getEquipments().put(4, new Equipment(
                4, "d", 5, 7, "U"
        ));
        a1.getFoods().put(5, new Food(5, "e", 9, 89));
        assertEquals("Adventurer", a1.checkName(2));
        assertEquals("Food", a1.checkName(5));
        assertEquals("U", a1.checkName(4));
        assertEquals("R", a1.checkName(3));
    }

    @Test
    public void sellAll() {
        Adventurer a = new Adventurer(1, "a");
        Shop shop = new Shop();
        a.getBottles().put(21, new Bottle(21, "b1", 10,
                10, "RegularBottle"));
        a.getBottles().put(22, new Bottle(22, "b2", 10,
                10, "RecoverBottle"));
        a.getBottles().put(23, new Bottle(23, "b3", 10,
                10, "ReinforcedBottle"));
        a.getEquipments().put(31, new Equipment(31, "e1", 3, 9, "RegularEquipment"));
        a.getEquipments().put(32, new Equipment(32, "e2", 3, 9, "CritEquipment"));
        a.getEquipments().put(33, new Equipment(33, "e3", 3, 9, "EpicEquipment"));
        a.getFoods().put(4, new Food(4, "f", 89, 78));
        a.carryBottle(21);
        a.carryBottle(22);
        a.carryBottle(23);
        a.carryEquipment(31);
        a.carryEquipment(32);
        a.carryEquipment(33);
        a.carryFood(4);
        a.sellAll(shop);
        assertEquals(a.getMoney(), 30 + 27 + 78);
    }
}