public class Rule {
    private long bucketSize;
    private long refillRate;

    private long id;

    Rule(long size, long rate, long i) {
        bucketSize = size;
        refillRate = rate;
        id = i;
    }

    public long getId() {
        return id;
    }

    public long getBucketSize() {
        return bucketSize;
    }

    public long getRefillRate() {
        return refillRate;
    }

    @Override
    public String toString() {
        return ConsoleColors.BLUE + "Rule{" +
                "bucketSize=" + bucketSize +
                ", refillRate=" + refillRate +
                ", id=" + id +
                '}' + ConsoleColors.RESET;
    }
}
