import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TokenBucketCache {
    ConcurrentMap<Long, TokenBucket> map;
    List<TokenBucket> bucketList;

    TokenBucketCache(int size) {
        map = new ConcurrentHashMap<>(size);
        bucketList = new ArrayList<>();
    }

    public TokenBucket getBucket(ClientIdentifierBuilder identifier) {
        long id = identifier.getIdentifier();
        TokenBucket bucket = map.get(id);
        System.out.println(bucket.getRule());
        return bucket;
    }

    public void addBucket(TokenBucket bucket) {
        long idx = bucketList.size();
        bucketList.add(bucket);
        map.put(idx, bucket);
    }
}
