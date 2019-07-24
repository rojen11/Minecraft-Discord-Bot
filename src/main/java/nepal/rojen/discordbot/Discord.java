package nepal.rojen.discordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

import org.bukkit.ChatColor;

public class Discord extends ListenerAdapter {

    private static JDA jda;
    private String token = "NTA4OTg0MjkwMzE5NjYzMTE2.XTdGeA.jT0298ceoalFHDGCP90WiwbPZ3Y";
    TextChannel textChannel;
    Guild guild;

    public Discord() throws LoginException, InterruptedException {
        jda = new JDABuilder(this.token).build();
        jda.setAutoReconnect(true);
        jda.addEventListener(this);
        jda.awaitReady();
        guild = jda.getGuildById("468006020724293632");
        textChannel = guild.getTextChannelById("594423079803813892");

    }

    public void stop() {
        jda.shutdown();
    }

    public void sendMessage(String msg) {
        textChannel.sendMessage(msg).queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getTextChannel() == textChannel) {
            if (event.getMember().getRoles().contains(guild.getRoleById("603461372503916544"))) {
                String msg = ChatColor.BLUE + event.getAuthor().getName() + "[Discord]: " + ChatColor.WHITE
                        + event.getMessage().getContentRaw();
                DiscordBot.broadcastMsg(msg);
            }
        }
    }

}
