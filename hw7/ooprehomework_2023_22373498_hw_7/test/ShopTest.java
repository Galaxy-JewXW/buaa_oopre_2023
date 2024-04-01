import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ShopTest {

    @Test
    public void sell() {
        Adventurer a = new Adventurer(1, "a");
        a.addMoney(114514);
        a.getBottles().put(2, new Bottle(2, "b", 10,
                10, "RegularBottle"));
        a.getEquipments().put(3, new Equipment(3, "e", 3, 9,
                "RegularEquipment"));
        a.getFoods().put(4, new Food(4, "f", 12, 23));
        Shop shop = new Shop();
        shop.buyB(a.deleteBottle(2));
        shop.buyE(a.deleteEquipment(3));
        shop.buyF(a.deleteFood(4));
        String[] s = {"23", "1", "21", "b1", "RegularBottle"};
        shop.sell(a, new ArrayList<>(Arrays.asList(s)));
        assertEquals(a.getMoney(), 114546);
        s = new String[]{"23", "1", "22", "b2", "RecoverBottle", "0.76"};
        shop.sell(a, new ArrayList<>(Arrays.asList(s)));
        assertEquals(a.getMoney(), 114536);
        s = new String[]{"23", "1", "31", "e1", "RegularEquipment"};
        shop.sell(a, new ArrayList<>(Arrays.asList(s)));
        assertEquals(a.getMoney(), 114536 - 9);
        s = new String[]{"23", "1", "31", "f1", "Food"};
        shop.sell(a, new ArrayList<>(Arrays.asList(s)));
        assertEquals(a.getMoney(), 114536 - 9 - 23);
    }
}