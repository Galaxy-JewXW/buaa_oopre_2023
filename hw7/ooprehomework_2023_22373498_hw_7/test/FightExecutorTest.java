import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FightExecutorTest {

    @Test
    public void getContainer() {
        FightExecutor f = new FightExecutor();
        assertEquals(f.getContainer().getLogs().size(), 0);
    }

    @Test
    public void clearParticipants() {
        FightExecutor f = new FightExecutor();
        HashMap<Integer, Adventurer> map = new HashMap<>();
        map.put(1, new Adventurer(1, "a"));
        map.put(2, new Adventurer(2, "b"));
        map.put(3, new Adventurer(3, "c"));
        String[] info = {"14" ,"3", "7", "a", "b", "c"};
        f.initParticipants(new ArrayList<>(Arrays.asList(info)), map);
        int a = f.getParticipants().size();
        f.clear();
        int b = f.getParticipants().size();
        assertTrue(a == 3 && b == 0);
    }

    @Test
    public void getPersonAmount() {
        FightExecutor f = new FightExecutor();
        assertEquals(f.getPersonAmount(), 0);
    }

    @Test
    public void getLogAmount() {
        FightExecutor f = new FightExecutor();
        assertEquals(f.getLogAmount(), 0);
    }

    @Test
    public void initParticipants() {
        FightExecutor f = new FightExecutor();
        HashMap<Integer, Adventurer> map = new HashMap<>();
        map.put(1, new Adventurer(1, "a"));
        map.put(2, new Adventurer(2, "b"));
        map.put(3, new Adventurer(3, "c"));
        String[] info = {"14" ,"3", "7", "a", "b", "c"};
        f.initParticipants(new ArrayList<>(Arrays.asList(info)), map);
    }

    @Test
    public void searchAdvName() {
        FightExecutor f = new FightExecutor();
        HashMap<Integer, Adventurer> map = new HashMap<>();
        map.put(1, new Adventurer(1, "a"));
        map.put(2, new Adventurer(2, "b"));
        map.put(3, new Adventurer(3, "c"));
        Adventurer a = f.searchAdvName(map, "a");
        assertEquals(a.getId(), 1);
        a = f.searchAdvName(map, "d");
        assertNull(a);
    }

    @Test
    public void executeLog() {
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
        assertEquals(map.get(1).getHitPoint(), 230);
        assertEquals(map.get(2).getHitPoint(), 240);
        assertEquals(map.get(3).getHitPoint(), 500);
    }
}