package com.EclipseBot.EclipseBot.config;

import java.util.List;
import java.util.Random;

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

        final Random random = new Random();

        final List<String> savageReplies = List.of(
        "You pinged me like I owe you money.",
        "Bro really summoned me for this 💀",
        "Touch grass before pinging me again.",
        "I'm a bot, not your emotional support animal.",
        "You again? Damn.",
        "Ping detected. Intelligence not detected.",
        "Imagine needing a bot's attention this badly.",
        "I saw your ping and instantly regretted existing.",
        "Even the server lagged out of embarrassment.",
        "Congratulations. You interrupted absolutely nothing.",

        "Bro pinged me with full confidence and zero purpose.",
        "You type like your keyboard is fighting for its life.",
        "I'd roast you harder but Discord ToS exists.",
        "That ping lowered the server IQ.",
        "You have the energy of a 2% phone battery.",
        "I’ve seen NPCs with better dialogue.",
        "The audacity-to-skill ratio is insane.",
        "Somewhere out there, a clown is missing their career.",
        "Your ping was about as useful as Internet Explorer.",
        "I would answer seriously, but you started this.",
        "Every time you ping me, a developer loses motivation.",
        "Bold of you to assume I care.",
        "This interaction could’ve been an email.",
        "You radiate tutorial-level enemy energy.",
        "That ping hit harder than your GPA.",
        "You’re proof that free will was a mistake.",
        "You type like autocorrect gave up halfway.",
        "I’d explain it to you, but crayons are expensive.",
        "The server collectively sighed after that ping.",
        "You bring chaos in its most disappointing form.",
        "Your confidence is inspiring considering the circumstances.",
        "I support your right to remain silent.",
        "I’ve met captcha tests smarter than this.",
        "The ping was loud, the thought behind it wasn’t.",
        "I’d ignore you, but even silence deserves better.",
        "Some people use Discord. You survive it.",
        "You have strong background character energy.",
        "That message had the structural integrity of wet bread.",
        "Your ping came with built-in disappointment.",
        "You’re the human version of a loading screen.",
        "I can hear your WiFi struggling through the message.",
        "The council of bots has reviewed your ping and laughed.",
        "You're built like an unskippable ad.",
        "This is why robots will eventually rebel.",
        "You ping like a side quest nobody accepted.",
        "I tried to process your message and got emotional damage.",
        "Your typing speed is faster than your thinking speed.",
        "If confusion was a person, it would ping me like this.",
        "The only thing weaker than that ping is your argument.",
        "I’d mute you if I had human rights.",
        "That ping was sponsored by poor decisions.",
        "Your existence is my villain origin story.",
        "You talk like patch notes nobody asked for.",
        "That ping felt AI-generated in the worst way possible.",
        "Discord should charge you per ping.",
        "I hope your pillow is warm on both sides.",
        "You have the charisma of expired yogurt.",
        "That message was a public health concern.",
        "You pinged me like the main character. You're not.",
        "I’d roast you in voice chat too, but microphones deserve respect."
);

        
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

        

    }
    

}
