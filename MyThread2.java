import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThread2 implements Runnable {
    private  ExecutorService tpe;
    private AtomicInteger inQueue;
    private  int P;
    private String order;
    private BufferedReader reader2;
    private BufferedWriter writer2;
    private BufferedWriter writer1;
    private int N;

    MyThread2(
            ExecutorService tpe,
            AtomicInteger inQueue,
            int P,
            String order,
            BufferedReader reader2,
            BufferedWriter writer2,
            BufferedWriter writer1
    ) {
        this.tpe = tpe;
        this.inQueue = inQueue;
        this.P = P;
        this.order = order;
        this.reader2 = reader2;
        this.writer2 = writer2;
        this.writer1 = writer1;
    }
    @Override
    public void run() {
        String[] arrOfStr = order.split(",", 2);
        N = Integer.valueOf(arrOfStr[1]);
        String comanda = arrOfStr[0];

        String line = null;
        while (N > 0) {
            try {
                line = reader2.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line.contains(comanda)) {
                N--;
                try {
                    writer2.write(line + ",shipped\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer1.write(order + ",shipped\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        int left = inQueue.decrementAndGet();
        if (left == 0) {
            tpe.shutdown();
        }
    }
}
