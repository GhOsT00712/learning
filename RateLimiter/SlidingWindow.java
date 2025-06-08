package RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow implements IRateLimiter {
    private Map<String,RequestLog> requestLog;
    private Map<String, Configuration> configurationCache;

    public SlidingWindow(ConfigurationService configService) {
        this.requestLog = new HashMap<>();
        configurationCache = configService.getConfiguration();
    }

    @Override
    public boolean allowRequest(String apiKey) {
        Configuration config = configurationCache.get(apiKey);
        if (config == null) {
            return false;
        }

        RequestLog log = requestLog.get(apiKey);
        if (log == null) {
            log = new RequestLog();
        }
        long startTime = ((long) (System.currentTimeMillis() / 1000)) - config.getWindowSize();

        int totalRequests = 0;
        
        for(Map.Entry<Long,Integer> entry : log.getRequestLog().entrySet()) {
            if(entry.getKey() >= startTime) {
                totalRequests += entry.getValue();
                if(totalRequests >= config.getRateLimit()) {
                    return false;
                }
            }
        }

        log.addLog(System.currentTimeMillis() / 1000, 1);
        this.requestLog.put(apiKey, log);

        return true;
    }

}