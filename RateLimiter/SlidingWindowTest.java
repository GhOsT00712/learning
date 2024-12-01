
package RateLimiter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowTest {

    @Test
    void testAllowRequest() {
        ConfigurationService configurationService = new ConfigurationService();
        IRateLimiter rateLimiter = new SlidingWindow(configurationService);

        // Allow first 3 requests
        for (int i = 0; i < 3; i++) {
            assertTrue(rateLimiter.allowRequest("service1"));
        }

        // 4th request should be denied
        assertFalse(rateLimiter.allowRequest("service1"));

        // Wait for 1 second
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // After 5 second, the next request should be allowed
        assertTrue(rateLimiter.allowRequest("service1"));}
}