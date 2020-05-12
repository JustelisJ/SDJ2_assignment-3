import Treasury.ReadWrite;
import Treasury.TreasureRoom;
import factory.*;

import java.util.Random;

public class TaxCollector implements Runnable {

    private int targetValue;
    private int actualValue;

    private ReadWrite monitor;
    private TreasureRoom treasureRoom;

    private int goldCoins;
    private int rubies;
    private int diamonds;

    private Catalog logger;

    public TaxCollector(ReadWrite monitor, TreasureRoom treasureRoom) {
        targetValue = 0;
        actualValue = 0;

        this.monitor = monitor;
        this.treasureRoom = treasureRoom;

        goldCoins = 0;
        rubies = 0;
        diamonds = 0;

        logger = Catalog.getInstance();
    }

    @Override
    public void run() {
        while (true) {
            targetValue = generateARandomNumber();
            while (actualValue < targetValue) {
                getARandomValuable();
                System.out.println("Collector got " + actualValue + " and needs " + (targetValue - actualValue));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            monitor.taxCollectorAcquireWrite();
            logger.log("Tax collector started adding valuables to the treasury\n");
            for (int i = 0; i < goldCoins; i++) {
                treasureRoom.addGoldCoin();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < rubies; i++) {
                treasureRoom.addRuby();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < diamonds; i++) {
                treasureRoom.addDiamond();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.log("Tax collector upped the treasury by " + actualValue+"\n");
            monitor.releaseWrite();
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int generateARandomNumber() {
        Random random = new Random();
        int number = random.nextInt(150) + 51;
        return number;
    }

    private void getARandomValuable() {
        Random random = new Random();
        int number = random.nextInt(3);

        switch (number) {
            case (0):    //make diamond
            {
                ValuableFactory.getValuable("Diamond");
                diamonds++;
                actualValue += Diamond.getValue();
            }
            case (1):    //make ruby
            {
                ValuableFactory.getValuable("Ruby");
                rubies++;
                actualValue += Ruby.getValue();
            }
            case (2):    //make gold coin
            {
                ValuableFactory.getValuable("Gold coin");
                goldCoins++;
                actualValue += GoldCoin.getValue();
            }
        }
    }
}
