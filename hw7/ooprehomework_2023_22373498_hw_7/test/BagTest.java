import org.junit.Test;

import static org.junit.Assert.*;

public class BagTest {

    @Test
    public void useBottle() {
        Bag bag = new Bag(100);
        Bottle bottle = new Bottle(1, "a", 10, 1, "RegularBottle");
        bag.getBottleBag().put(1, bottle);

        bottle = new Bottle(2, "b", 20, 1, "ReinforcedBottle");
        bottle.setRatio(0.5);
        bag.getBottleBag().put(2, bottle);

        bottle = new Bottle(3, "c", 40, 1, "RecoverBottle");
        bottle.setRatio(0.2);
        bag.getBottleBag().put(3, bottle);
        assertEquals(bag.useBottle(1, 500), 10);
        assertEquals(bag.useBottle(2, 500), 30);
        assertEquals(bag.useBottle(3, 500), 100);
        assertEquals(bag.useBottle(4, 500), -1);
    }


    @Test
    public void useFood() {
        Bag bag = new Bag(100);
        for (int i = 0; i < 50; i++) {
            Food food = new Food(i, "b", i, i);
            bag.getFoodBag().put(i, food);
        }
        int a = bag.useFood(1);
        int b = bag.useFood(90);
        assertTrue(a == 1 && b == 0);
    }

}