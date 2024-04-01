import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> inputInfo = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        HashMap<Integer, Adventurer> adventurerMap = new HashMap<>();
        Adventurer tempAdventurer;
        for (int i = 0; i < n; i++) {
            String nextLine = scanner.nextLine();
            String[] strings = nextLine.trim().split(" +");
            inputInfo.add(new ArrayList<>(Arrays.asList(strings)));
        }
        for (int i = 0; i < n; i++) {
            ArrayList<String> lines = inputInfo.get(i);
            int advId = Integer.parseInt(lines.get(1));
            int op = Integer.parseInt(lines.get(0));
            if (op == 1) { //加入一个需要管理的冒险者（新加入的冒险者不携带任何药水瓶和装备）
                String name = lines.get(2);
                tempAdventurer = new Adventurer(advId, name);
                adventurerMap.put(advId, tempAdventurer);
            } else if (op == 2) { //给某个冒险者增加一个药水瓶
                int botId = Integer.parseInt(lines.get(2));
                String name = lines.get(3);
                int capacity = Integer.parseInt(lines.get(4));
                adventurerMap.get(advId).addBottle(botId, name, capacity);
            } else if (op == 3) { //删除某个冒险者的某个药水瓶
                adventurerMap.get(advId).deleteBottle(Integer.parseInt(lines.get(2)));
            } else if (op == 4) { //给某个冒险者增加一个装备
                int equId = Integer.parseInt(lines.get(2));
                String name = lines.get(3);
                int star = Integer.parseInt(lines.get(4));
                adventurerMap.get(advId).addEquipment(equId, name, star);
            } else if (op == 5) { //删除某个冒险者的某个装备
                adventurerMap.get(advId).deleteEquipment(Integer.parseInt(lines.get(2)));
            } else if (op == 6) { //给某个冒险者的某个装备提升一个星级
                adventurerMap.get(advId).addOneStar(Integer.parseInt(lines.get(2)));
            } else if (op == 7) { //给冒险者增加一个食物
                int foodId = Integer.parseInt(lines.get(2));
                String name = lines.get(3);
                int energy = Integer.parseInt(lines.get(4));
                adventurerMap.get(advId).addFood(foodId, name, energy);
            } else if (op == 8) { //删除冒险者的一个食物
                adventurerMap.get(advId).deleteFood(Integer.parseInt(lines.get(2)));
            } else if (op == 9) { //冒险者尝试携带他拥有的某件装备
                adventurerMap.get(advId).carryEquipment(Integer.parseInt(lines.get(2)));
            } else if (op == 10) { //冒险者尝试携带他拥有的某个药水瓶
                adventurerMap.get(advId).carryBottle(Integer.parseInt(lines.get(2)));
            } else if (op == 11) { //冒险者尝试携带他拥有的某个食物
                adventurerMap.get(advId).carryFood(Integer.parseInt(lines.get(2)));
            } else if (op == 12) { //冒险者使用某个药水瓶
                adventurerMap.get(advId).useBottle(lines.get(2));
            } else if (op == 13) { //冒险者使用某个食物
                adventurerMap.get(advId).useFood(lines.get(2));
            }
        }
        scanner.close();
    }
}

