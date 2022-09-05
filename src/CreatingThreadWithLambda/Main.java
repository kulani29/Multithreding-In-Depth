package CreatingThreadWithLambda;

public class Main {

    public static void main(String[] args) {
        Runnable r = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hello there from Runner Class " + i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        r.run();
    }
}
