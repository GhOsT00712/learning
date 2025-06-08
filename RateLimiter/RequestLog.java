package RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class RequestLog {
    private Map<Long,Integer> requestLog;

    public RequestLog() {
        requestLog = new HashMap<>();
    }

    public void addLog(long lastRequestTime, int requestCount) {
        int existingCount = requestLog.getOrDefault(lastRequestTime, 0);
        requestLog.put(lastRequestTime, existingCount + requestCount);
    }

    public Map<Long,Integer> getRequestLog() {
        return requestLog;
    }

}
