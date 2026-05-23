package com.EclipseBot.EclipseBot.config;

import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class SpamPingLuffy extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return; // Ignore messages from bots

        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (content.equalsIgnoreCase("ping luffy")) {
            event.getChannel().sendMessage("<@1304744654750617602>" 
            + " <@1304744654750617602> " 
            + "<@1304744654750617602> " 
            + "<@1304744654750617602> "
            + "<@1304744654750617602> ").queue();
        }
    }
    
}
