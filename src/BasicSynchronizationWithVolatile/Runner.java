package BasicSynchronizationWithVolatile;

public class Runner extends Thread {

    // Usages of Volatile guarantee that the running variable would not be cached by Thread created
    private volatile boolean running = true;


    public void run() {
        while (running) {
            System.out.println("Hello there from Runner Class " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutdown() {

        running = false;
    }
}
