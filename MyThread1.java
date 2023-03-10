import java.io.*;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThread1 implements Runnable {
	private ExecutorService tpe;
	private ExecutorService tpe2;
	private AtomicInteger inQueue;
	private AtomicInteger inQueue2;
	private  int P;
	private BufferedReader reader1;
	private BufferedReader reader2;
	private BufferedWriter writer1;
	private BufferedWriter writer2;
	private File input1;
	private int N;

	public MyThread1(
			ExecutorService tpe,
			ExecutorService tpe2,
			AtomicInteger inQueue,
			AtomicInteger inQueue2,
			int P,
			BufferedReader reader1,
			BufferedReader reader2,
			BufferedWriter writer1,
			BufferedWriter writer2,
			File input1
	) {
		this.tpe = tpe;
		this.tpe2 = tpe2;
		this.inQueue = inQueue;
		this.inQueue2 = inQueue2;
		this.P = P;
		this.reader1 = reader1;
		this.reader2 = reader2;
		this.writer1 = writer1;
		this.writer2 = writer2;
		this.input1 = input1;
	}

	@Override
	public void run() {
		String line = null;
			try {
				line = reader1.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

		while (line != null) {
			String[] arrOfStr = line.split(",", 2);
			int N = Integer.valueOf(arrOfStr[1]);
			if ( N > 0) {
				// submit thread 2
				BufferedReader readerX = null;
				try {
					readerX = new BufferedReader(new FileReader(input1));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				inQueue2.incrementAndGet();
				tpe2.submit(new MyThread2(tpe2, inQueue2, P, line, readerX, writer2, writer1));
			}
			// read new order
			try {
				line = reader1.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int left = inQueue.decrementAndGet();
		if (left == 0) {
			tpe.shutdown();
		}
	}
}
