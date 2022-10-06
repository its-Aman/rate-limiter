public class ClientIdentifierBuilder {
    FakeRequest fakeRequest;

    ClientIdentifierBuilder(FakeRequest fakeRequest) {
        this.fakeRequest = fakeRequest;
    }

    public long getIdentifier() {
        return fakeRequest.getIdentifier();
    }
}
