package com.anikulin.producer.service;

import com.anikulin.producer.request.Word;
import com.anikulin.producer.service.FeignWordSender;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    private final FeignWordSender feignWordSender;

    public WordService(FeignWordSender feignWordSender) {
        this.feignWordSender = feignWordSender;
    }

    public void sendMessage(Word word) {
        feignWordSender.sendMessage(word);
    }
}
