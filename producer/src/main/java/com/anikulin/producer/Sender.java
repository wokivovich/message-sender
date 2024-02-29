package com.anikulin.producer;

import com.anikulin.producer.request.Word;
import com.anikulin.producer.service.WordService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Sender {

    private final WordService wordService;

    private final KafkaTemplate kafkaTemplate;

    @Value("${word.sending.method}")
    private String sendingMethod;

    @Value("${word.sending.timeout}")
    private int sendingTimeout;

    public Sender(WordService wordService, KafkaTemplate<Long, Word> kafkaTemplate) {
        this.wordService = wordService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostConstruct
    public void feignTest() throws InterruptedException {
        while (true) {

            if (sendingMethod.equals("kafka")) {
                kafkaTemplate.send("word", generateRandomWord());
            } else if (sendingMethod.equals("feign")) {
                wordService.sendMessage(generateRandomWord());
            } else {
                throw new IllegalArgumentException("Incorrect sending method - " + sendingMethod);
            }

            Thread.sleep(sendingTimeout);
        }
    }

    private Word generateRandomWord() {
        int length = new Random().nextInt(100) + 1;
        StringBuilder randomString = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < length; i++) {
            int randomIndex = new Random().nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }

        return new Word(randomString.toString());
    }

}
