package com.anikulin.consumer.service;

import com.anikulin.consumer.entity.Word;
import com.anikulin.consumer.repository.WordRepository;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    private final WordRepository repository;

    public WordService(WordRepository repository) {
        this.repository = repository;
    }

    public void saveWord(Word word) {
        repository.save(word);
    }
}
