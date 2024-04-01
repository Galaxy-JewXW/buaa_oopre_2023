import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FightLogContainerTest {

    @Test
    public void searchDate() {
        FightExecutor f = new FightExecutor();
        HashMap<Integer, Adventurer> map = new HashMap<>();
        map.put(1, new Adventurer(1, "a"));
        map.put(2, new Adventurer(2, "b"));
        map.put(3, new Adventurer(3, "c"));
        Equipment equipment = new Equipment(3, "ee", 10, 5,
                "RegularEquipment");
        map.get(1).getEquipments().put(3, equipment);
        map.get(1).carryEquipment(3);

        equipment = new Equipment(4, "ff", 30, 5,
                "CritEquipment");
        equipment.setCritical(10);
        map.get(2).getEquipments().put(4, equipment);
        map.get(2).carryEquipment(4);
        map.get(2).carryEquipment(1144);
        map.get(2).carryBottle(11445144);

        equipment = new Equipment(7, "gg", 30, 5,
                "EpicEquipment");
        equipment.setRatio(0.5);
        map.get(3).getEquipments().put(7, equipment);
        map.get(3).carryEquipment(7);
        map.get(3).carryEquipment(514);

        String[] info = {"14" ,"3", "7", "a", "b", "c"};
        f.initParticipants(new ArrayList<>(Arrays.asList(info)), map);

        map.get(3).getBottles().put(98, new Bottle(98, "bb", 10, 100,
                "RegularBottle"));
        map.get(3).carryBottle(98);
        String[] s = {"2022/09-a@b-ee", "2022/09-a@#-ee", "2022/09-a-bB",
                "2022/09-a@b-eE", "2022/09-a@#-eE", "2022/09-b@a-ff",
                "2022/09-c-bb", "2022/09-c@#-gg", "2022/09-a@d-ee",
                "2022/09-d-kk"};
        for (int i = 0; i < 10; i++) {
            f.executeLog(s[i]);
        }
        assertEquals(f.getContainer().searchDate("2022/09"), 5);
        assertEquals(f.getContainer().searchDate("2022/10"), 0);
    }

    @Test
    public void searchAttacker() {
        FightExecutor f = new FightExecutor();
        HashMap<Integer, Adventurer> map = new HashMap<>();
        map.put(1, new Adventurer(1, "a"));
        map.put(2, new Adventurer(2, "b"));
        map.put(3, new Adventurer(3, "c"));
        Equipment equipment = new Equipment(3, "ee", 10, 5,
                "RegularEquipment");
        map.get(1).getEquipments().put(3, equipment);
        map.get(1).carryEquipment(3);

        equipment = new Equipment(4, "ff", 30, 5,
                "CritEquipment");
        equipment.setCritical(10);
        map.get(2).getEquipments().put(4, equipment);
        map.get(2).carryEquipment(4);
        map.get(2).carryEquipment(1144);
        map.get(2).carryBottle(11445144);

        equipment = new Equipment(7, "gg", 30, 5,
                "EpicEquipment");
        equipment.setRatio(0.5);
        map.get(3).getEquipments().put(7, equipment);
        map.get(3).carryEquipment(7);
        map.get(3).carryEquipment(514);

        String[] info = {"14" ,"3", "7", "a", "b", "c"};
        f.initParticipants(new ArrayList<>(Arrays.asList(info)), map);

        map.get(3).getBottles().put(98, new Bottle(98, "bb", 10, 100,
                "RegularBottle"));
        map.get(3).carryBottle(98);
        String[] s = {"2022/09-a@b-ee", "2022/09-a@#-ee", "2022/09-a-bB",
                "2022/09-a@b-eE", "2022/09-a@#-eE", "2022/09-b@a-ff",
                "2022/09-c-bb", "2022/09-c@#-gg", "2022/09-a@d-ee",
                "2022/09-d-kk"};
        for (int i = 0; i < 10; i++) {
            f.executeLog(s[i]);
        }
        assertEquals(f.getContainer().searchAttacker("a"), 2);
        assertEquals(f.getContainer().searchAttacker("k"), 0);
    }

    @Test
    public void searchFucked() {
        FightExecutor f = new FightExecutor();
        HashMap<Integer, Adventurer> map = new HashMap<>();
        map.put(1, new Adventurer(1, "a"));
        map.put(2, new Adventurer(2, "b"));
        map.put(3, new Adventurer(3, "c"));
        Equipment equipment = new Equipment(3, "ee", 10, 5,
                "RegularEquipment");
        map.get(1).getEquipments().put(3, equipment);
        map.get(1).carryEquipment(3);

        equipment = new Equipment(4, "ff", 30, 5,
                "CritEquipment");
        equipment.setCritical(10);
        map.get(2).getEquipments().put(4, equipment);
        map.get(2).carryEquipment(4);
        map.get(2).carryEquipment(1144);
        map.get(2).carryBottle(11445144);

        equipment = new Equipment(7, "gg", 30, 5,
                "EpicEquipment");
        equipment.setRatio(0.5);
        map.get(3).getEquipments().put(7, equipment);
        map.get(3).carryEquipment(7);
        map.get(3).carryEquipment(514);

        String[] info = {"14" ,"3", "7", "a", "b", "c"};
        f.initParticipants(new ArrayList<>(Arrays.asList(info)), map);

        map.get(3).getBottles().put(98, new Bottle(98, "bb", 10, 100,
                "RegularBottle"));
        map.get(3).carryBottle(98);
        String[] s = {"2022/09-a@b-ee", "2022/09-a@#-ee", "2022/09-a-bB",
                "2022/09-a@b-eE", "2022/09-a@#-eE", "2022/09-b@a-ff",
                "2022/09-c-bb", "2022/09-c@#-gg", "2022/09-a@d-ee",
                "2022/09-d-kk"};
        for (int i = 0; i < 10; i++) {
            f.executeLog(s[i]);
        }
        assertEquals(f.getContainer().searchFucked("c"), 1);
        assertEquals(f.getContainer().searchFucked("b"), 3);
        assertEquals(f.getContainer().searchFucked("j"), 0);
    }
}