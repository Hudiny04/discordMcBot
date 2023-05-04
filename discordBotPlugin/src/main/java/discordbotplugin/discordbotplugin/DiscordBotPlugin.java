package discordbotplugin.discordbotplugin;

import com.sun.net.httpserver.HttpServer;
import discordbotplugin.discordbotplugin.handlers.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.InetSocketAddress;

public final class DiscordBotPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 5000), 0);
            server.createContext("/online-players", new OnlinePlayers());
            server.createContext("/give-everyone-cookie", new GiveEveryoneCookie());
            server.createContext("/give-one-cookie", new GiveOneCookie());
            server.createContext("/kill-everyone", new KillEveryone());
            server.createContext("/kill-one", new KillOne());
            server.createContext("/message", new Message());
            server.createContext("/starve-everyone", new StarveEveryone());
            server.createContext("/starve-one", new StarveOne());

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

