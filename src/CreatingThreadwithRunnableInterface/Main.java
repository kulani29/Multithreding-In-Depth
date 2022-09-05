package CreatingThreadwithRunnableInterface;

import CreatingThreadwithThreadClass.Runner;

public class Main {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runner());
        thread1.start();

        Thread thread2 = new Thread(new Runner());
        thread2.start();

    }
}
