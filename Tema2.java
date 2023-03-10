import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Tema2 {
    public static int P;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        P = Integer.parseInt(args[1]);
        ExecutorService tpe = Executors.newFixedThreadPool(P);
        AtomicInteger inQueue = new AtomicInteger(0);
        AtomicInteger inQueue2 = new AtomicInteger(0);
        ExecutorService tpe2 = Executors.newFixedThreadPool(P);
        /*
        File director = new File(args[0]);
        File[] in = director.listFiles();
        BufferedReader reader1 = new BufferedReader(new FileReader( in[0]));
        BufferedReader reader2 = new BufferedReader(new FileReader( in[1]));

        File ordersOut = new File("orders_out.txt");
        File productsOut = new File("order_products_out.txt");
        ordersOut.createNewFile();
        productsOut.createNewFile();*/
        String comenzi = args[0] + "/orders.txt";
        String comenzi_out = "orders_out.txt";
        String produse = args[0] + "/order_products.txt";
        String produse_out = "order_products_out.txt";

        BufferedReader reader1 = new BufferedReader(new FileReader( comenzi));
        BufferedReader reader2 = new BufferedReader(new FileReader( produse));

        BufferedWriter writer1 = new BufferedWriter(new FileWriter(comenzi_out));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(produse_out));

        for (int i = 0; i < P; i++) {
            inQueue.incrementAndGet();
            tpe.submit(new MyThread1(tpe, tpe2, inQueue, inQueue2, P, reader1, reader2, writer1, writer2, new File(produse)));
        }

        while (!tpe.isShutdown() || !tpe2.isShutdown()) {
        }

        try {
            reader1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

