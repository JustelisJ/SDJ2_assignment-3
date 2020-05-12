package factory;

public class GoldCoin extends Valuable {

    @Override
    public String getName() {
        return "Gold coin";
    }

    public static int  getValue() {
        return 1;
    }
}
