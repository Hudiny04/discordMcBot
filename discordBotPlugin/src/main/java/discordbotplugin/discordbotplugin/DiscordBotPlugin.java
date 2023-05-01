package discordbotplugin.discordbotplugin;

import com.sun.net.httpserver.HttpServer;
import discordbotplugin.discordbotplugin.handlers.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;

public final class DiscordBotPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 5000), 0);
            server.createContext("/get-online-players", new OnlinePlayersHandler());
            server.createContext("/give-all-cookie", new GiveCookieToAllHandler());
            server.createContext("/give-player-cookie", new GiveCookieToOneHandler());
            server.createContext("/kill-all", new KillAllHandler());
            server.createContext("/kill", new KillOne());
            server.createContext("/message", new MessageHandler());
            server.createContext("/starve-all", new StarveAllHandler());
            server.createContext("/starve", new StarveOneHandler());

            server.setExecutor(null); // creates a default executor
            server.start();
            getLogger().info(" Server started on port 5000");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

