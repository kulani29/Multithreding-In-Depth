package InterruptionThreads;

import java.util.Random;

public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting the thread...");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i = 0; i < 1E9; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted");
                        break;
                    }
                    Math.sin(random.nextDouble());
                }
            }
        });

        thread1.start();
        Thread.sleep(500);
        thread1.interrupt();
        thread1.join();

        System.out.println("Finishing the thread...");
    }
}
