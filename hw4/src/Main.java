import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> inputInfo = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        HashMap<Integer, Adventurer> mainMap = new HashMap<>();
        Adventurer tempAdventurer;
        FightExecutor fightExecutor = new FightExecutor(scanner);
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
                    int capacity = Integer.parseInt(l.get(4));
                    mainMap.get(advId).addBottle(Integer.parseInt(l.get(2)), l.get(3), capacity);
                } else if (op == 3) { //删除某个冒险者的某个药水瓶
                    mainMap.get(advId).deleteBottle(Integer.parseInt(l.get(2)));
                } else if (op == 4) { //给某个冒险者增加一个装备
                    int star = Integer.parseInt(l.get(4));
                    mainMap.get(advId).addEquipment(Integer.parseInt(l.get(2)), l.get(3), star);
                } else if (op == 5) { //删除某个冒险者的某个装备
                    mainMap.get(advId).deleteEquipment(Integer.parseInt(l.get(2)));
                } else if (op == 6) { //给某个冒险者的某个装备提升一个星级
                    mainMap.get(advId).addOneStar(Integer.parseInt(l.get(2)));
                } else if (op == 7) { //给冒险者增加一个食物
                    int energy = Integer.parseInt(l.get(4));
                    mainMap.get(advId).addFood(Integer.parseInt(l.get(2)), l.get(3), energy);
                } else if (op == 8) { //删除冒险者的一个食物
                    mainMap.get(advId).deleteFood(Integer.parseInt(l.get(2)));
                } else if (op == 9) { //冒险者尝试携带他拥有的某件装备
                    mainMap.get(advId).carryEquipment(Integer.parseInt(l.get(2)));
                } else if (op == 10) { //冒险者尝试携带他拥有的某个药水瓶
                    mainMap.get(advId).carryBottle(Integer.parseInt(l.get(2)));
                } else if (op == 11) { //冒险者尝试携带他拥有的某个食物
                    mainMap.get(advId).carryFood(Integer.parseInt(l.get(2)));
                } else if (op == 12) { //冒险者使用某个药水瓶
                    mainMap.get(advId).useBottle(l.get(2));
                } else if (op == 13) { //冒险者使用某个食物
                    mainMap.get(advId).useFood(l.get(2));
                } else if (op == 14) {
                    fightExecutor.initParticipants(l.subList(1, 6), mainMap);
                    fightExecutor.executeLog();
                } else if (op == 16) {
                    fightExecutor.getContainer().searchAttacker(mainMap.get(advId).getName());
                } else if (op == 17) {
                    fightExecutor.getContainer().searchFucked(mainMap.get(advId).getName());
                }
            } else {
                fightExecutor.getContainer().searchDate(l.get(1));
            }
        }
    }
}

