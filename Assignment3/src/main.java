import Treasury.ReadWrite;
import Treasury.ReadWriteSafe;
import Treasury.TreasureRoom;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        ArrayList<Thread> accountants = new ArrayList<>();
        ArrayList<Thread> taxCollectors = new ArrayList<>();

        ReadWrite monitor = new ReadWriteSafe();
        TreasureRoom treasureRoom = new TreasureRoom();

        for(int i = 0; i < 5; i++)
        {
            Thread thread = new Thread(new Accountant(monitor, treasureRoom));
            accountants.add(thread);
            accountants.get(i).start();
        }
        for(int i = 0; i < 3; i++)
        {
            Thread thread = new Thread(new TaxCollector(monitor, treasureRoom));
            taxCollectors.add(thread);
            taxCollectors.get(i).start();
        }
        Thread king = new Thread(new King(monitor, treasureRoom));
        king.start();
    }

}
