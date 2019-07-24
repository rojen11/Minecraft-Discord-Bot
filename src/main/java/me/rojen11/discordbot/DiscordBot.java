package me.rojen11.discordbot;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public class DiscordBot extends JavaPlugin implements Listener {

    public static Discord bot;
    public static Boolean discordConnected;
    FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        getLogger().info("DiscordBot enabled.");
        this.saveDefaultConfig();
        String token, guildId, textChannelId, roleId;
        token = config.getString("token");
        guildId = config.getString("guildId");
        textChannelId = config.getString("textChannelId");
        roleId = config.getString("roleId");
        Boolean allData = true;

        if (token.equals(null)) {
            getLogger().info("Input Token in config");
            allData = false;
        }
        if (guildId.equals(null)) {
            getLogger().info("Input GuildId in config");
            allData = false;
        }
        if (textChannelId.equals(null)) {
            getLogger().info("Input Text Channel Id in config");
            allData = false;
        }
        if (roleId.equals(null)) {
            getLogger().info("Input Role Id in config");
            allData = false;
        }
        if (allData) {
            try {
                bot = new Discord(token, guildId, textChannelId, roleId);
                discordConnected = true;
            } catch (LoginException e) {
                getLogger().info("Invalid Token");
                discordConnected = false;
            } catch (InterruptedException e) {
                discordConnected = false;
                e.printStackTrace();
            }

        } else {
            discordConnected = false;

        }

        getServer().getPluginManager().registerEvents(new MinecraftEventHandler(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("DiscordBot disabled.");
        if (discordConnected) {
            bot.stop();
        }

    }

}
