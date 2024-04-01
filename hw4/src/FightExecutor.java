import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FightExecutor {
    private FightLogContainer container;
    private HashMap<Integer, Adventurer> participants;
    private int personAmount;
    private int logAmount;
    private Scanner scanner;
    private String rule1 = "(\\d{1,4})/((1[0-2])|(0?[1-9]))-(\\w+)-(\\w+)";
    private String rule2 = "(\\d{1,4})/((1[0-2])|(0?[1-9]))-(\\w+)@(\\w+)-(\\w+)";
    private String rule3 = "(\\d{1,4})/((1[0-2])|(0?[1-9]))-(\\w+)@#-(\\w+)";
    private Pattern p1;
    private Pattern p2;
    private Pattern p3;

    public FightLogContainer getContainer() {
        return container;
    }

    public FightExecutor(Scanner scanner) {
        container = new FightLogContainer();
        participants = new HashMap<>();
        this.scanner = scanner;
        p1 = Pattern.compile(rule1);
        p2 = Pattern.compile(rule2);
        p3 = Pattern.compile(rule3);
    }

    public void initParticipants(List<String> infos, HashMap<Integer, Adventurer> map) {
        personAmount = Integer.parseInt(infos.get(0));
        logAmount = Integer.parseInt(infos.get(1));
        for (int i = 0; i < personAmount; i++) {
            String name = infos.get(i + 2);
            Adventurer temp = searchAdvName(map, name);
            participants.put(temp.getId(), temp);
        }
    }

    public Adventurer searchAdvName(HashMap<Integer, Adventurer> map, String name) {
        for (Map.Entry<Integer, Adventurer> entry : map.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void executeLog() {
        System.out.println("Enter Fight Mode");
        for (int i = 0; i < logAmount; i++)
        {
            String info = this.scanner.nextLine();
            Matcher m1 = p1.matcher(info);
            Matcher m2 = p2.matcher(info);
            Matcher m3 = p3.matcher(info);
            if (m3.find()) {
                String[] s = m3.group().split("-");
                String[] ss = s[1].split("@");
                Adventurer adv1 = searchAdvName(participants, ss[0]);
                if (adv1 == null) {
                    System.out.println("Fight log error");
                } else if (!adv1.getBag().isIn(s[2])) {
                    System.out.println("Fight log error");
                } else {
                    container.addAttackEveryone(s[0], ss[0], s[2]);
                    adv1.fuckEveryone(participants, s[2]);
                }
            } else if (m2.find()) {
                String[] s = m2.group().split("-");
                String[] ss = s[1].split("@");
                Adventurer adv1 = searchAdvName(participants, ss[0]);
                Adventurer adv2 = searchAdvName(participants, ss[1]);
                if (adv1 == null || adv2 == null) {
                    System.out.println("Fight log error");
                } else if (!adv1.getBag().isIn(s[2])) {
                    System.out.println("Fight log error");
                } else {
                    container.addOneVsOne(s[0], ss[0], ss[1], s[2]);
                    adv1.fuckYou(adv2, s[3]);
                    System.out.println(adv2.getId() + " " + adv2.getHitPoint());
                }
            } else if (m1.find()) {
                String[] s = m1.group().split("-");
                Adventurer adv1 = searchAdvName(participants, s[1]);
                if (adv1 == null) {
                    System.out.println("Fight log error");
                } else if (adv1.useBottle(s[2])) {
                    int bottleId = adv1.getBag().getBottleId(s[2]);
                    container.addBottle(s[0], s[1], s[2]);
                } else {
                    System.out.println("Fight log error");
                }
            }

        }
        participants.clear();
    }

}
