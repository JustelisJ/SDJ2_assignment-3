package factory;

public class Ruby extends Valuable {
    @Override
    public String getName() {
        return "Ruby";
    }

    public static int getValue() {
        return 10;
    }
}
