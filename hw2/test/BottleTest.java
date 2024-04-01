import org.junit.Test;
public class BottleTest {

    @Test
    public void getId() {
        Bottle b1 = new Bottle(11, "book", 19);
        assert (b1.getId() == 11);
    }

    @Test
    public void getName() {
        Bottle b1 = new Bottle(11, "book", 19);
        assert (b1.getName().equals("book"));
    }

    @Test
    public void getCapacity() {
        Bottle b1 = new Bottle(11, "book", 19);
        assert (b1.getCapacity() == 19);
    }
}