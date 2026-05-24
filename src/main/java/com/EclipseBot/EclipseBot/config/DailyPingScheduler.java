package com.EclipseBot.EclipseBot.config;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class DailyPingScheduler {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void start(JDA jda) {

        
        long channelId = 1507317682565808169L;

        TextChannel channel = jda.getTextChannelById(channelId);

        if (channel == null) {
            System.out.println("Channel not found!");
            return;
        }

        Runnable task = () -> {
            channel.sendMessage("<@&1507321441228423218> WAKE THE HELL UP ITS 8PM 🔥! HOP IN VC AND LETS PLAY LOCKED")
                    .queue();
        };

        long initialDelay = getInitialDelay();

        long oneDay = TimeUnit.DAYS.toSeconds(1);

        scheduler.scheduleAtFixedRate(
                task,
                initialDelay,
                oneDay,
                TimeUnit.SECONDS
        );

        System.out.println("Daily scheduler started.");
    }

    private long getInitialDelay() {
        ZoneId zone = ZoneId.of("Asia/Kathmandu");
        ZonedDateTime now = ZonedDateTime.now(zone);
        ZonedDateTime nextRun = now.with(LocalTime.of(20, 0, 0));

        if (now.compareTo(nextRun) >= 0) {
            nextRun = nextRun.plusDays(1);
        }

        return Duration.between(now, nextRun).getSeconds();
    }
}