package com.anikulin.producer.service;

import com.anikulin.producer.request.Word;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "consumer", url = "localhost:5000")
public interface FeignWordSender {

    @PostMapping("/")
    void sendMessage(@RequestBody Word word);
}
