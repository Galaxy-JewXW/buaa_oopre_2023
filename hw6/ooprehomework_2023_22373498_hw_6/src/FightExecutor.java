import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FightExecutor {
    private FightLogContainer container;
    private LinkedHashMap<Integer, Adventurer> participants;
    private int personAmount;
    private int logAmount;
    private String rule1 = "(\\d{1,4})/((1[0-2])|(0?[1-9]))-(\\S+)-(\\S+)";
    private String rule2 = "(\\d{1,4})/((1[0-2])|(0?[1-9]))-(\\S+)@(\\S+)-(\\S+)";
    private String rule3 = "(\\d{1,4})/((1[0-2])|(0?[1-9]))-(\\S+)@#-(\\S+)";
    private Pattern p1;
    private Pattern p2;
    private Pattern p3;
    private int cnt;

    public FightLogContainer getContainer() {
        return container;
    }

    public LinkedHashMap<Integer, Adventurer> getParticipants() {
        return participants;
    }

    public void clearParticipants() {
        participants.clear();
    }

    public int getPersonAmount() {
        return personAmount;
    }

    public int getLogAmount() {
        return logAmount;
    }

    public FightExecutor() {
        container = new FightLogContainer();
        participants = new LinkedHashMap<>();
        p1 = Pattern.compile(rule1);
        p2 = Pattern.compile(rule2);
        p3 = Pattern.compile(rule3);
    }

    public void initParticipants(ArrayList<String> infos, HashMap<Integer, Adventurer> map) {
        this.clearParticipants();
        personAmount = Integer.parseInt(infos.get(1));
        logAmount = Integer.parseInt(infos.get(2));
        for (int i = 0; i < personAmount; i++) {
            String name = infos.get(i + 3);
            Adventurer temp = searchAdvName(map, name);
            participants.put(temp.getId(), temp);
        }
        System.out.println("Enter Fight Mode");
    }

    public Adventurer searchAdvName(HashMap<Integer, Adventurer> map, String name) {
        for (Map.Entry<Integer, Adventurer> entry : map.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void executeLog(String info) {
        Matcher m1 = p1.matcher(info);
        Matcher m2 = p2.matcher(info);
        Matcher m3 = p3.matcher(info);
        if (m3.find()) {
            String[] s = m3.group().split("-");
            String[] ss = s[1].split("@");
            Adventurer adv1 = searchAdvName(participants, ss[0]);
            if (adv1 == null || !adv1.getBag().isIn(s[2])) {
                System.out.println("Fight log error");
            } else {
                container.addAttackEveryone(s[0], ss[0], s[2]);
                adv1.fuckEveryone(participants, s[2], this.container, s[0]);
                System.out.print("\n");
            }
        } else if (m2.find()) {
            String[] s = m2.group().split("-");
            String[] ss = s[1].split("@");
            Adventurer adv1 = searchAdvName(participants, ss[0]);
            Adventurer adv2 = searchAdvName(participants, ss[1]);
            if (adv1 == null || adv2 == null || !adv1.getBag().isIn(s[2])) {
                System.out.println("Fight log error");
            } else {
                container.addOneVsOne(s[0], ss[0], ss[1], s[2]);
                adv1.fuckYou(adv2, s[2]);
                System.out.println(adv2.getId() + " " + adv2.getHitPoint());
            }
        } else if (m1.find()) {
            String[] s = m1.group().split("-");
            Adventurer adv1 = searchAdvName(participants, s[1]);
            if (adv1 == null) {
                System.out.println("Fight log error");
            } else if (adv1.useBottle(s[2], true)) {
                container.addBottle(s[0], s[1], s[2]);
            } else {
                System.out.println("Fight log error");
            }
        }
        cnt++;
    }

}
