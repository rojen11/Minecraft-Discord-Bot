package me.rojen11.discordbot;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Discord extends ListenerAdapter {

    private static JDA jda;
    private String token;
    TextChannel textChannel;
    Guild guild;
    String roleId;

    public Discord(String token, String guild, String textChannel, String roleId)
            throws LoginException, InterruptedException {
        this.token = token;
        jda = new JDABuilder(this.token).build();
        jda.setAutoReconnect(true);
        jda.addEventListener(this);
        jda.awaitReady();
        jda.getPresence().setGame(Game.playing("Minecraft || Players Online: " + Bukkit.getOnlinePlayers().size()));
        this.guild = jda.getGuildById(guild);
        this.textChannel = jda.getTextChannelById(textChannel);
        this.roleId = roleId;

    }

    public void stop() {
        jda.shutdown();
    }

    public void sendMessage(String msg) {
        textChannel.sendMessage(msg).queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();

        if (event.getTextChannel() == textChannel) {
            if (msg.startsWith("!")) {
                String command = msg.substring(1, msg.length());
                textChannel.sendMessage(command).queue();
                if (command.equals("onlineplayers")) {
                    textChannel.sendMessage(Bukkit.getOnlinePlayers().toString()).queue();
                }
                return;
            }
            if (event.getMember().getRoles().contains(guild.getRoleById(roleId))) {
                String message = ChatColor.AQUA + "[Discord]" + ChatColor.GREEN + event.getAuthor().getName() + ": "
                        + ChatColor.WHITE + event.getMessage().getContentRaw();
                Bukkit.broadcastMessage(message);
            }
        }
    }

    public void setPresence() {
        jda.getPresence().setGame(Game.playing("Minecraft || Players Online: " + Bukkit.getOnlinePlayers().size()));
    }

}
