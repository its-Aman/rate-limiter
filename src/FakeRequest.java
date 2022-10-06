import java.util.Random;

public class FakeRequest {
    private long identifier;
    private int weight;

    FakeRequest() {
        identifier = new Random().nextLong(5);
        weight = new Random().nextInt(5);
    }

    public long getIdentifier() {
        return identifier;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return ConsoleColors.BLUE + "FakeRequest{" +
                "identifier=" + identifier +
                ", weight=" + weight +
                '}' + ConsoleColors.RESET;
    }
}
