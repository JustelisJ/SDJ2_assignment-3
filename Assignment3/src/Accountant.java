import Treasury.ReadWrite;
import Treasury.TreasureRoom;

public class Accountant implements Runnable {

    private ReadWrite monitor;
    private TreasureRoom treasureRoom;

    private Catalog logger;

    public Accountant(ReadWrite monitor, TreasureRoom treasureRoom)
    {
        this.monitor = monitor;
        this.treasureRoom = treasureRoom;
        logger = Catalog.getInstance();
    }

    @Override
    public void run() {
        while (true)
        {
            monitor.accountantAcquireRead();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logger.log("Treasure room value - " + treasureRoom.countValuables() +"\n");

            monitor.releaseRead();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
