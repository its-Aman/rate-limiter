public class TokenBucket {
    private Rule rule;
    private double currentBucketSize;
    private long lastRefillTimestamp;

    public TokenBucket(Rule rule) {
        this.rule = rule;
        currentBucketSize = rule.getBucketSize();
        lastRefillTimestamp = System.nanoTime();
    }

    public synchronized boolean allowRequest(int tokens) {
        refillBucket();

        if (currentBucketSize > tokens) {
            currentBucketSize -= tokens;
            System.out.println(ConsoleColors.BLUE + "Remaining tokens: " + currentBucketSize + ConsoleColors.RESET);
            return true;
        }

        System.out.println(ConsoleColors.YELLOW + "Remaining tokens: " + currentBucketSize + "\t Requesting token: " + tokens + ConsoleColors.RESET);
        return false;
    }

    private void refillBucket() {
        long now = System.nanoTime();
        double tokensToAdd = (now - lastRefillTimestamp) * rule.getRefillRate() / 1e9;
        currentBucketSize = Math.min(rule.getBucketSize(), currentBucketSize + tokensToAdd);
        lastRefillTimestamp = now;
    }

    public Rule getRule() {
        return rule;
    }
}
