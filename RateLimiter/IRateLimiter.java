package RateLimiter;

public interface IRateLimiter {
    public boolean allowRequest(String apiKey);
}

