package me.rojen11.discordbot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MinecraftEventHandler implements Listener {

    Discord bot = DiscordBot.bot;

    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent event) {
        String message = event.getPlayer().getName() + ": " + event.getMessage();
        discordMsg(message);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String message = event.getPlayer().getName() + " Joined.";
        discordMsg(message);
        discordSetPresence();

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        String message = event.getPlayer().getName() + " Left. ";
        discordMsg(message);
        discordSetPresence();
    }

    @EventHandler
    public void onPlayerDead(PlayerDeathEvent event) {
        discordMsg(event.getDeathMessage());
    }

    private void discordMsg(String message) {
        if (DiscordBot.discordConnected) {
            bot.sendMessage(message);
        }
    }

    private void discordSetPresence() {
        if (DiscordBot.discordConnected) {
            bot.setPresence();
        }
    }
}
