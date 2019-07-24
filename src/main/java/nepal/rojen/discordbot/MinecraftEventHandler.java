package nepal.rojen.discordbot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MinecraftEventHandler implements Listener {

    Discord bot = DiscordBot.bot;

    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent event) {
        String message = event.getPlayer().getName() + ": " + event.getMessage();
        bot.sendMessage(message);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String message = event.getPlayer().getName() + " Joined.";
        bot.sendMessage(message);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        String message = event.getPlayer().getName() + " Left. ";
        bot.sendMessage(message);
    }
}
