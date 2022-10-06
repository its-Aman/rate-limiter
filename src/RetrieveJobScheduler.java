import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RetrieveJobScheduler {
    private final ScheduledExecutorService schedular;

    public RetrieveJobScheduler() {
        schedular = new ScheduledThreadPoolExecutor(1);
    }

    public void start(RetrieveRulesTask task) {
        schedular.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }

    public void stop() {
        schedular.shutdown();
    }
}
