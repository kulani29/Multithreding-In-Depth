package Semaphore;

import java.util.concurrent.Semaphore;

public class Connection {
    private static Connection instance = new Connection();
    private Semaphore semaphore = new Semaphore(10, true);

    private int connections = 0;

    private Connection() {

    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            doConnect();
        } finally {
            semaphore.release(); // This will make sure that semaphore release the lock even in case of
            // exception
        }
    }

    public void doConnect() {

        synchronized (this) {
            connections++;
            System.out.println("Current Connections :" + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (this) {
            connections--;
        }
    }
}
