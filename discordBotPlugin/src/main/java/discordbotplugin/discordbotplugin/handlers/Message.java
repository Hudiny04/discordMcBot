package discordbotplugin.discordbotplugin.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import discordbotplugin.discordbotplugin.utils.Utils;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static org.bukkit.Bukkit.getLogger;

public class Message implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        Utils utils = new Utils();
        Map<String, String> params = utils.getStringStringMap(exchange.getRequestURI().getQuery());
        if ( Bukkit.getPlayer(params.get("target")) != null){
            response = "Sending message to " +params.get("target");
            Objects.requireNonNull(Bukkit.getPlayer(params.get("target"))).sendMessage(params.get("author")+ ": " +params.get("message").replace("+", " "));
        }else{
            response = "No such player exists";
        }
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();

        getLogger().info("Message specific player");
    }
}
