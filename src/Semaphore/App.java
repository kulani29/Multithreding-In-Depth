package Semaphore;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        semaphore.release(); // This will increment the available permit size

        semaphore.acquire(); // This will decrement the available permit size
        // If available permits are not present, and we call acquire() method then it waits till one of
        //the semaphore release the resource
        System.out.println("Available permits :" + semaphore.availablePermits());

        System.out.println("======================");

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);


    }
}
