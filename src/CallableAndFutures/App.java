package CallableAndFutures;

import java.util.Random;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Thread started...");
                Random random = new Random();
                int duration = random.nextInt(4000);
                Thread.sleep(duration);
                System.out.println("Thread finished..");
                return duration;
            }
        });
        service.shutdown();
        try {
            System.out.println("Result is : " + future.get());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }

    }
}
