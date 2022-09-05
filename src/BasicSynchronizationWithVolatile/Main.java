package BasicSynchronizationWithVolatile;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Runner r = new Runner();
        r.start();

        System.out.println("Enter key to stop the process....");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        r.shutdown();
    }
}
