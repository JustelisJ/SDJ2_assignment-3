package Treasury;

import factory.Diamond;
import factory.GoldCoin;
import factory.Ruby;

public class TreasureRoom {

    private int goldCoins;
    private int rubies;
    private int diamonds;

    public TreasureRoom()
    {
        goldCoins = 0;
        rubies = 0;
        diamonds = 0;
    }


    //For the tax collector
    public void addGoldCoin()
    {
        goldCoins++;
    }

    public void addRuby()
    {
        rubies++;
    }

    public void addDiamond()
    {
        diamonds++;
    }


    ////accountant
    public int countValuables()
    {
        int totalSum = 0;
        for(int i = 0; i < goldCoins; i++)
        {
            totalSum += GoldCoin.getValue();
        }
        for(int i = 0; i < rubies; i++)
        {
            totalSum += Ruby.getValue();
        }
        for(int i = 0; i < diamonds; i++)
        {
            totalSum += Diamond.getValue();
        }
        return totalSum;
    }



    //king
    public String removeValuables(int amount)
    {
        int fee = amount;
        if(enoughMoneyInTheTreasury(amount))
        {
            while(true)
            {
                if(fee > Diamond.getValue() && diamonds != 0)   //Uses diamonds to pay
                {
                    diamonds--;
                    fee -= Diamond.getValue();
                }
                else
                {
                    break;
                }
            }
            while(true)
            {
                if(fee > Ruby.getValue() && rubies != 0)   //Uses Rubies to pay
                {
                    rubies--;
                    fee -= Ruby.getValue();
                }
                else
                {
                    break;
                }
            }
            while(true)
            {
                if(fee > GoldCoin.getValue() && goldCoins != 0)   //Uses coins to pay
                {
                    goldCoins--;
                    fee -= GoldCoin.getValue();
                }
                else
                {
                    break;
                }
            }
            return "There will be a party!!! It costs " + amount;
        }
        else {
            return "Not enough money in the treasury";
        }
    }

    private boolean enoughMoneyInTheTreasury(int amount)
    {
        int treasury = goldCoins * GoldCoin.getValue() + rubies * Ruby.getValue() + diamonds * Diamond.getValue();
        if(treasury >= amount)
            return true;
        else
            return false;
    }
}
