import org.junit.Test;
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
            adventurer.addBottle(i, "sb", i * 2);
        }
        assertEquals(adventurer.getBottles().size(),191);
    }

    @Test
    public void deleteBottle() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 191; i++) {
            adventurer.addBottle(i, "sb", i * 2);
        }
        for (int i = 0; i < 191 - 42; i++) {
            adventurer.deleteBottle(i);
        }
        assertEquals(adventurer.getBottles().size(), 42);
    }

    @Test
    public void carryBottle() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 191; i++) {
            adventurer.addBottle(i, "sb", i * 2);
        }
        adventurer.carryBottle(19);
        adventurer.carryBottle(18);
        assertEquals(adventurer.getBag().getBottleBag().size(), 1);
    }

    @Test
    public void useBottle() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 191; i++) {
            adventurer.addBottle(i, "sb", i * 2);
        }
        adventurer.carryBottle(19);
        adventurer.carryBottle(17);
        adventurer.useBottle("sb");
        adventurer.useBottle("sb");
        adventurer.useBottle("pan");
        assertEquals(adventurer.getHitPoint(), 500 + 19 * 2);
    }

    @Test
    public void addEquipment() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 99; i++) {
            adventurer.addEquipment(i, "you", i + 1);
        }
        assertEquals(adventurer.getEquipments().size(), 99);
    }

    @Test
    public void deleteEquipment() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 99; i++) {
            adventurer.addEquipment(i, "you", i + 1);
        }
        for (int i = 0; i < 99 - 32; i++) {
            adventurer.deleteEquipment(i);
        }
        assertEquals(adventurer.getEquipments().size(), 32);
    }

    @Test
    public void addOneStar() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 1; i++) {
            adventurer.addEquipment(i, "you", i + 1);
        }
        adventurer.addOneStar(0);
        assertEquals(adventurer.getEquipments().get(0).getStar(), 2);
    }

    @Test
    public void carryEquipment() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 99; i++) {
            adventurer.addEquipment(i, "you", i + 1);
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
            adventurer.addFood(i + 1, "sb", i + 3);
        }
        assertEquals(adventurer.getFoods().size(), 123);
    }

    @Test
    public void deleteFood() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 123; i++) {
            adventurer.addFood(i + 1, "sb", i + 3);
        }
        for (int i = 0; i < 123 - 67; i++) {
            adventurer.deleteFood(i + 1);
        }
        assertEquals(adventurer.getFoods().size(), 67);
    }

    @Test
    public void carryFood() {
        Adventurer adventurer = new Adventurer(12, "sb");
        for (int i = 0; i < 123; i++) {
            adventurer.addFood(i + 1, "sb", i + 3);
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
            adventurer.addFood(i + 1, "sb", i + 3);
        }
        for (int i = 0; i < 67; i++) {
            adventurer.carryFood(i + 1);
        }
        for (int i = 0; i < 2; i++) {
            adventurer.useFood("sb");
        }
        adventurer.useFood("food");
        assertEquals(adventurer.getLevel(), 8);
    }
}