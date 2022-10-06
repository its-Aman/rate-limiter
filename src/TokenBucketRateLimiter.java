public class TokenBucketRateLimiter {
    FakeRequest fakeRequest;
    TokenBucketCache bucketCache;

    TokenBucketRateLimiter(FakeRequest fakeRequest, TokenBucketCache bucketCache) {
        this.fakeRequest = fakeRequest;
        this.bucketCache = bucketCache;
    }

    public boolean allowRequest() {
        System.out.println(fakeRequest);

        ClientIdentifierBuilder identifier = new ClientIdentifierBuilder(fakeRequest);
        TokenBucket bucket = bucketCache.getBucket(identifier);

        return bucket.allowRequest(fakeRequest.getWeight());
    }
}
