package discordbotplugin.discordbotplugin.utils;

import java.util.HashMap;
import java.util.Map;

public class Utils implements IUtils{
    @Override
    public Map<String, String> getStringStringMap(String query) {
        if(query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }
}
