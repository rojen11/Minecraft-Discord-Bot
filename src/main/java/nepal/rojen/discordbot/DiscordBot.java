package nepal.rojen.discordbot;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public class DiscordBot extends JavaPlugin implements Listener {

    public static Discord bot;

    @Override
    public void onEnable() {
        getLogger().info("DiscordBot enabled.");
        try {
            bot = new Discord();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getServer().getPluginManager().registerEvents(new MinecraftEventHandler(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("DiscordBot disabled.");
        bot.stop();
    }

    public static void broadcastMsg(String msg) {
        Bukkit.broadcastMessage(msg);
    }

}
