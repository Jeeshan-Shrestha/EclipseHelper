package com.EclipseBot.EclipseBot.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;

@Component
public class BotEventListener extends ListenerAdapter {

    private final Path path = Paths.get("replies.txt");
    final List<String> savageReplies = new ArrayList<>();

    @PostConstruct
    public void loadReplies() {
        try {

            System.out.println("Working dir: " + System.getProperty("user.dir"));
            System.out.println("File path: " + path.toAbsolutePath());

            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            savageReplies.clear();
            savageReplies.addAll(Files.readAllLines(path));

            System.out.println("Loaded replies: " + savageReplies.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("Message received!");

        final Random random = new Random();

        
        if (event.getAuthor().isBot()) return; // Ignore messages from bots
        

        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (content.equalsIgnoreCase("lurang")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Lurang is GAY").queue();

        }

        if (content.equalsIgnoreCase("nara")){
            event.getChannel().sendMessage("Nara is GOAT").queue();
        }

        if (message.getMentions().isMentioned(event.getJDA().getSelfUser())) {

            String reply = savageReplies.get(random.nextInt(savageReplies.size()));

            event.getChannel()
                    .sendMessage(reply)
                    .queue();
        }

        if (content.startsWith("!addreply ")) {

            // check admin permission
            if (!event.getMember().hasPermission(net.dv8tion.jda.api.Permission.ADMINISTRATOR)) {
                event.getChannel().sendMessage("You are not an admin.").queue();
                return;
            }

            String newReply = content.substring("!addreply ".length());

            savageReplies.add(newReply);

            try {
                Files.write(path, savageReplies);
            } catch (Exception e) {
                e.printStackTrace();
            }

            event.getChannel().sendMessage("Reply added: " + newReply).queue();
        }

        if (content.startsWith("!removereply ")) {

            if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.getChannel().sendMessage("You are not an admin.").queue();
                return;
            }

            try {
                int index = Integer.parseInt(content.split(" ")[1]);

                if (index < 0 || index >= savageReplies.size()) {
                    event.getChannel().sendMessage("Invalid index.").queue();
                    return;
                }

                String removed = savageReplies.remove(index);

                Files.write(path, savageReplies);

                event.getChannel().sendMessage("Removed: " + removed).queue();

            } catch (Exception e) {
                event.getChannel().sendMessage("Usage: !removereply <index>").queue();
            }
        }

        if (content.equalsIgnoreCase("!listreplies")) {

            try {
                List<String> lines = Files.readAllLines(path);

                List<String> numbered = new ArrayList<>();

                for (int i = 0; i < lines.size(); i++) {
                    numbered.add(i + ": " + lines.get(i));
                }

                Path tempFile = Paths.get("replies_numbered.txt");
                Files.write(tempFile, numbered);

                event.getChannel()
                    .sendFiles(FileUpload.fromData(tempFile.toFile(), "replies_numbered.txt"))
                    .queue();

            } catch (Exception e) {
                e.printStackTrace();
                event.getChannel().sendMessage("Failed to generate file.").queue();
            }
        }

        

    }
    

}
