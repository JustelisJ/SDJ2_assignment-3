import Treasury.ReadWrite;
import Treasury.TreasureRoom;

import java.util.Random;

public class King implements Runnable{

    private ReadWrite monitor;
    private TreasureRoom treasureRoom;
    private Catalog logger;

    private int partyValue;

    public King(ReadWrite monitor, TreasureRoom treasureRoom)
    {
        this.monitor = monitor;
        this.treasureRoom = treasureRoom;
        partyValue = 0;
        logger = Catalog.getInstance();
    }

    @Override
    public void run() {
        while(true)
        {
            partyValue = generateARandomNumber();
            monitor.kingAcquireWrite();
            logger.log(treasureRoom.removeValuables(partyValue));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monitor.releaseWrite();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int generateARandomNumber()
    {
        Random random = new Random();
        int number = random.nextInt(150) + 51;
        return number;
    }
}
