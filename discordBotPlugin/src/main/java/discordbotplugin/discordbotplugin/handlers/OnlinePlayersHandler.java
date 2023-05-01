package discordbotplugin.discordbotplugin.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Collection;

import static org.bukkit.Bukkit.getLogger;

public class OnlinePlayersHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
        StringBuilder response = new StringBuilder();
        response.append(Bukkit.getOnlinePlayers().size()).append(": ");
        for (Player p: players) {
            response.append(p.getName()).append(" ");
        }
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.toString().getBytes());
        exchange.getResponseBody().close();

        getLogger().info("Sending online Players on Discord");

    }
}
