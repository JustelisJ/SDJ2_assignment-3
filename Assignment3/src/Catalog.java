import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Catalog {

    private File logFile;
    private static Catalog instance;
    private static Lock lock = new ReentrantLock();

    private Catalog()
    {
        logFile = new File("LogFile.txt");
    }

    public static Catalog getInstance()
    {
        if(instance == null)
        {
            synchronized (lock)
            {
                if(instance == null)
                {
                    instance = new Catalog();
                }
            }
        }
        return instance;
    }

    public void log(String txt)
    {
        try {
            Writer out = new BufferedWriter(new FileWriter(logFile, true));
            out.append(txt);
            out.flush();
            out.close();
            System.out.println(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
