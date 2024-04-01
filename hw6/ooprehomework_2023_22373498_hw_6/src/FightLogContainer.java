import java.util.ArrayList;

public class FightLogContainer {
    private ArrayList<FightLog> logs;

    public FightLogContainer() {
        logs = new ArrayList<FightLog>();
    }

    public ArrayList<FightLog> getLogs() {
        return logs;
    }

    public void addBottle(String date, String advName, String bottleName) {
        FightLog newLog = new FightLog(date, 1);
        newLog.useBottle(advName, bottleName);
        logs.add(newLog);
    }

    public void addOneVsOne(String date, String advName1, String advName2, String equipmentName) {
        FightLog newLog = new FightLog(date, 2);
        newLog.oneVsOne(advName1, advName2, equipmentName);
        logs.add(newLog);
    }

    public void addAttackEveryone(String date, String advName, String equipmentName) {
        FightLog newLog = new FightLog(date, 3);
        newLog.attackEveryone(advName, equipmentName);
        logs.add(newLog);
    }

    public void addAttackedByAoe(String date, String name1, String name2, String equipmentName) {
        FightLog newLog = new FightLog(date, 4);
        newLog.attackedByAoe(name1, name2, equipmentName);
        logs.add(newLog);
    }

    public int searchDate(String date) {
        boolean isEmpty = true;
        int cnt = 0;
        for (FightLog log : logs) {
            if (log.getDate().equals(date) && log.getType() != 4) {
                log.showLog();
                isEmpty = false;
                cnt++;
            }
        }
        if (isEmpty) {
            System.out.println("No Matched Log");
        }
        return cnt;
    }

    public int searchAttacker(String attacker) {
        boolean isEmpty = true;
        int cnt = 0;
        for (FightLog log : logs) {
            if (log.getAdvName1().equals(attacker) &&
                    (log.getType() == 2 || log.getType() == 3)) {
                log.showLog();
                isEmpty = false;
                cnt++;
            }
        }
        if (isEmpty) {
            System.out.println("No Matched Log");
        }
        return cnt;
    }

    public int searchFucked(String fucked) {
        boolean isEmpty = true;
        int cnt = 0;
        for (FightLog log : logs) {
            if (log.getAdvName2() == null) {
                continue;
            } else if (log.getAdvName2().equals(fucked) &&
                    (log.getType() == 2 || log.getType() == 4)) {
                log.showLog();
                isEmpty = false;
                cnt++;
            }
        }
        if (isEmpty) {
            System.out.println("No Matched Log");
        }
        return cnt;
    }
}