package Treasury;

public interface ReadWrite {

    void accountantAcquireRead();
    void releaseRead();
    void taxCollectorAcquireWrite();
    void kingAcquireWrite();
    void releaseWrite();

}
