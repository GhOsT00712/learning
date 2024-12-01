package RateLimiter;

public class Configuration {
    private String apiKey;
    private int rateLimit;
    private int windowSize;

    public Configuration(String apiKey, int rateLimit, int windowSize) {
        this.apiKey = apiKey;
        this.rateLimit = rateLimit;
        this.windowSize = windowSize;
    }

    public int getRateLimit() {
        return rateLimit;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public String getApiKey() {
        return apiKey;
    }
}
