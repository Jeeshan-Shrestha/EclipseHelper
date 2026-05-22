package com.EclipseBot.EclipseBot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

@Configuration
public class BotConfig {

    @Bean
    public DailyPingScheduler dailyPingScheduler() {
        return new DailyPingScheduler();
    }

    @org.springframework.beans.factory.annotation.Value("${discord.bot.token}")
    private String token;   

    private final BotEventListener botEventListener;
    private final AfkCommand afkCommand;

    public BotConfig(BotEventListener botEventListener, AfkCommand afkCommand) {
        this.botEventListener = botEventListener;
        this.afkCommand = afkCommand;
    }
    
    @Bean
    public JDA jda(DailyPingScheduler scheduler) throws Exception {

        JDA jda = JDABuilder.createDefault(token,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_VOICE_STATES
            )
            .addEventListeners(botEventListener, afkCommand)
            .build()
            .awaitReady();

        scheduler.start(jda);

        return jda;
    }


}
