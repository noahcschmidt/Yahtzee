import java.util.Random;

public class Dice {
    private int TOTAL_FACES = 6;
    private int value = 1;
    private Random rand = new Random();

    public void roll() {
        value = rand.nextInt(TOTAL_FACES) + 1;
    }
    public int getValue() {
        return value;
    }
}
