package com.anikulin.consumer.api;

import com.anikulin.consumer.request.WordRequest;
import com.anikulin.consumer.entity.Word;
import com.anikulin.consumer.service.WordService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class Controller {

    private final WordService wordService;

    public Controller(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping
    public void saveMessage(@RequestBody WordRequest request) {
        Word word = Word.builder()
                .word(request.word())
                .receiveDate(LocalDateTime.now())
                .build();

        wordService.saveWord(word);
    }

    @KafkaListener(topics = "word", containerFactory = "userKafkaListenerContainerFactory", groupId = "1")
    public void handleMessage(ConsumerRecord<Long, WordRequest> record) {
        Word word = Word.builder()
                .word(record.value().word())
                .receiveDate(LocalDateTime.now())
                .build();

        wordService.saveWord(word);
    }
}
