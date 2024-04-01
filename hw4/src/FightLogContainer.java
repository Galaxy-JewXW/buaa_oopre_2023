import java.util.ArrayList;

public class FightLogContainer {
    private ArrayList<FightLog> logs;

    public FightLogContainer() {
        logs = new ArrayList<FightLog>();
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

    public void searchDate(String date) {
        boolean isEmpty = true;
        for (FightLog log : logs) {
            if (log.getDate().equals(date)) {
                log.showLog();
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println("No Matched Log");
        }
    }

    public void searchAttacker(String attacker) {
        boolean isEmpty = true;
        for (FightLog log : logs) {
            if (log.getAdvName1().equals(attacker) && log.getType() != 1) {
                log.showLog();
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println("No Matched Log");
        }
    }

    public void searchFucked(String fucked) {
        boolean isEmpty = true;
        for (FightLog log : logs) {
            if (log.getAdvName2().equals(fucked) && log.getType() == 2) {
                log.showLog();
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println("No Matched Log");
        }
    }
}