package discordbotplugin.discordbotplugin.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Collection;

import static org.bukkit.Bukkit.getLogger;

public class GiveCookieToAllHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Giving cookies to all players";
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();

        Collection<? extends Player> player = Bukkit.getServer().getOnlinePlayers();
        for (Player p : player) {
            ItemStack itemStack = new ItemStack(Material.COOKIE, 1, (short) 1);
            p.getInventory().addItem(itemStack);
        }

        getLogger().info("Giving cookies to all players");
    }
}
