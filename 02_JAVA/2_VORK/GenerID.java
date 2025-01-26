import java.util.concurrent.atomic.AtomicLong;

public class GenerID {
    public static void main(String[] args) {
        int id=0;
        AtomicLong counter = new AtomicLong();
        long numericID = counter.getAndIncrement();
       // String uniqueNumericID = String.valueOf(numericID);
    }


   // return id = uniqueNumericID;
}
