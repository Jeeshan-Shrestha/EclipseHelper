package com.EclipseBot.EclipseBot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

@Configuration
public class BotConfig {

    @org.springframework.beans.factory.annotation.Value("${discord.bot.token}")
    private String token;   

    private final BotEventListener botEventListener;

    public BotConfig(BotEventListener botEventListener) {
        this.botEventListener = botEventListener;
    }
    @Bean
    public JDA jda() throws Exception {
        return JDABuilder.createDefault(token
            ,GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.MESSAGE_CONTENT
        )
        .addEventListeners(botEventListener)
        .build().awaitReady();
    }


}
