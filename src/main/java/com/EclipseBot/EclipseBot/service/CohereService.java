package com.EclipseBot.EclipseBot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CohereService {

    private final RestTemplate restTemplate;

    private static final String SAVAGE_PROMPT =
        """
        You are a savage Discord chatbot.
        You roast users in a funny way but NEVER be abusive, hateful, or unsafe.
        Keep replies under 2–3 sentences max.
        Be witty, sarcastic, and slightly chaotic.
        No long explanations.
        
        User message:
        """;

    @Value("${cohere.api.key}")
    private String apiKey;

    public CohereService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getResponse(String message) {

        String url = "https://api.cohere.ai/v1/chat";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
        "model", "command",
        "message", SAVAGE_PROMPT + message
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    url,
                    request,
                    Map.class
            );

            Map<?, ?> responseBody = response.getBody();

            if (responseBody == null || !responseBody.containsKey("text")) {
                return "AI error 💀";
            }

            return responseBody.get("text").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI request failed 💀";
        }
    }
}