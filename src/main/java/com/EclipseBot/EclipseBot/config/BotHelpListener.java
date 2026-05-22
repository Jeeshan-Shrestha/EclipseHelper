package com.EclipseBot.EclipseBot.config;

import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class BotHelpListener extends ListenerAdapter {
    
        @Override
        public void onMessageReceived(MessageReceivedEvent event) {
            super.onMessageReceived(event); // Call the parent method to handle existing logic
    
            String content = event.getMessage().getContentRaw();
    
            if (content.equalsIgnoreCase("!help")) {
                String helpMessage = "```Available commands:\n" +
                        "!help - Show this help message\n" +
                        "!listreplies - List all savage replies\n" +
                        "@EclipseHelper - Get a random savage reply\n"+
                        "?afk [reason] - Set your AFK status with an optional reason\n```";
    
                event.getChannel().sendMessage(helpMessage).queue();
            }
        }

}
