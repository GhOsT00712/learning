package RateLimiter;

import java.util.Map;
import java.util.HashMap;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

public class ConfigurationService {
    public Map<String, Configuration> getConfiguration() {
        Map<String, Configuration> configMap = new HashMap<>();
        Gson gson = new Gson();
        
        String configPath = java.nio.file.Paths.get("RateLimiter", "config.json").toString();
        try (FileReader reader = new FileReader(configPath)) {
            JsonElement jsonElement = new JsonStreamParser(reader).next();
            if (jsonElement.isJsonArray()) {
                for (JsonElement element : jsonElement.getAsJsonArray()) {
                    Configuration config = gson.fromJson(element, Configuration.class);
                    configMap.put(config.getApiKey(), config);
                }
            } else if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                    Configuration config = gson.fromJson(entry.getValue(), Configuration.class);
                    configMap.put(config.getApiKey(), config);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return configMap;
    }
}
