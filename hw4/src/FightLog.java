public class FightLog {
    private String date;
    private int type; // 1: bottle 2: 1v1 3: AOE
    private String bottleName;
    private String equipmentName;
    private String advName1;
    private String advName2;
    private boolean aoe;

    public FightLog(String date, int type) {
        this.date = date;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getBottleName() {
        return bottleName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getDate() {
        return date;
    }

    public String getAdvName1() {
        return advName1;
    }

    public String getAdvName2() {
        return advName2;
    }

    public boolean isEveryone() {
        return aoe;
    }

    public void useBottle(String name, String bottleName) {
        if (type != 1) {
            return;
        }
        this.bottleName = bottleName;
        equipmentName = null;
        advName1 = name;
        advName2 = null;
        aoe = false;
    }

    public void oneVsOne(String name1, String name2, String equipmentName) {
        if (type != 2) {
            return;
        }
        bottleName = null;
        advName1 = name1;
        advName2 = name2;
        this.equipmentName = equipmentName;
        aoe = false;
    }

    public void attackEveryone(String name, String equipmentName) {
        if (type != 3) {
            return;
        }
        bottleName = null;
        advName1 = name;
        advName2 = null;
        this.equipmentName = equipmentName;
        aoe = true;
    }

    public void showLog() {
        switch (type) {
            case 1: {
                System.out.println(date + " " + advName1 + " used " + bottleName);
                break;
            }
            case 2: {
                System.out.println(date + " " + advName1 + " attacked " + advName2 + " with " +
                        equipmentName);
                break;
            }
            case 3: {
                System.out.println(date + " " + advName1 + " AOE-attacked with " +
                        equipmentName);
                break;
            }
            default: {
                System.out.println("show log error");
            }
        }
    }
}
