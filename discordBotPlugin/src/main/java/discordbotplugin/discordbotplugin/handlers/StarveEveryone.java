package discordbotplugin.discordbotplugin.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Collection;

import static org.bukkit.Bukkit.getLogger;

public class StarveEveryone implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Starving all players";
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
        Collection<? extends Player> player = Bukkit.getServer().getOnlinePlayers();
        for (Player p : player) {
            p.setFoodLevel(0);
        }

        getLogger().info("Starving all players");
    }
}
