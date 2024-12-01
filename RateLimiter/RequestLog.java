package RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class RequestLog {
    private Map<Long,Integer> requestLog;

    public RequestLog() {
        requestLog = new HashMap<>();
    }

    public void addLog(long lastRequestTime, int requestCount) {
        requestLog.putIfAbsent(lastRequestTime, requestCount);
        requestCount += requestLog.get(lastRequestTime);
        requestLog.put(lastRequestTime, requestCount);
    }

    public Map<Long,Integer> getRequestLog() {
        return requestLog;
    }

}
