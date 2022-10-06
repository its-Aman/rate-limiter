import java.util.ArrayList;
import java.util.List;

public class RetrieveRulesTask implements Runnable {
    private TokenBucketCache bucketCache;

    RetrieveRulesTask(TokenBucketCache bucketCache) {
        this.bucketCache = bucketCache;
        createSomeFakeBuckets();
    }

    @Override
    public void run() {
        System.out.println(ConsoleColors.CYAN + "fetching new Rules.." + ConsoleColors.RESET);
    }

    private void createSomeFakeBuckets() {
        List<Rule> rules = new ArrayList<>();

        rules.add(new Rule(10, 10, rules.size()));
        rules.add(new Rule(100, 5, rules.size()));
        rules.add(new Rule(100, 1000, rules.size()));
        rules.add(new Rule(5, 100, rules.size()));
        rules.add(new Rule(10, 1000, rules.size()));

        for (Rule rule : rules)
            bucketCache.addBucket(new TokenBucket(rule));
    }

}
