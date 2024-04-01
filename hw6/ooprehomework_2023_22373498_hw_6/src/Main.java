import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static final HashMap<Integer, Adventurer> mainMap = new HashMap<>();
    private static Adventurer tempAdventurer;
    private static final FightExecutor fightExecutor = new FightExecutor();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < n; i++) {
            String nextLine = scanner.nextLine();
            ArrayList<String> l = new ArrayList<>(Arrays.asList(nextLine.trim().split(" +")));
            int op = Integer.parseInt(l.get(0));
            if (op != 15) {
                int advId = Integer.parseInt(l.get(1));
                if (op == 1) { //加入一个需要管理的冒险者（新加入的冒险者不携带任何药水瓶和装备）
                    tempAdventurer = new Adventurer(advId, l.get(2));
                    mainMap.put(advId, tempAdventurer);
                } else if (op == 2) { //给某个冒险者增加一个药水瓶
                    createObj(l, true);
                } else if (op == 3) { //删除某个冒险者的某个药水瓶
                    mainMap.get(advId).deleteBottle(Integer.parseInt(l.get(2)));
                } else if (op == 4) { //给某个冒险者增加一个装备
                    createObj(l, false);
                } else if (op == 5) { //删除某个冒险者的某个装备
                    mainMap.get(advId).deleteEquipment(Integer.parseInt(l.get(2)));
                } else if (op == 6) { //给某个冒险者的某个装备提升一个星级
                    mainMap.get(advId).addOneStar(Integer.parseInt(l.get(2)));
                } else if (op == 7) { //给冒险者增加一个食物
                    mainMap.get(advId).addFood(Integer.parseInt(l.get(2)), l.get(3),
                            Integer.parseInt(l.get(4)), Long.parseLong(l.get(5)));
                } else if (op == 8) { //删除冒险者的一个食物
                    mainMap.get(advId).deleteFood(Integer.parseInt(l.get(2)));
                } else if (op == 9) { //冒险者尝试携带他拥有的某件装备
                    mainMap.get(advId).carryEquipment(Integer.parseInt(l.get(2)));
                } else if (op == 10) { //冒险者尝试携带他拥有的某个药水瓶
                    mainMap.get(advId).carryBottle(Integer.parseInt(l.get(2)));
                } else if (op == 11) { //冒险者尝试携带他拥有的某个食物
                    mainMap.get(advId).carryFood(Integer.parseInt(l.get(2)));
                } else if (op == 12) { //冒险者使用某个药水瓶
                    mainMap.get(advId).useBottle(l.get(2), false);
                } else if (op == 13) { //冒险者使用某个食物
                    mainMap.get(advId).useFood(l.get(2), false);
                } else if (op == 14) {
                    fightExecutor.initParticipants(l, mainMap);
                    for (int j = 0; j < fightExecutor.getLogAmount(); j++) {
                        String s = scanner.nextLine();
                        fightExecutor.executeLog(s);
                    }
                } else if (op == 16) {
                    fightExecutor.getContainer().searchAttacker(mainMap.get(advId).getName());
                } else if (op == 17) {
                    fightExecutor.getContainer().searchFucked(mainMap.get(advId).getName());
                } else if (op == 18) {
                    mainMap.get(advId).hire(mainMap, Integer.parseInt(l.get(2)));
                } else if (op == 19) {
                    mainMap.get(advId).sumValue(true);
                } else if (op == 20) {
                    mainMap.get(advId).maxValue(true);
                } else if (op == 21) {
                    mainMap.get(advId).checkName(Integer.parseInt(l.get(2)));
                }
            } else {
                fightExecutor.getContainer().searchDate(l.get(1));
            }
        }
    }

    public static void createObj(ArrayList<String> l, boolean isBottle) {
        int advId = Integer.parseInt(l.get(1));
        Adventurer adventurer = mainMap.get(advId);
        int id = Integer.parseInt(l.get(2));
        String name = l.get(3);
        int arg1 = Integer.parseInt(l.get(4));
        long arg2 = Long.parseLong(l.get(5));
        String typeString = l.get(6);
        if (isBottle) {
            Bottle bottle = new Bottle(id, name, arg1, arg2, typeString);
            if (bottle.getType() == 2 || bottle.getType() == 3) {
                double ratio = Double.parseDouble(l.get(7));
                bottle.setRatio(ratio);
            }
            adventurer.getBottles().put(bottle.getId(), bottle);
        } else {
            Equipment equipment = new Equipment(id, name, arg1, arg2, typeString);
            if (equipment.getType() == 2) {
                int critical = Integer.parseInt(l.get(7));
                equipment.setCritical(critical);
            } else if (equipment.getType() == 3) {
                double ratio = Double.parseDouble(l.get(7));
                equipment.setRatio(ratio);
            }
            adventurer.getEquipments().put(equipment.getId(), equipment);
        }
    }
}

