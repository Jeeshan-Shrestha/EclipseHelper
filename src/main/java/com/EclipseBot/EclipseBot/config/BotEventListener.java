package com.EclipseBot.EclipseBot.config;

import java.util.EventListener;

import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class BotEventListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("Message received!");

        
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

    }
    

}
