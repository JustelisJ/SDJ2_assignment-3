package factory;

public class Diamond extends Valuable {

    @Override
    public String getName() {
        return "Diamond";
    }

    public static int getValue() {
        return 25;
    }
}
