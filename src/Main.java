import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var sol = new Main();
        sol.bar();
    }

    public void bar() throws InterruptedException {
        long sleepTime = 2;
        RetrieveJobScheduler scheduler = new RetrieveJobScheduler();
        TokenBucketCache bucketCache = new TokenBucketCache(5);

        RetrieveRulesTask task = new RetrieveRulesTask(bucketCache);
        scheduler.start(task);

        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.printf("Cores: %d\n", coreCount);
        ExecutorService poolService = Executors.newFixedThreadPool(coreCount);
        int i = 0;
        do {
            poolService.execute(new FakeRequestTask(bucketCache, i));
            Thread.sleep(sleepTime);
            i++;
        }
        while (true);

    }

    class FakeRequestTask implements Runnable {

        TokenBucketCache bucketCache;
        int idx;

        FakeRequestTask(TokenBucketCache bucketCache, int idx) {
            this.bucketCache = bucketCache;
            this.idx = idx;
        }

        @Override
        public void run() {
            FakeRequest fakeRequest = new FakeRequest();
            TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(fakeRequest, bucketCache);

            System.out.printf(ConsoleColors.BLUE + "%d : Requesting tokens from -> %s\n" + ConsoleColors.RESET, idx, Thread.currentThread().getName());

            if (rateLimiter.allowRequest()) {
                System.out.println(ConsoleColors.GREEN + "Passed: Ok" + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + "Failed: Error" + ConsoleColors.RESET);
            }
        }
    }
}