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
            switch (Integer.parseInt(lines.get(0))) {
                case 1: { //加入一个需要管理的冒险者（新加入的冒险者不携带任何药水瓶和装备）
                    String name = lines.get(2);
                    tempAdventurer = new Adventurer(advId, name);
                    adventurerMap.put(advId, tempAdventurer);
                    break;
                }
                case 2: { //给某个冒险者增加一个药水瓶
                    int botId = Integer.parseInt(lines.get(2));
                    String name = lines.get(3);
                    int capacity = Integer.parseInt(lines.get(4));
                    adventurerMap.get(advId).addBottle(botId, name, capacity);
                    break;
                }
                case 3: { //删除某个冒险者的某个药水瓶
                    int botId = Integer.parseInt(lines.get(2));
                    adventurerMap.get(advId).deleteBottle(botId);
                    break;
                }
                case 4: { //给某个冒险者增加一个装备
                    int equId = Integer.parseInt(lines.get(2));
                    String name = lines.get(3);
                    int star = Integer.parseInt(lines.get(4));
                    adventurerMap.get(advId).addEquipment(equId, name, star);
                    break;
                }
                case 5: { //删除某个冒险者的某个装备
                    int equId = Integer.parseInt(lines.get(2));
                    adventurerMap.get(advId).deleteEquipment(equId);
                    break;
                }
                case 6: { //给某个冒险者的某个装备提升一个星级
                    int equId = Integer.parseInt(lines.get(2));
                    adventurerMap.get(advId).addEquipmentStar(equId);
                    break;
                }
                default:
                    break;
            }
        }
        scanner.close();
    }
}
