package discordbotplugin.discordbotplugin;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Objects;

public final class DiscordBotPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            server.createContext("/giveallcookie", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    getLogger().info("Giving cookies to all players");

                    String response = "Giving cookies to all players";
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();

                    ItemStack itemStack = new ItemStack(Material.COOKIE, 1, (short) 1);

                    Collection<? extends Player> player = Bukkit.getServer().getOnlinePlayers();
                    for (Player p : player) {
                        p.getInventory().addItem(itemStack);
                    }


                }
            });
            server.createContext("/killall", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {

                    String response = "Killing all players";
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();

                    getLogger().info("Killing all players");

                    Collection<? extends Player> player = Bukkit.getServer().getOnlinePlayers();
                    for (Player p : player) {
                        p.setHealth(0);
                    }
                }
            });
            server.createContext("/getOnlinePlayers", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    Collection<? extends Player> player = Bukkit.getServer().getOnlinePlayers();

                    StringBuilder response = new StringBuilder();
                    response.append(Bukkit.getOnlinePlayers().size()).append(": ");
                    for (Player p : player) {
                        response.append(p.getName()).append(" ");
                    }

                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.toString().getBytes());
                    exchange.getResponseBody().close();

                    getLogger().info("Getting online players");

                }
            });server.createContext("/starvePlayers", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    Collection<? extends Player> player = Bukkit.getServer().getOnlinePlayers();


                    String response = "Starving all players";

                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.toString().getBytes());
                    exchange.getResponseBody().close();

                    getLogger().info("Getting online players");

                    for (Player p : player) {
                        p.setFoodLevel(0);
                    }

                }
            });

         /*   server.createContext("/lightningStrikePlayers", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {



                    String response = "strike all players with lightning";

                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();

                    getLogger().info("Striking all players with lightning");
                    Collection<? extends Player> player = Bukkit.getServer().getOnlinePlayers();
                    for (Player p : player) {
                        Location loc;
                        loc = p.getLocation();
                        LightningStrike lightning = (LightningStrike) loc.getWorld().strikeLightning(loc);
                    }

                }
            });*/
            server.setExecutor(null); // creates a default executor
            server.start();
             getLogger().info(" Server started on port 8001");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
