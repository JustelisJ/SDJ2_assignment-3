package factory;

import java.util.HashMap;

public class ValuableFactory {

    static HashMap<String, Valuable> valuables = new HashMap<>();

    public static Valuable getValuable(String valuableType)
    {
        Valuable valuable = valuables.get(valuableType);
        if(valuable == null)
        {
            switch (valuableType)
            {
                case "Diamond":
                {
                    valuable = new Diamond();
                    break;
                }
                case "Gold coin":
                {
                    valuable = new GoldCoin();
                    break;
                }
                case "Ruby":
                {
                    valuable = new Ruby();
                    break;
                }
            }
            valuables.put(valuableType, valuable);
        }
        return valuable;
    }

}
