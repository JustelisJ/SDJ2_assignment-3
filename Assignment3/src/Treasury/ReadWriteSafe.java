package Treasury;

public class ReadWriteSafe implements ReadWrite {

    private int readers;
    private int writers;
    private int waitingWriters;

    public ReadWriteSafe()
    {
        readers = 0;
        writers = 0;
        waitingWriters = 0;
    }

    @Override
    public synchronized void accountantAcquireRead()
    {
        while(writers > 0 || waitingWriters > 0)
        {
            try {
                wait();
                System.out.println("Accountant waiting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readers++;

    }

    @Override
    public synchronized void releaseRead()
    {
        readers--;
        if(readers == 0)
        {
            notify();
        }
    }

    @Override
    public synchronized void taxCollectorAcquireWrite()
    {
        waitingWriters++;
        while(readers > 0 || writers > 0)
        {
            try {
                wait();
                System.out.println("Collector waiting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitingWriters--;
        writers++;
    }

    @Override
    public synchronized void kingAcquireWrite() {
        waitingWriters++;
        while(readers > 0 || writers > 0)
        {
            try {
                wait();
                System.out.println("King waiting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitingWriters--;
//        writers++;
    }

    @Override
    public synchronized void releaseWrite() {
        writers--;
        notifyAll();
    }
}
