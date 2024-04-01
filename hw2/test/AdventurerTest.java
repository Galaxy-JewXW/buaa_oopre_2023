import org.junit.Test;
import java.util.HashMap;

public class AdventurerTest {

    @Test
    public void addBottle() {
        Adventurer A1 = new Adventurer(114514, "apple");
        A1.addBottle(1919, "banana", 10);
        assert (A1.getSumBottle() == 1);
    }

    @Test
    public void addEquipment() {
        Adventurer A1 = new Adventurer(114514, "apple");
        A1.addEquipment(89, "peer",5);
        assert (A1.getSumEquipment() == 1);
    }

    @Test
    public void deleteBottle() {
        Adventurer A1 = new Adventurer(114514, "apple");
        A1.addBottle(1919, "banana", 10);
        A1.deleteBottle(1919);
        assert (A1.getSumBottle() == 0);
    }

    @Test
    public void deleteEquipment() {
        Adventurer A1 = new Adventurer(114514, "apple");
        A1.addEquipment(89, "peer",5);
        A1.deleteEquipment(89);
        assert (A1.getSumEquipment() == 0);
    }

    @Test
    public void addEquipmentStar() {
        Adventurer A1 = new Adventurer(114514, "apple");
        A1.addEquipment(89, "peer",5);
        HashMap<Integer, Equipment> map = A1.getEquipmentMap();
        A1.addEquipmentStar(89);
        assert (map.get(89).getStar() == 6);
    }
}