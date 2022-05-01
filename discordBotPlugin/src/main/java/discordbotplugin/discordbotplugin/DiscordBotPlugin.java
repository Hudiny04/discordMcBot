package discordbotplugin.discordbotplugin;

import com.sun.net.httpserver.HttpServer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;

public final class DiscordBotPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            server.createContext("/get-online-players", exchange -> {
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
            });

            server.createContext("/give-all-cookie", exchange -> {
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
            });

            server.createContext("/give-player-cookie", exchange -> {

                ItemStack itemStack = new ItemStack(Material.COOKIE, 1, (short) 1);
                String response = "";
                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                if ( Bukkit.getPlayer(params.get("target")) != null){
                    response = "Giving cookie to " + params.get("target");
                    Objects.requireNonNull(Bukkit.getPlayer(params.get("target"))).getInventory().addItem(itemStack);
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();
                }else{
                    response = "No such player exists";
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();
                }

                getLogger().info("Giving cookie to specific player request");
            });

            server.createContext("/kill-all", exchange -> {
                String response = "Killing all players";
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
                Collection<? extends Player> player = Bukkit.getServer().getOnlinePlayers();
                for (Player p : player) {
                    p.setHealth(0);
                }

                getLogger().info("Killing all players request");
            });

                server.createContext("/kill", exchange -> {
                    String response = "";
                    Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                    if ( Bukkit.getPlayer(params.get("target")) != null){
                        response = "Killing " + params.get("target");

                        exchange.sendResponseHeaders(200, response.length());
                        exchange.getResponseBody().write(response.getBytes());
                        exchange.getResponseBody().close();
                        Objects.requireNonNull(Bukkit.getPlayer(params.get("target"))).setHealth(0);
                    }else{
                        response = "No such player exists";
                        exchange.sendResponseHeaders(200, response.length());
                        exchange.getResponseBody().write(response.getBytes());
                        exchange.getResponseBody().close();
                    }

                getLogger().info("Kill specific player request");
            });

                server.createContext("/message", exchange -> {
                String response = "";
                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                if ( Bukkit.getPlayer(params.get("target")) != null){
                     response = "Sending message to " +params.get("target");
                    Objects.requireNonNull(Bukkit.getPlayer(params.get("target"))).sendMessage(params.get("author")+ ": " +params.get("message").replace("+", " "));
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();
                }else{
                    response = "No such player exists";
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();
                }

                getLogger().info("Message specific player request");
            });

            server.createContext("/starve-all", exchange -> {
                String response = "Starving all players";
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
                Collection<? extends Player> player = Bukkit.getServer().getOnlinePlayers();
                for (Player p : player) {
                    p.setFoodLevel(0);
                }

                getLogger().info("Starving all players");
            });


            server.createContext("/starve", exchange -> {
                String response = "";
                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
                if ( Bukkit.getPlayer(params.get("target")) != null){
                    response = "Starving " + params.get("target");
                    Objects.requireNonNull(Bukkit.getPlayer(params.get("target"))).setFoodLevel(0);
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();
                }else{
                    response = "No such player exists";
                    exchange.sendResponseHeaders(200, response.length());
                    exchange.getResponseBody().write(response.getBytes());
                    exchange.getResponseBody().close();
                }

                getLogger().info("Starving specific player");
            });

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

    public Map<String, String> queryToMap(String query) {
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

